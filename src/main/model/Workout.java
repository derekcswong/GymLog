package model;

import java.util.ArrayList;
import java.util.Scanner;

public class Workout {

    private ArrayList<Exercise> exerciseList;
    private String category;

    //EFFECTS: set the date to date, set the category to cat, set the exerciseList to
    //an ArrayList, run addExercises() method
    public Workout(String cat, ArrayList<Exercise> exList) {
        this.category = cat;
        this.exerciseList = exList;
    }

    //MODIFIES: this
    //EFFECTS: adds Exercise to exerciseList
    public void addExercise(String name, int weight, int reps, int sets) {
        exerciseList.add(new Exercise(name, weight, reps, sets));
    }

    //EFFECTS: returns the number of exercises in this workout
    public int numOfExercises() {
        return exerciseList.size();
    }

}
