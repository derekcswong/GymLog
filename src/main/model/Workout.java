package model;


import java.util.ArrayList;
import java.util.Objects;

public class Workout {

    private ArrayList<Exercise> exerciseList;
    private String category;

    public Workout(String cat, ArrayList<Exercise> exList) {
        this.category = cat;
        this.exerciseList = exList;
    }

    //MODIFIES: this, Exercise
    //EFFECTS: adds Exercise to exerciseList, and Workout to Exercise,
    // if it isn't in exerciseList already, otherwise do nothing
    public void addExercise(Exercise e) {
        if (!exerciseList.contains(e)) {
            exerciseList.add(e);
            e.addWorkout(this);
        }
    }

    //MODIFIES: this, Exercise
    //EFFECTS: if exerciseList contains e then remove e from exerciseList, and this from e,
    // ,otherwise do nothing
    public void removeExercise(Exercise e) {
        if (exerciseList.contains(e)) {
            exerciseList.remove(e);
            e.removeWorkout(this);
        }
    }

    public ArrayList<Exercise> getExerciseList() {
        return exerciseList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Workout workout = (Workout) o;
        return Objects.equals(category, workout.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category);
    }
}
