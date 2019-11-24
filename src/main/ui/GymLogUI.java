package ui;

import model.GymLog;
import ui.network.ReadWebPage;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Properties;

//Code in setNplaceDatePicker borrowed from S/O

public class GymLogUI {
    private GymLog gl;
    private JButton addWorkoutButton;
    private JButton removeWorkoutButton;
    private JButton changeWorkoutButton;
    private JButton viewButton;
    private JButton saveButton;
    private JPanel mainPanel;
    private JLabel quoteDay;
    private JPanel calendarPanel;
    private JTextPane display;
    private ReadWebPage rwp;
    private JDatePickerImpl datePicker;
    private String category;

    private GymLogUI() {
        gl = new GymLog();
//        gl.load("/Users/derek/CPSC210/project_n4q1b/history");
        setQuoteOfTheDay();
        datePicker();
        addWorkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                category = JOptionPane.showInputDialog("Workout Category:");
                if (category != null) {
                    if (!(category.isEmpty())) {
                        gl.addWorkout(getDateFromDatePicker(), category);
                        showAddWorkoutFrame();
                    }
                }
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                gl.save("/Users/derek/CPSC210/project_n4q1b/history");
            }
        });
    }

    private void showAddWorkoutFrame() {
        JFrame addWorkoutFrame = new JFrame("Add Exercises");
        addWorkoutFrame.setContentPane(new AddExToWorkout(getDateFromDatePicker(), gl).addWorkoutForm);
        addWorkoutFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                addWorkoutFrame.setDefaultCloseOperation(addWorkoutFrame.HIDE_ON_CLOSE);
                JOptionPane.showMessageDialog(addWorkoutFrame, category + " workout recorded on: "
                        + getDateFromDatePicker());
            }
        });
        addWorkoutFrame.pack();
        addWorkoutFrame.setVisible(true);
    }

    private LocalDate convertDateLocalDate(Date selectedDate) {
        Instant instant = Instant.ofEpochMilli(selectedDate.getTime());
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDate localDate = localDateTime.toLocalDate();
        return localDate;
    }

    private void datePicker() {
        setNplaceDatePicker();
        datePicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.setText(gl.workoutListToString(getDateFromDatePicker()));
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Your Personal Gym Log");
        frame.setContentPane(new GymLogUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private LocalDate getDateFromDatePicker() {
        return convertDateLocalDate((Date) datePicker.getModel().getValue());
    }

    private void setNplaceDatePicker() {
        Properties p = new Properties();
        p.put("text.today", "Today");
//        p.put("text.month", "Month");
//        p.put("text.year", "Year");
        UtilDateModel model = new UtilDateModel();
        Date today = new Date(System.currentTimeMillis());
        model.setValue(today);
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
        datePicker.setTextEditable(true);
        calendarPanel.add(datePicker);
    }

    private void setQuoteOfTheDay() {
        try {
            rwp = new ReadWebPage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        quoteDay.setText(rwp.getQuoteString());
    }

}
