package com.example.taskmanagerapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;


public class TaskListActivity extends AppCompatActivity {

    private ListView listView;
    private TaskAdapter taskAdapter;

    private static final int EDIT_TASK_REQUEST_CODE = 2; // Unique request code for task editing

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        listView = findViewById(R.id.taskListView);

        // Create adapter and set it on the ListView
        taskAdapter = new TaskAdapter(this, MainActivity.tasks);
        listView.setAdapter(taskAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        taskAdapter.notifyDataSetChanged();
    }


    // Handle results from other activities (e.g., EditTaskActivity)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EDIT_TASK_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            int position = data.getIntExtra("position", -1);

            if (position != -1) {
                // Notify adapter about changes
                taskAdapter.notifyDataSetChanged();
            }
        }
    }
}