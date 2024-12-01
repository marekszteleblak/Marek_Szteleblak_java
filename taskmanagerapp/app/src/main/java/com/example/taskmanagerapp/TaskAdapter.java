package com.example.taskmanagerapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.graphics.Paint;


import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {

    public TaskAdapter(Context context, List<Task> tasks) {
        super(context, 0, tasks);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView taskText = convertView.findViewById(R.id.taskText);
        TextView categoryText = convertView.findViewById(R.id.categoryText);  // Wyświetlenie kategorii
        CheckBox taskCheckBox = convertView.findViewById(R.id.taskCheckbox); // CheckBox do zaznaczenia
        Button deleteButton = convertView.findViewById(R.id.deleteButton);



        final Task task = getItem(position);
        taskText.setText(task.getName());
        categoryText.setText(task.getCategory());  // Wyświetlanie kategorii

        // Zmienianie wyglądu w zależności od stanu zadania
        if (task.isCompleted()) {
            taskText.setTextColor(Color.GRAY); // Szary kolor tekstu
            taskText.setPaintFlags(taskText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG); // Przekreślenie tekstu
        } else {
            taskText.setTextColor(Color.BLACK); // Czarny kolor tekstu
            taskText.setPaintFlags(taskText.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG)); // Usunięcie przekreślenia
        }

        // Ustawienie stanu CheckBox
        taskCheckBox.setChecked(task.isCompleted());

        // Obsługa CheckBox
        taskCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            task.setCompleted(isChecked);
            notifyDataSetChanged();  // Zaktualizowanie widoku
        });

        // Obsługa przycisku usuwania
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Usuwanie zadania z listy
                MainActivity.tasks.remove(position);
                notifyDataSetChanged();  // Powiadomienie adaptera o zmianie listy
            }
        });

        // Edycja zadania po kliknięciu na zadanie
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Uruchomienie EditTaskActivity z danymi do edycji
                Intent intent = new Intent(getContext(), EditTaskActivity.class);
                intent.putExtra("position", position);  // Przekazanie pozycji zadania
                getContext().startActivity(intent);
            }
        });

        return convertView;
    }
}
