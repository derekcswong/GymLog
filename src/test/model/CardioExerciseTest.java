package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

public class CardioExerciseTest {

    CardioExercise c0;
    CardioExercise c1;

    @BeforeEach
    public void runBefore() {
        c0 = new CardioExercise("treadmill", 50, LocalTime.of(1,1,30));
    }

    @Test
    public void getPaceTest() {
        assertEquals(c0.getPace(), Math.round(50 / 61.5));
    }

    @Test
    public void copyTest() {
        c1 = c0.copy();
        assertEquals(c1.getName(),c0.getName());
        assertEquals(c1.getDistance(),c0.getDistance());
        assertEquals(c1.getTime(),c0.getTime());
    }

    @Test
    public void changeName(){
        assertNotEquals(c0.getName(), "Skipping Rope");
        c0.changeName("Skipping Rope");
        assertEquals(c0.getName(), "Skipping Rope");
    }

    @Test
    public void printExerciseDetails(){
        c0.printExerciseDetails();
    }
}
