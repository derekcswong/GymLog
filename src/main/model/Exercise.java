package model;

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

    //EFFECTS: prints exercise details
    public abstract void printExerciseDetails();

    //EFFECTS: instantiates new exercise with the same values and returns it
    public abstract Exercise copy();

    public String getName() {
        return name;
    }
}
