package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WeightExerciseTest {

    WeightExercise w0;
    WeightExercise w1;

    @BeforeEach
    public void runBefore() {
        w0 = new WeightExercise("squat", 100, 5, 5);
    }

    @Test
    public void totalWeightLiftedTest() {
        assertEquals(w0.totalWeightLifted(), 100 * 5 * 5);
    }

    @Test
    public void copyTest() {
        w1 = w0.copy();
        assertEquals(w1.getName(),w0.getName());
        assertEquals(w1.getSets(),w0.getSets());
        assertEquals(w1.getWeight(),w0.getWeight());
        assertEquals(w1.getReps(),w0.getReps());

    }

    @Test
    public void changeName(){
        assertNotEquals(w0.getName(), "deadlift");
        w0.changeName("deadlift");
        assertEquals(w0.getName(), "deadlift");
    }

    @Test
    public void printExerciseDetails(){
        w0.printExerciseDetails();
    }
}