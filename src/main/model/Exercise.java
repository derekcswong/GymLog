package model;

public class Exercise {

    private String name;
    private int sets;
    private int weight;
    private int reps;

    public Exercise(String name, int weight, int sets, int reps) {
        this.name = name;
        this.weight = weight;
        this.sets = sets;
        this.reps = reps;
    }

    public int totalWeightLifted() {
        return this.weight * sets * reps;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getReps() {
        return reps;
    }

    public int getSets() {
        return sets;
    }
}
