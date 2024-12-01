package com.example.taskmanagerapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Task implements Parcelable {

    private String name;
    private String category;
    private boolean isCompleted;

    public Task(String name, String category) {
        this.name = name;
        this.category = category;
        this.isCompleted = false; // domyślnie zadanie nie jest wykonane
    }

    // Gettery i Settery

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    // Metody Parcelable
    protected Task(Parcel in) {
        name = in.readString();
        category = in.readString();
        isCompleted = in.readByte() != 0;  // Odczyt wartości boolean
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(category);
        dest.writeByte((byte) (isCompleted ? 1 : 0)); // Zapis wartości boolean
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };
}