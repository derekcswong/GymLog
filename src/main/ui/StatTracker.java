package ui;

import model.Exercise;
import ui.GymLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

//should i make this abstract and extend a cardio stat tracker and a weight exercise stat tracker?

public class StatTracker extends Observable {
    private HashMap<Exercise, ArrayList<Integer>> statTracker; //use this to hold all the totalweight lifted and then graph by day?
// how should i think about this here?

    public StatTracker() {
        statTracker = new HashMap<>();
    }

    public void update() {
        System.out.println("exercise has been added");
    }
}
