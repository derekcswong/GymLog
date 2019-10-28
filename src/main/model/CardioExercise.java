package model;

import java.time.LocalTime;

public class CardioExercise extends Exercise {

    private double distance; //in meters?
    private LocalTime time;

    public CardioExercise(String name, double d, LocalTime lt) {
        super(name);
        this.distance = d;
        this.time = lt;
    }

    //REQUIRES: time != 0:0:0
    //EFFECTS: returns distance (in meters?) per minute
    public double getPace() {
        double totalminutes = time.getHour() * 60 + time.getMinute() + time.getSecond() / 60;
        return Math.round(distance / totalminutes);
    }

    @Override
    public String exerciseDetailsString() {
        return ("name: " + name
                + "\nweight: " + distance
                + "\nsets: " + time.toString());
    }

    @Override
    public CardioExercise copy() {
        return new CardioExercise(name, distance, time);
    }

    public double getDistance() {
        return distance;
    }

    public LocalTime getTime() {
        return time;
    }
}
