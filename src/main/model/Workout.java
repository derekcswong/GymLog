package model;

import java.util.ArrayList;

public class Workout {

    private ArrayList<Exercise> exerciseList;
    private String category;

    public Workout(String cat, ArrayList<Exercise> exList) {
        this.category = cat;
        this.exerciseList = exList;
    }


}
