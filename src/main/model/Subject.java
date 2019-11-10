package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Subject extends model.Observable {
    protected List<Observable> observers = new ArrayList<>();

    public void notifyObservers(LocalDate ld, String c) {
        for (Observable o: observers) {
            o.update(ld, c);
        }
    }
}
