package model;

import ui.StatTracker;
import ui.Workout;
import ui.gsonadapters.AbstractExerciseAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

//citation: https://www.baeldung.com/gson-json-to-map,
// http://www.studytrails.com/java/json/java-google-json-custom-serializer-deserializer/

public class GymLog extends Observable implements Saveable, Loadable {
    private HashMap<LocalDate, ArrayList<Workout>> gymLog;

    public GymLog() {
        gymLog = new HashMap<>();
        StatTracker tracker = new StatTracker();
        addObserver(tracker);
    }

    //MODIFIES: this
    //EFFECTS: adds empty arrayList<Workout> to gymLog if hashmap currently holds null,
    // then adds a new Workout into gymLog
    public void addWorkout(LocalDate ld, String category) {
        if (gymLog.get(ld) == null) {
            gymLog.put(ld, new ArrayList<>());
            gymLog.get(ld).add(new Workout(category));
        } else {
            gymLog.get(ld).add(new Workout(category));
        }
    }

    //EFFECTS: returns a formatted string of Workouts in GymLog.get(key)
    public String workoutListToString(LocalDate key) {
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<Workout> workoutList = gymLog.get(key);
        try {
            if (workoutList.size() != 0) {
                for (Workout w : workoutList) {
                    stringBuilder.append(w.getCategory())
                            .append(" Workout:\n")
                            .append(w.workoutToString());
                }
            }
        } catch (NullPointerException e) {
            stringBuilder.append("No Workouts Recorded.");
        }
        return stringBuilder.toString();
    }

    public HashMap<LocalDate, ArrayList<Workout>> getGymLog() {
        return gymLog;
    }

    public ArrayList<Workout> getGymLogKeyValue(LocalDate ld) {
        return gymLog.get(ld);
    }

    @Override
    //EFFECTS: saves gymLog as json
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
    //EFFECTS: takes json file and converts it into gymLog format HashMap<LocalDate, ArrayList<Workout>>
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
