package model;

public class WeightExercise extends Exercise {
    private int sets;
    private double weight;
    private int reps;

    public WeightExercise(String name, double w, int s, int r) {
        super(name);
        this.weight = w;
        this.sets = s;
        this.reps = r;
    }

    @Override
    public String exerciseToString() {
        return ("\t" + "Exercise Name: " + name
                + "\n\t\tWeight: " + weight
                + "\n\t\tSets: " + sets
                + "\n\t\tReps: " + reps);
    }

}
