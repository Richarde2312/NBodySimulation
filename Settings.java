

import NBobodySimulation.SimulationSettings;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Settings extends JFrame implements ActionListener {
    private JPanel settingsPanel;
    private JCheckBox runInfinitelyCheckBox;
    private JCheckBox showParticlesCheckBox;
    private JCheckBox showCenterOfGravityCheckBox;
    private JSlider simulationSpeedSlider;
    private JTextArea numberOfStepsTextArea;
    private JTextArea timeStepTextArea;
    private JButton applyButton;
    private JButton backButton;
    private Demo demo;

    public Settings(Demo demo) {
        this.demo = demo;
        setTitle("Settings Menu");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        settingsPanel = new JPanel(new GridBagLayout());
        add(settingsPanel);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);  

        runInfinitelyCheckBox = new JCheckBox("Run Infinitely");
        showParticlesCheckBox = new JCheckBox("Show Particles");
        showCenterOfGravityCheckBox = new JCheckBox("Show Center of Gravity");

        showParticlesCheckBox.setSelected(demo.settings.showTrail());
        showCenterOfGravityCheckBox.setSelected(demo.settings.showCenterOfGravity());
        runInfinitelyCheckBox.setSelected(demo.settings.isInfinite());

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.weightx = 1;
        settingsPanel.add(runInfinitelyCheckBox, constraints);

        constraints.gridy = 1;
        settingsPanel.add(showParticlesCheckBox, constraints);

        constraints.gridy = 2;
        settingsPanel.add(showCenterOfGravityCheckBox, constraints);

        constraints.gridy = 3;
        constraints.gridwidth = 1;
        settingsPanel.add(new JLabel("Simulation Speed:"), constraints);

        constraints.gridx = 1;
        simulationSpeedSlider = new JSlider(JSlider.HORIZONTAL, 1, 91, (int)(demo.settings.getSimulationSpeed() * 10));
        simulationSpeedSlider.setMajorTickSpacing(10);
        simulationSpeedSlider.setMinorTickSpacing(1);
        simulationSpeedSlider.setPaintTicks(true);
        simulationSpeedSlider.setPaintLabels(true);
        settingsPanel.add(simulationSpeedSlider, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        settingsPanel.add(new JLabel("Number of Steps:"), constraints);

        constraints.gridx = 1;
        numberOfStepsTextArea = new JTextArea(String.valueOf(demo.settings.getSkipAhead()), 1, 10);
        settingsPanel.add(numberOfStepsTextArea, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        settingsPanel.add(new JLabel("Time Step:"), constraints);

        constraints.gridx = 1;
        timeStepTextArea = new JTextArea(String.valueOf(demo.settings.getSimulationSpeed()), 1, 10);
        settingsPanel.add(timeStepTextArea, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 1;
        applyButton = new JButton("Apply");
        applyButton.setBackground(Color.green);
        applyButton.setActionCommand("Apply");
        applyButton.addActionListener(this);
        settingsPanel.add(applyButton, constraints);

        constraints.gridx = 1;
        backButton = new JButton("Back");
        backButton.setBackground(Color.red);
        backButton.setActionCommand("Back");
        backButton.addActionListener(this);
        settingsPanel.add(backButton, constraints);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("Apply".equals(command)) {
            applySettings();
        } else if ("Back".equals(command)) {
            dispose();
        }
    }

    private void applySettings() {
        demo.settings.setInfinite(runInfinitelyCheckBox.isSelected());
        demo.settings.setShowTrail(showParticlesCheckBox.isSelected());
        demo.settings.setShowCenterOfGravity(showCenterOfGravityCheckBox.isSelected());
        demo.settings.setSimulationSpeed(simulationSpeedSlider.getValue() / 10.0);
        demo.settings.setSkipAhead(Integer.parseInt(numberOfStepsTextArea.getText()));
        demo.simulation.configure(demo.settings);
    }
}
