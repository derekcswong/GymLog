package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExerciseTest {

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
        w2 = new Workout("Tuesday", exList2);
        w1 = new Workout("Monday", exList1);
        workoutList = new ArrayList<>();
        workoutList.add(w2);
        e3.setInWorkouts(workoutList);
    }

    @Test
    public void addWorkoutTestAdded() {
        assertFalse(e3.getInWorkouts().contains(w1));
        assertFalse(w1.getExerciseList().contains(e3));
        e3.addWorkout(w1);
        assertTrue(e3.getInWorkouts().contains(w1));
        assertTrue(w1.getExerciseList().contains(e3));
    }

    @Test
    public void addWorkoutTestNotAdded() {
        assertTrue(e3.getInWorkouts().contains(w2));
        assertFalse(w2.getExerciseList().contains(e3));
        e3.addWorkout(w2);
        assertTrue(e3.getInWorkouts().contains(w2));
        assertFalse(w2.getExerciseList().contains(e3));
    }

    @Test
    public void removeWorkoutTestRemoved(){
        exList2.add(e3);
        assertTrue(e3.getInWorkouts().contains(w2));
        assertTrue(w2.getExerciseList().contains(e3));
        e3.removeWorkout(w2);
        assertFalse(e3.getInWorkouts().contains(w2));
        assertFalse(w2.getExerciseList().contains(e3));
    }

    @Test
    public void removeWorkoutTestNotRemoved(){
        assertFalse(e3.getInWorkouts().contains(w1));
        assertFalse(w1.getExerciseList().contains(e3));
        e3.removeWorkout(w1);
        assertFalse(e3.getInWorkouts().contains(w1));
        assertFalse(w1.getExerciseList().contains(e3));
    }
}
