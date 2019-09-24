package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


public class LogEntryTest {
    private LogEntry logEntry;
    private final static LocalDate d0 = LocalDate.parse("1999-12-11");

    @BeforeEach
    public void runBefore(){
        logEntry = new LogEntry(d0, "legs");
    }

    @Test
    public void testNumOfExercises() {
        assertEquals(logEntry.numOfExercises(), 0);

        logEntry.addExercise("squats", 10, 10, 10);
        logEntry.addExercise("deadlift", 10, 10, 10);
        logEntry.addExercise("legpress", 100, 15, 8);

        assertEquals(logEntry.numOfExercises(), 3);
    }


}