package ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Observable {

    public void update(LocalDate ld, String category) {
        System.out.println(category + " workout has been created on "
                + ld.format(DateTimeFormatter.ofPattern("d MMM uuuu")));
    }
}
