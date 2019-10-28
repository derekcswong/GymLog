package model;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Exercise {

    protected String name;
    protected ArrayList<Workout> inWorkouts;

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

    //MODIFIES: this, Workout
    //EFFECTS: adds Workout to inWorkouts, and Exercise to Workout,
    // if it isn't in inWorkouts already, otherwise do nothing
    public void addWorkout(Workout w) {
        if (!inWorkouts.contains(w)) {
            inWorkouts.add(w);
            w.addExercise(this);
        }
    }

    //MODIFIES: this, Workout
    //EFFECTS: if inWorkouts contains w then removes w from inWorkouts, and this from w,
    // ,otherwise do nothing
    public void removeWorkout(Workout w) {
        if (inWorkouts.contains(w)) {
            inWorkouts.remove(w);
            w.removeExercise(this);
        }
    }

    public void setInWorkouts(ArrayList<Workout> inWorkouts) {
        this.inWorkouts = inWorkouts;
    }

    public ArrayList<Workout> getInWorkouts() {
        return inWorkouts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Exercise exercise = (Exercise) o;
        return Objects.equals(name, exercise.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
