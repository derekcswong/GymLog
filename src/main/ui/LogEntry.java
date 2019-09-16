package ui;

import java.util.ArrayList;
import java.time.LocalDate;

//this log entry should have a date, list of exercises (class - with weight and reps), category of workout
public class LogEntry {
    private LocalDate date;
    private ArrayList<String> exercises;
    private String category;

    public LogEntry(LocalDate date) {
        this.date = date;
    }

    public void blue() {
        System.out.println("That's Cool!");
    }
}