package model;

import java.util.ArrayList;

public class Workout {

    private ArrayList<Exercise> exerciseList;
    private String category;

    //EFFECTS: set the date to date, set the category to cat, set the exerciseList to
    //an ArrayList, run addExercises() method
    public Workout(String cat, ArrayList<Exercise> exList) {
        this.category = cat;
        this.exerciseList = exList;
    }
}
