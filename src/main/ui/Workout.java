package ui;

import model.CardioExercise;
import model.Exercise;
import model.WeightExercise;

import java.util.ArrayList;
import java.util.Objects;

public class Workout {
    private ArrayList<Exercise> exerciseList;
    private String category;

    public Workout(String cat) {
        this.category = cat;
        this.exerciseList = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds WeightExercise to exerciseList
    public void addExercise(WeightExercise e) {
        exerciseList.add(e);
    }

    //MODIFIES: this
    //EFFECTS: adds CardioExercise to exerciseList
    public void addExercise(CardioExercise e) {
        exerciseList.add(e);
    }

    //EFFECTS: returns formatted string of exercises in this.exerciseList
    public String workoutToString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Exercise e: exerciseList) {
            stringBuilder.append(e.exerciseToString())
                        .append("\n");
        }
        return stringBuilder.toString();
    }

    public void setExerciseList(ArrayList<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public String getCategory() {
        return category;
    }

    public int getNumberOfExercises() {
        return exerciseList.size();
    }

    public ArrayList<Exercise> getExerciseList() {
        return exerciseList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Workout workout = (Workout) o;
        return Objects.equals(category, workout.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category);
    }
}
