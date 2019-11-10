package ui;


import model.Subject;
import ui.exceptions.NotAnExerciseTypeException;
import model.CardioExercise;
import model.Exercise;
import model.WeightExercise;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Workout {
    private static final String CMD_ADD_CARDIO = "cardio";
    private static final String CMD_ADD_WEIGHT = "weight";
    private static final String CMD_BACK = "back";
    private ArrayList<Exercise> exerciseList;
    private String category;
    private transient Scanner scanner = new Scanner(System.in);

    public Workout(String cat) {
        this.category = cat;
        this.exerciseList = new ArrayList<>();
    }

    //MODIFIES: this, Exercise
    //EFFECTS: adds Exercise to exerciseList, and Workout to Exercise,
    // if it isn't in exerciseList already, otherwise do nothing
    public void addExercise() throws NotAnExerciseTypeException {
        while (true) {
            createExercisesCommands();
            String input2 = scanner.nextLine();
            if (input2.equals(CMD_ADD_CARDIO)) {
                exerciseList.add(askForCardioExercise());
            } else if (input2.equals(CMD_ADD_WEIGHT)) {
                exerciseList.add(askForWeightExercise());
            } else if (input2.equals(CMD_BACK)) {
                return;
            } else {
                throw new NotAnExerciseTypeException();
            }
        }
    }

    //EFFECTS: print commands when users adding exercises
    private void createExercisesCommands() {
        System.out.println("Enter '" + CMD_ADD_CARDIO + "' to add new cardio exercise.");
        System.out.println("Enter '" + CMD_ADD_WEIGHT + "' to add new weight exercise.");
        System.out.println("Enter '" + CMD_BACK + "' to return to main menu.");
    }

    //EFFECTS: ask user for CardioExercise details and returns CardioExercise
    private CardioExercise askForCardioExercise() {
        while (true) {
            System.out.println("Enter exercise name: ");
            String name = scanner.nextLine();
            try {
                System.out.println("Distance Travelled (meters): ");
                double distance = Double.parseDouble(scanner.nextLine());
                System.out.println("Time in (HH:MM:SS) ");
                LocalTime time = LocalTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("HH:mm:ss"));
                return new CardioExercise(name, distance, time);
            } catch (NumberFormatException nfe) {
                System.out.println("Not a valid number. Try Again.");
            } catch (DateTimeParseException dtpe) {
                System.out.println("Not a valid time. Try Again.");
            }
        }
    }

    //EFFECTS: ask user for WeightExercise details and returns WeightExercise
    private WeightExercise askForWeightExercise() {
        while (true) {
            System.out.println("Enter exercise name: ");
            String name = scanner.nextLine();
            try {
                System.out.println("Weight (lbs): ");
                int weight = Integer.parseInt(scanner.nextLine());
                System.out.println("How many sets: ");
                int sets = Integer.parseInt(scanner.nextLine());
                System.out.println("How many reps: ");
                int reps = Integer.parseInt(scanner.nextLine());
                return new WeightExercise(name, weight, sets, reps);
            } catch (NumberFormatException nfe) {
                System.out.println("Not a valid number. Try again");
            }
        }
    }

    public void setExerciseList(ArrayList<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Workout workout = (Workout) o;
        return Objects.equals(category, workout.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category);
    }
}
