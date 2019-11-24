package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

public class CardioExerciseTest {

    CardioExercise c0;

    @BeforeEach
    public void runBefore() {
        c0 = new CardioExercise("treadmill", 50, LocalTime.of(1,1,30));
    }

    @Test
    public void getPaceTest() {
        assertEquals(c0.getPace(), Math.round(50 / 61.5));
    }

    @Test
    public void changeName(){
        assertNotEquals(c0.getName(), "Skipping Rope");
        c0.changeName("Skipping Rope");
        assertEquals(c0.getName(), "Skipping Rope");
    }

    @Test
    public void exerciseToString(){
        String c0String = ("\t" + "Exercise Name: " + "treadmill"
                + "\n\t\tDistance: " + "50.0"
                + "\n\t\tTime: " + "01:01:30");
        assertTrue(c0String.equals(c0.exerciseToString()));
    }
}
