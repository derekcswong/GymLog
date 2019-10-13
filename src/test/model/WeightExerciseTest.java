package model;

import exceptions.NegativeWeightException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WeightExerciseTest {

    WeightExercise w0;
    WeightExercise w1;
    WeightExercise w2;

    @BeforeEach
    public void runBefore() {
        w0 = new WeightExercise("squat", 100, 5, 5);
    }

    @Test
    public void totalWeightLiftedTestNothingThrown() {
        try {
           w0.totalWeightLifted();
           assertEquals(w0.totalWeightLifted(), 100 * 5 * 5);
        } catch (NegativeWeightException e) {
            fail("caught NegativeWeight Exception");
        }
    }

    @Test
    //expecting negative weight exception
    public void negativeWeightExceptionTest() {
        w2 = new WeightExercise("deadlift", -2, -2, -2);
        try {
            w2.totalWeightLifted();
            fail("negative weight exception not thrown");
        } catch (NegativeWeightException e) {}
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
    public void exerciseDetailsString(){
        String w0String = ("name: " + "squat"
                + "\nweight: " + "100"
                + "\nsets: " + "5"
                + "\nreps: " + "5");
        assertTrue(w0String.equals(w0.exerciseDetailsString()));
    }
}