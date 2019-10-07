package model;

public class WeightExercise extends Exercise {
    private int sets;
    private int weight;
    private int reps;

    public WeightExercise(String name, int w, int s, int r) {
        super(name);
        this.weight = w;
        this.sets = s;
        this.reps = r;
    }

    public int totalWeightLifted() {
        return weight * reps * sets;
    }

    @Override
    public void printExerciseDetails() {
        System.out.println(name + "weight: " + weight + "sets: " + sets + "reps: " + reps);
    }

    @Override
    public WeightExercise copy() {
        return new WeightExercise(name, weight, sets, reps);
    }

    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }

    public int getWeight() {
        return weight;
    }
}
