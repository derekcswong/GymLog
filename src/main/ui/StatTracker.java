package ui;

import model.Exercise;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;


public class StatTracker implements Observer {
    private HashMap<Exercise, ArrayList<Integer>> statTracker;
    //use this to hold all the totalweight lifted and then graph by day?
// how should i think about this here?

    public StatTracker() {
        statTracker = new HashMap<>();
    }

    @Override
    //EFFECTS: prints message when workout has been added into gymLog
    public void update(Observable o, Object arg) {
        LocalDate ld = (LocalDate) arg;
        System.out.println("Workout has been created on "
                + ld.format(DateTimeFormatter.ofPattern("d MMM uuuu")));
    }
}
