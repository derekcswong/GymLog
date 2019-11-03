package ui;

import exceptions.NotAnExerciseTypeException;
import exceptions.NotCommandException;
import gsonadapters.AbstractExerciseAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

//citation: https://www.baeldung.com/gson-json-to-map,
// http://www.studytrails.com/java/json/java-google-json-custom-serializer-deserializer/

public class GymLog implements Saveable, Loadable {
    private static final String CMD_NEW = "new";
    private static final String CMD_QUIT = "quit";
    private static final String CMD_VIEW = "view";
    private static final String CMD_CONTINUE = "continue";
    private static final String CMD_SAVE = "save";
    private static HashMap<LocalDate, ArrayList<Workout>> gymLog;
    private LocalDate date;
    private Scanner scanner = new Scanner(System.in);

    public GymLog() {
        gymLog = new HashMap<>();
    }

    //EFFECTS: takes user history then performs specific methods depending on history.
    public void handleInput() {
        while (true) {
            mainMenuCommands();
            String input = scanner.nextLine();
            if (input.equals(CMD_NEW)) {
                try {
                    createWorkout();
                } catch (NotCommandException e) {
                    System.out.println("Not a Command!");
                }
            } else if (input.equals(CMD_QUIT)) {
                System.exit(0);
//            } else if (input.equals(CMD_VIEW)) {
//                System.out.println("You have gone to the gym " + this.size() + " days so far\n");
            } else if (input.equals(CMD_SAVE)) {
                save("/Users/derek/CPSC210/project_n4q1b/history");
            } else {
                System.out.println("Sorry, I didn't understand that command. Please try again.");
            }
        }
    }

//    public void removeWorkout(LocalDate ld, )


    //MODIFIES: logEntries
    //EFFECTS: will create new Workouts with a Date and a category given user inputs
    private void createWorkout() throws NotCommandException {
        date = askForDate();
        //doesn't loop ever??
        while (gymLog.containsKey(date)) {
            System.out.println("Enter '" + CMD_CONTINUE + "' to add another Workout to " + date);
            String input = scanner.nextLine();
            if (input.equals(CMD_CONTINUE)) {
                addWorkout(date);
                break;
            } else {
                throw new NotCommandException();
            }
        }
        if (!gymLog.containsKey(date)) {
            gymLog.put(date, new ArrayList<>());
            addWorkout(date);
        }
    }

    //MODIFIES: this, workoutList?
    //EFFECTS: adds Workout to key ld in gymLog
    private void addWorkout(LocalDate ld) {
        Workout nw = new Workout(askForCategory());
        if (gymLog.get(ld).contains(nw)) {
            System.out.println("workout already exists on this day");
            //want to make any changes to existing workout??
        } else {
            try {
                nw.addExercise();
                gymLog.get(ld).add(nw);
            } catch (NotAnExerciseTypeException e) {
                System.out.println("Not an Exercise Type.");
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: asks user for date and returns in LocalDate format yyyy-mm-dd
    private LocalDate askForDate() {
        System.out.println("Enter a date 'yyyy-mm-dd':");
        date = null;
        while (date == null) {
            try {
                String d = scanner.nextLine();
                date = LocalDate.parse(d);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Sorry, that is an invalid date. Please try again.");
            }
        }
        return date;
    }

    //EFFECTS: asks user for and returns Workout Category
    private String askForCategory() {
        System.out.println("Enter the category: ");
        return scanner.nextLine();
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

    public static HashMap<LocalDate, ArrayList<Workout>> getGymLog() {
        return gymLog;
    }

    @Override
    public void save(String directory) {
        try (FileWriter writer = new FileWriter(directory)) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .enableComplexMapKeySerialization()
                    .registerTypeAdapter(Exercise.class, new AbstractExerciseAdapter())
                    .create();
            gson.toJson(this.gymLog, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load(String directory) {
        try (FileReader reader = new FileReader(directory)) {
            Type mapType = new TypeToken<HashMap<LocalDate, ArrayList<Workout>>>() {
            }.getType();
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Exercise.class, new AbstractExerciseAdapter())
                    .create();
            gymLog = gson.fromJson(reader, mapType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
