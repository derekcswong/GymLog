package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class WorkoutTest {
    Workout w1;
    Workout w2;
    ArrayList<Exercise> exList1;
    ArrayList<Exercise> exList2;
    ArrayList<Workout> workoutList;
    Exercise e1;
    Exercise e2;
    Exercise e3;

    @BeforeEach
    public void runBefore() {
        e1 = new WeightExercise("squat", 1, 1, 1);
        e2 = new CardioExercise("sprint", 10.0, LocalTime.of(10,10));
        e3 = new WeightExercise("deadlift", 1, 1, 1);
        exList1 = new ArrayList<> ();
        exList2 = new ArrayList<> ();
        exList1.add(e1);
        exList1.add(e2);
        exList2.add(e2);
        w1 = new Workout("Monday", exList1);
        w2 = new Workout("Tuesday", exList2);
        workoutList = new ArrayList<>();
        workoutList.add(w2);
        e3.setInWorkouts(workoutList);
    }

    @Test
    public void addExerciseTestAdded() {
        assertFalse(w1.getExerciseList().contains(e3));
        w1.addExercise(e3);
        assertTrue(w1.getExerciseList().contains(e3));
        assertTrue(e3.getInWorkouts().contains(w1));
    }

    @Test
    public void addExerciseTestNotAdded() {
        assertTrue(w1.getExerciseList().contains(e1));
        w1.addExercise(e1);
        assertFalse(w1.getExerciseList().contains(e3));
        assertFalse(e3.getInWorkouts().contains(w1));
    }

    @Test
    public void removeExerciseTestRemoved(){
        e2.setInWorkouts(workoutList);
        assertTrue(w2.getExerciseList().contains(e2));
        assertTrue(e2.getInWorkouts().contains(w2));
        w2.removeExercise(e2);
        assertFalse(w2.getExerciseList().contains(e2));
        assertFalse(e2.getInWorkouts().contains(w2));
    }

    @Test
    public void removeExerciseTestNotRemoved(){
        assertFalse(w1.getExerciseList().contains(e3));
        assertFalse(e3.getInWorkouts().contains(w1));
        w1.removeExercise(e3);
        assertFalse(w1.getExerciseList().contains(e3));
        assertFalse(e3.getInWorkouts().contains(w1));
    }

}
