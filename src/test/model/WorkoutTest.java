package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Workout;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;


public class WorkoutTest {

    Workout wk0;
    Workout wk1;
    WeightExercise w0;
    WeightExercise w1;
    CardioExercise c0;
    CardioExercise c1;

    @BeforeEach
    public void runBefore() {
        c0 = new CardioExercise("treadmill", 50, LocalTime.of(1, 1, 30));
        w0 = new WeightExercise("squat", 100, 5, 5);
        c1 = new CardioExercise("elliptical", 6, LocalTime.of(1, 5, 30));
        w1 = new WeightExercise("deadlift", 1, 1, 1);
        wk0 = new Workout("legs");
        wk1 = new Workout("mixed");
        wk0.addExercise(w0);
        wk0.addExercise(c0);
    }

    @Test
    public void addWeightExerciseToEmptyTest() {
        assertEquals(wk1.getNumberOfExercises(), 0);
        wk1.addExercise(w0);
        assertEquals(wk1.getNumberOfExercises(), 1);
        assertEquals(wk1.getExerciseList().indexOf(w0), 0);
    }

    @Test
    public void addCardioExerciseToEmptyTest() {
        assertEquals(wk1.getNumberOfExercises(), 0);
        wk1.addExercise(c0);
        assertEquals(wk1.getNumberOfExercises(), 1);
        assertEquals(wk1.getExerciseList().indexOf(c0), 0);
    }

    @Test
    public void addExerciseTest() {
        wk1.addExercise(w0);
        assertEquals(wk1.getNumberOfExercises(), 1);
        wk1.addExercise(w1);
        wk1.addExercise(c0);
        wk1.addExercise(c1);
        assertEquals(wk1.getNumberOfExercises(), 4);
        assertEquals(wk1.getExerciseList().indexOf(w0), 0);
        assertEquals(wk1.getExerciseList().indexOf(w1), 1);
        assertEquals(wk1.getExerciseList().indexOf(c0), 2);
        assertEquals(wk1.getExerciseList().indexOf(c1), 3);
    }

    @Test
    public void workoutToStringTest() {
        String w0String =
                ("\t" + "Exercise Name: " + "squat"
                        + "\n\t\tWeight: " + "100.0"
                        + "\n\t\tSets: " + "5"
                        + "\n\t\tReps: " + "5" + "\n"
                        + "\t" + "Exercise Name: " + "treadmill"
                        + "\n\t\tDistance: " + "50.0"
                        + "\n\t\tTime: " + "01:01:30" + "\n");
        assertTrue(w0String.equals(wk0.workoutToString()));
    }
}
