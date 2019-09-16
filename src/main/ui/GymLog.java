package ui;

import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;

public class GymLog {
    private ArrayList<LogEntry> logEntries;
    private Scanner scanner;
    private LocalDate date;
    private String cmdNew = "new";
    private String cmdQuit = "quit";

    public GymLog() {
        logEntries = new ArrayList<>();
        scanner = new Scanner(System.in);
        handleInput();
    }

    public void handleInput() {
        while (true) {
            System.out.println("Enter '" + cmdNew + "' to record a new entry.");
            System.out.println("Enter '" + cmdQuit + "' to leave.");
            String input = scanner.nextLine();
            if (input.equals(cmdNew)) {
                System.out.println("Enter the date 'YYYY-MM-DD':");
                String d = scanner.nextLine();
                LocalDate date = LocalDate.parse(d);
                logEntries.add(new LogEntry(date));
                System.out.println("The number of entries is " + logEntries.size());
            }
            if (input.equals(cmdQuit)) {
                System.out.println("Please enter your name:");
                String nm = scanner.nextLine();
                System.out.println(quitProgram(nm));
                break;
            }
        }
    }

    public String quitProgram(String nm) {
        return ("Thank you for your time, " + nm);
    }

    public static void main(String[] args) {
        new GymLog();
    }

}

