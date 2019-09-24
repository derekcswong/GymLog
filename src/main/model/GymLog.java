package model;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class GymLog {
    private static final String CMD_NEW = "new";
    private static final String CMD_QUIT = "quit";
    private static final String CMD_VIEW = "view";

    private ArrayList<LogEntry> logEntryList;
    private Scanner scanner = new Scanner(System.in);

    //EFFECTS: set is empty
    public GymLog() {
        logEntryList = new ArrayList<>();
    }

    //logEntries?
    //EFFECTS: takes user input and print instructions, then performs specific methods
    // depending on input.
    public void handleInput() {
        while (true) {
            printCommands();
            String input = scanner.nextLine();
            if (input.equals(CMD_NEW)) {
                createLogEntry();
            } else if (input.equals(CMD_QUIT)) {
                break;
            } else if (input.equals(CMD_VIEW)) {
                System.out.println("You have been to the gym " + this.size() + " times\n");
            } else {
                System.out.println("Sorry, I didn't understand that command. Please try again.");
            }
        }
    }

    //EFFECTS: print commands for users to use GymLog
    private void printCommands() {
        System.out.println("Enter '" + CMD_NEW + "' to record a new entry.");
        System.out.println("Enter '" + CMD_QUIT + "' to leave.");
        System.out.println("Enter '" + CMD_VIEW + "' to find out how many times you've been to the gym.");
    }

    //MODIFIES: logEntries
    //EFFECTS: will create new LogEntry with a Date and a category given user input
    private void createLogEntry() {
        System.out.println("Enter a date 'YYYY-MM-DD':");
        String d = scanner.nextLine();
        LocalDate ld = LocalDate.parse(d);
        while (logEntryListHaveDate(ld)) {
            System.out.println("Log Already Exists:");
            System.out.println("Enter a date 'YYYY-MM-DD':");
            d = scanner.nextLine();
            ld = LocalDate.parse(d);
        }
        System.out.println("Enter the category: ");
        String c = scanner.nextLine();
        addLogEntry(ld, c);
    }

    //MODIFIES: this
    //EFFECTS: adds LogEntry to GymLog
    public void addLogEntry(LocalDate ld, String c) {
        logEntryList.add(new LogEntry(ld, c));
    }

    //EFFECTS: returns true if a LogEntry in logEntries has date equal to LocalDate d, otherwise returns false
    public boolean logEntryListHaveDate(LocalDate d) {
        for (LogEntry l : this.logEntryList) {
            if (l.getDate().equals(d)) {
                return true;
            } else {
                break;
            }
        }
        return false;
    }

    //EFFECTS: returns the number of times that you've gone to the gym
    public int size() {
        return logEntryList.size();
    }

}
