package com.example.taskmanagerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class EditTaskActivity extends AppCompatActivity {

    private EditText taskInput;
    private Spinner categorySpinner;
    private Button saveButton;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        // Initialize views
        taskInput = findViewById(R.id.editTaskInput);
        categorySpinner = findViewById(R.id.editCategorySpinner);
        saveButton = findViewById(R.id.saveButton);

        // Get the position of the task to edit
        position = getIntent().getIntExtra("position", -1);

        if (position != -1) {
            // Load the task for editing based on position
            Task task = MainActivity.tasks.get(position);
            taskInput.setText(task.getName());

            // Set the correct category in the spinner
            ArrayAdapter<String> adapter = (ArrayAdapter<String>) categorySpinner.getAdapter();
            int spinnerPosition = adapter.getPosition(task.getCategory());
            categorySpinner.setSelection(spinnerPosition);
        }

        // Handle save button click
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Collect updated data
                String newTaskName = taskInput.getText().toString();
                String newCategory = categorySpinner.getSelectedItem().toString();

                Intent resultIntent = new Intent();

                if (position != -1) {
                    // Edit existing task
                    Task task = MainActivity.tasks.get(position);
                    task.setName(newTaskName);
                    task.setCategory(newCategory);

                    // Pass updated task data back
                    resultIntent.putExtra("position", position);
                }

                setResult(RESULT_OK, resultIntent); // Return results to calling activity
                finish();  // Close edit screen
            }
        });
    }
}