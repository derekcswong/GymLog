package ui;

import model.GymLog;
import model.WeightExercise;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDate;


//sound code https://stackoverflow.com/questions/6045384/playing-mp3-and-wav-in-java
//applause sound Recorded by Yannick Lemieux

public class AddExToWorkout {
    protected JPanel addWorkoutForm;
    private JTabbedPane tabbedPane1;
    private JTextField textFieldWeightName;
    private JButton addWeightExerciseButton;
    private JTextField textFieldCardioName;
    private JButton addCardioExerciseButton;
    private JSpinner spinnerDistance;
    private JSpinner spinnerTime;
    private JSpinner spinnerWeight;
    private JSpinner spinnerSets;
    private JSpinner spinnerReps;
    private JButton addButton;
    private SpinnerNumberModel decimalSpinnerModel;

    AddExToWorkout(LocalDate ld, GymLog gl) {
        addWeightExerciseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = gl.getGymLogKeyValue(ld).size();
                Workout w = gl.getGymLogKeyValue(ld).get(size - 1);
                w.addExercise(
                        new WeightExercise(textFieldWeightName.getText(),
                                (double) spinnerWeight.getValue(),
                                (int) spinnerSets.getValue(),
                                (int) spinnerReps.getValue()));
                playApplause();
                JOptionPane.showMessageDialog(addWorkoutForm, "Exercise Recorded.");
            }
        });
    }

    private void playApplause() {
        try {
            AudioInputStream audioInputStream = AudioSystem
                    .getAudioInputStream(new File("/Users/derek/CPSC210/project_n4q1b/SMALL_CROWD_APPLAUSE.wav")
                            .getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    private void createUIComponents() {
        decimalSpinnerModel = new SpinnerNumberModel(0.0, 0.0,1000.0,0.1);
        spinnerWeight = new JSpinner(decimalSpinnerModel);
        spinnerDistance = new JSpinner(decimalSpinnerModel);

    }
}
