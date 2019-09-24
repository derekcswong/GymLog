package model;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Scanner;

public class LogEntry {
    private static final String CMD_ADD = "add";
    private static final String CMD_BACK = "back";

    private LocalDate date;
    private ArrayList<Exercise> exerciseList;
    private String category;
    private Scanner scanner = new Scanner(System.in);

    //EFFECTS: set the date to date, set the category to cat, set the exerciseList to
    //an ArrayList, run addExercises() method
    public LogEntry(LocalDate date, String cat) {
        this.date = date;
        this.category = cat;
        this.exerciseList = new ArrayList<>();
    }

    //EFFECTS: based on user input and Exercise is created with user inputs for weight,
    //sets, reps, and name
    public void createExercises() {
        while (true) {
            System.out.println("Enter '" + CMD_ADD + "' to add new exercise.");
            System.out.println("Enter '" + CMD_BACK + "' to go back to main menu.");
            String input2 = scanner.nextLine();
            if (input2.equals(CMD_ADD)) {
                System.out.println("Enter new exercise name: ");
                String name = scanner.nextLine();
                System.out.println("Weight (lbs): ");
                int weight = Integer.parseInt(scanner.nextLine());
                System.out.println("How many sets: ");
                int sets = Integer.parseInt(scanner.nextLine());
                System.out.println("How many reps: ");
                int reps = Integer.parseInt(scanner.nextLine());
                exerciseList.add(new Exercise(name, weight, sets, reps));
            } else if (input2.equals(CMD_BACK)) {
                break;
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: adds Exercise to exerciseList
    public void addExercise(String name, int weight, int reps, int sets) {
        exerciseList.add(new Exercise(name, weight, reps, sets));
    }

    //EFFECTS: returns the number of exercises in this workout
    public int numOfExercises() {
        return exerciseList.size();
    }

    //EFFECTS: returns date for this LogEntry
    public LocalDate getDate() {
        return date;
    }
}
