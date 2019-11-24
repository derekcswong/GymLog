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

    //EFFECTS: convert exercise details to string
    public abstract String exerciseToString();

    public String getName() {
        return name;
    }


}
