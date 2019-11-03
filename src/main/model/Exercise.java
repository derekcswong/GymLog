package model;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Exercise {

    protected String name;

    public Exercise(String name) {
        this.name = name;
    }

    //MODIFIES: this
    //EFFECTS: changes name of exercise
    public void changeName(String s) {
        this.name = s;
    }

    //EFFECTS: convert exercise details to string
    public abstract String exerciseDetailsString();

    //EFFECTS: instantiates new exercise with the same values and returns it
    public abstract Exercise copy();

    public String getName() {
        return name;
    }
}
