package model;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class GymLog implements Saveable, Loadable {
    private static final String CMD_NEW = "new";
    private static final String CMD_QUIT = "quit";
    private static final String CMD_VIEW = "view";
    private static final String CMD_ADD = "add";
    private static final String CMD_BACK = "back";
    private static final String CMD_CONTINUE = "continue";
    private static final String CMD_SAVE = "save";
    private static HashMap<LocalDate, ArrayList<Workout>> gymLog;
    private LocalDate date;
    private ArrayList<Exercise> exerciseList;
    private Scanner scanner = new Scanner(System.in);


    @Override
    public void save(String directory) {
        try (FileWriter writer = new FileWriter(directory)) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .enableComplexMapKeySerialization()
                    .create();
            gson.toJson(gymLog, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load(String directory) {
        try (FileReader reader = new FileReader(directory)) {
            Type mapType = new TypeToken<HashMap<LocalDate, ArrayList<Workout>>>() {
            }.getType();
            gymLog = new Gson().fromJson(reader, mapType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //EFFECTS: set is empty
    public GymLog() {
        gymLog = new HashMap<>();
    }

    //logEntries?
    //EFFECTS: takes user history then performs specific methods depending on history.
    public void handleInput() {
        while (true) {
            System.out.println(gymLog.entrySet());
            mainMenuCommands();
            String input = scanner.nextLine();
            if (input.equals(CMD_NEW)) {
                createWorkout();
            } else if (input.equals(CMD_QUIT)) {
                System.exit(0);
            } else if (input.equals(CMD_VIEW)) {
                System.out.println("You have gone to the gym " + this.size() + " days so far\n");
            } else if (input.equals(CMD_SAVE)) {
                save("/Users/derek/CPSC210/project_n4q1b/history");
            } else {
                System.out.println("Sorry, I didn't understand that command. Please try again.");
            }
        }
    }

    //MODIFIES: logEntries
    //EFFECTS: will create new Workouts with a Date and a category given user inputs
    private void createWorkout() {
        String c;
        date = askForDate();
        while (gymLog.containsKey(date)) {
            System.out.println("Enter '" + CMD_CONTINUE + "' to add another Workout to " + date);
            System.out.println("Enter '" + CMD_BACK + "' to go back to main menu.");
            String input = scanner.nextLine();
            if (input.equals(CMD_CONTINUE)) {
                c = askForCategory();
                addWorkout(date, c);
                break;
            } else if (input.equals(CMD_BACK)) {
                break;
            }
        }
        if (!gymLog.containsKey(date)) {
            c = askForCategory();
            addWorkout(date, c);
        }
    }

    //MODIFIES: this?? b/c it modifies date...
    //EFFECTS: asks user for date and returns in LocalDate format yyyy-mm-dd
    private LocalDate askForDate() {
        System.out.println("Enter a date 'yyyy-mm-dd':");
        date = null;
        while (date == null) {
            try {
                String d = scanner.nextLine();
                date = LocalDate.parse(d);
                break;
            } catch (Exception e) {
                System.out.println("Sorry, that is an invalid date. Please try again.");
            }
        }
        return date;
    }

    //EFFECTS: asks user for and returns Workout Category
    private String askForCategory() {
        System.out.println("Enter the category: ");
        String c = scanner.nextLine();
        return c;
    }

    //MODIFIES: this, workoutList?
    //EFFECTS: adds Workout to key ld in gymLog
    private void addWorkout(LocalDate ld, String c) {
        exerciseList = createExerciseList();
        ArrayList<Workout> workoutList = gymLog.get(ld);
        if (workoutList == null) {
            workoutList = new ArrayList<>();
            workoutList.add(new Workout(c, exerciseList));
            gymLog.put(ld, workoutList);
        } else {
            workoutList.add(new Workout(c, exerciseList));
        }
    }

    //EFFECTS: Exercise(s) are created with user inputs for weight, sets, reps, and name.
    //returns list of Exercises
    private ArrayList<Exercise> createExerciseList() {
        exerciseList = new ArrayList<>();
        while (true) {
            createExercisesCommands();
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
                return exerciseList;
            }
        }
    }

    //EFFECTS: returns the number of times that you've gone to the gym
    public int size() {
        return gymLog.size();
    }

    //EFFECTS: print commands for users to use GymLog
    private void mainMenuCommands() {
        System.out.println("Enter '" + CMD_NEW + "' to record a new entry.");
        System.out.println("Enter '" + CMD_QUIT + "' to leave.");
        System.out.println("Enter '" + CMD_VIEW + "' to find out how many days that you've gone to the gym.");
        System.out.println("Enter '" + CMD_SAVE + "' to save your gymLog.");
    }

    //EFFECTS: print commands when users adding exercises
    private void createExercisesCommands() {
        System.out.println("Enter '" + CMD_ADD + "' to add new exercise.");
        System.out.println("Enter '" + CMD_BACK + "' to go back to main menu.");
    }

    public static HashMap<LocalDate, ArrayList<Workout>> getGymLog() {
        return gymLog;
    }
}
