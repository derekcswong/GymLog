package ui;

import ui.Observable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Subject extends Observable {
    protected List<Observable> observers = new ArrayList<>();

    public void notifyObservers(LocalDate ld, String c) {
        for (Observable o: observers) {
            o.update(ld, c);
        }
    }
}
