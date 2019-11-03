package model;

import ui.exceptions.NegativeWeightException;

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

    public int totalWeightLifted() throws NegativeWeightException {
        int totalWeight = weight * reps * sets;
        if (totalWeight < 0) {
            throw new NegativeWeightException();
        } else {
            return totalWeight;
        }
    }


    @Override
    public String exerciseDetailsString() {
        return ("name: " + name
                + "\nweight: " + weight
                + "\nsets: " + sets
                + "\nreps: " + reps);
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
