package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.GymLog;
import ui.Workout;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class GymLogTest {
    LocalDate ld;
    LocalDate ld1;
    GymLog g1;
    GymLog g0;
    Exercise e1;
    ArrayList<Workout> workoutList;
    ArrayList<Exercise> exerciseList;
    Workout w1;

    @BeforeEach
    public void runBefore() {
        ld = LocalDate.of(1000, 10, 10);
        ld1 = LocalDate.of(2019, 1, 1);
        g0 = new GymLog();
        g1 = new GymLog();
        e1 = new WeightExercise("a", 1, 1, 1);
        workoutList = new ArrayList<>();
        exerciseList = new ArrayList<>();
        exerciseList.add(e1);
        w1 = new Workout("a");
        w1.setExerciseList(exerciseList);
        workoutList.add(w1);
        g1.getGymLog().put(ld, workoutList);
    }

    @Test
    public void addWorkoutNullTest() {
        assertEquals(g0.getGymLogKeyValue(ld), null);
        g0.addWorkout(ld, "a");
        assertEquals(g0.getGymLogKeyValue(ld).size(), 1);
        assertEquals(g0.getGymLogKeyValue(ld).get(0), new Workout("a"));
    }

    @Test
    public void addWorkoutTest() {
        g0.addWorkout(ld, "a");
        assertEquals(g0.getGymLogKeyValue(ld).size(), 1);
        assertEquals(g0.getGymLogKeyValue(ld).get(0), new Workout("a"));
        g0.addWorkout(ld, "b");
        g0.addWorkout(ld1, "c");
        assertEquals(g0.getGymLogKeyValue(ld).size(), 2);
        assertEquals(g0.getGymLogKeyValue(ld1).size(), 1);
        assertEquals(g0.getGymLogKeyValue(ld).get(0), new Workout("a"));
        assertEquals(g0.getGymLogKeyValue(ld).get(1), new Workout("b"));
        assertEquals(g0.getGymLogKeyValue(ld1).get(0), new Workout("c"));

    }

    @Test void workoutListToStringTest() {
        String g1String = ("a Workout:\n" + w1.workoutToString());
        assertTrue(g1String.equals(g1.workoutListToString(ld)));
    }

    @Test void workoutListToStringNullPointerExecption() {
        String g0String = ("No Workouts Recorded.");
        assertTrue(g0String.equals(g0.workoutListToString(ld)));
    }

    @Test
    public void saveTest(){
        g1.save("/Users/derek/CPSC210/project_n4q1b/outputTest");
        List<String> outputTestLines = null;
        try {
            outputTestLines = Files.readAllLines(Paths.get("/Users/derek/CPSC210/project_n4q1b/outputTest"));
        } catch (IOException e) {
            fail();
        }
        List<String> saveTestLines = null;
        try {
            saveTestLines = Files.readAllLines(Paths.get("/Users/derek/CPSC210/project_n4q1b/saveTest"));
        } catch (IOException e) {
            fail();
        }
        assertEquals(saveTestLines, outputTestLines);
    }

    @Test
    public void loadTest() {
        GymLog g2 = new GymLog();
        g2.load("/Users/derek/CPSC210/project_n4q1b/saveTest");
        assertEquals(g1.getGymLog(), g2.getGymLog());
    }
}
