package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WeightExerciseTest {

    WeightExercise w0;

    @BeforeEach
    public void runBefore() {
        w0 = new WeightExercise("squat", 100, 5, 5);
    }

    @Test
    public void changeName(){
        assertNotEquals(w0.getName(), "deadlift");
        w0.changeName("deadlift");
        assertEquals(w0.getName(), "deadlift");
    }

    @Test
    public void exerciseToString(){
        String w0String = ("\t" + "Exercise Name: " + "squat"
                + "\n\t\tWeight: " + "100.0"
                + "\n\t\tSets: " + "5"
                + "\n\t\tReps: " + "5");
        assertTrue(w0String.equals(w0.exerciseToString()));
    }
}