package com.example.taskmanagerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Task> tasks = new ArrayList<>(); // Task list
    private TaskAdapter taskAdapter;  // Adapter for tasks

    private EditText taskInput;
    private Button addButton, viewTasksButton;
    private Spinner categorySpinner;  // Spinner for category selection

    private static final int EDIT_TASK_REQUEST_CODE = 1; // Request code for task editing

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskInput = findViewById(R.id.taskInput);
        categorySpinner = findViewById(R.id.categorySpinner);  // Spinner for selecting category
        addButton = findViewById(R.id.addButton);
        viewTasksButton = findViewById(R.id.viewTasksButton);

        // Create adapter for the task list
        taskAdapter = new TaskAdapter(this, tasks);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskName = taskInput.getText().toString();
                String category = categorySpinner.getSelectedItem().toString();  // Get selected category

                if (!taskName.isEmpty()) {
                    // Add new task
                    tasks.add(new Task(taskName, category));
                    taskInput.setText("");  // Clear input field
                    taskAdapter.notifyDataSetChanged();  // Update adapter
                }
            }
        });

        viewTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open task list
                Intent intent = new Intent(MainActivity.this, TaskListActivity.class);
                startActivityForResult(intent, EDIT_TASK_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EDIT_TASK_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            int position = data.getIntExtra("position", -1);

            if (position != -1) {
                // Update the task list and notify adapter
                taskAdapter.notifyDataSetChanged();
            }
        }
    }
}