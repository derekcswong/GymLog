package ui;

import network.ReadWebPage;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ReadWebPage rwp = new ReadWebPage();
        GymLog g = new GymLog();
        g.load("/Users/derek/CPSC210/project_n4q1b/history");
        g.handleInput();
    }
}

