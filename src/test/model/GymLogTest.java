package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.GymLog;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class GymLogTest {
    LocalDate ld;
    GymLog g1;
    ArrayList<Workout> workoutList;
    ArrayList<Exercise> exerciseList;
    Exercise e1;
    Workout w1;

    @BeforeEach
    public void runBefore() {
        ld = LocalDate.of(1000, 10, 10);
        g1 = new GymLog();
        workoutList = new ArrayList<>();
        exerciseList = new ArrayList<>();
        w1 = new Workout("a", exerciseList);
        e1 = new WeightExercise("a", 1, 1, 1);
        exerciseList.add(e1);
        workoutList.add(w1);
        g1.getGymLog().put(ld, workoutList);
    }

    @Test
    public void saveTest() throws IOException{
        g1.save("/Users/derek/CPSC210/project_n4q1b/outputTest");
        List<String> outputTestLines = Files.readAllLines(Paths.get("/Users/derek/CPSC210/project_n4q1b/outputTest"));
        List<String> saveTestLines = Files.readAllLines(Paths.get("/Users/derek/CPSC210/project_n4q1b/saveTest"));
        assertEquals(saveTestLines, outputTestLines);
    }

    @Test
    public void loadTest() {
        GymLog g2 = new GymLog();
        g2.load("/Users/derek/CPSC210/project_n4q1b/saveTest");
        assertEquals(g1.getGymLog(), g2.getGymLog());
    }
}
