//Name: Richarde Wihby
//Student ID: 816030381

// Resources:
//https:youtu.be/g2vDARb7gx8?feature=shared
//https:docs.oracle.com/javase%2F8%2Fdocs%2Fapi%2F%2F/java/awt/GridBagLayout.html
//https:youtu.be/HgkBvwgciB4?feature=shared
//https:docs.oracle.com/javase%2Ftutorial%2Fuiswing%2F%2F/components/slider.html
//https:youtu.be/Om3qzMoaIUo?feature=shared

import NBobodySimulation.OrbitalBody;
import NBobodySimulation.Simulation;
import NBobodySimulation.SimulationSettings;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Demo extends JFrame implements ActionListener {
    //Simulation simulation = new Simulation();
    SimulationSettings settings;
    JTextArea textArea = new JTextArea();
    private JPanel controlPanel;
    private JButton startButton;
    private JButton stopButton;
    private JButton settingsButton;
    private JTextArea status;
    private JPanel simulationPanel;
    private JLabel label;
    private Timer updateTimer;
    private JComboBox<String> presetComboBox;

    public Demo() {
        setTitle("Demo");
        setSize(1280, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;

        controlPanel = new JPanel();
        startButton = new JButton("Start Simulation");
        stopButton = new JButton("Stop Simulation");
        settingsButton = new JButton("Settings");

        startButton.setBackground(Color.green);
        startButton.addActionListener(this);

        stopButton.setBackground(Color.red);
        stopButton.addActionListener(this);

        settingsButton.setBackground(Color.yellow);
        settingsButton.addActionListener(this);

        controlPanel.add(startButton);
        controlPanel.add(stopButton);
        controlPanel.add(settingsButton);

        String[] presets = {"Simple Orbit", "Asymmetric Orbit", "Lagrangian Orbit", "Figure-8 Orbit", "Chaotic Orbit"};
        presetComboBox = new JComboBox<>(presets);
        presetComboBox.addActionListener(this);
        controlPanel.add(presetComboBox);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.weightx = 1.0;
        constraints.weighty = 0.0;
        mainPanel.add(controlPanel, constraints);

        status = new JTextArea();
        status.setEditable(false);
        status.setPreferredSize(new Dimension(200, 800));
        JScrollPane statusScrollPane = new JScrollPane(status);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 0.2;
        constraints.weighty = 1.0;
        mainPanel.add(statusScrollPane, constraints);

        simulationPanel = new JPanel();
        simulation.getPanel().setPreferredSize(new Dimension(1000, 1000));
        simulationPanel.add(simulation.getPanel(), BorderLayout.CENTER);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weightx = 0.6;
        mainPanel.add(simulationPanel, constraints);

        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 3;
        constraints.weightx = 1.0;
        constraints.weighty = 0.2;
        mainPanel.add(scrollPane, constraints);

        add(mainPanel);
        setVisible(true);

        ArrayList<OrbitalBody> bodies = getPresetBodies("Simple Orbit");

        settings = new SimulationSettings(bodies);
        settings.setInfinite(true);
        settings.setShowCenterOfGravity(false);
        settings.setBackgroundColor(Color.WHITE);
        settings.setShowTrail(true);
        settings.setShowGrid(true);
        settings.setFrameRate(60);
        settings.setSimulationSpeed(1.0);

        simulation.configure(settings);
        simulation.start();

        new Timer(100, e -> updateTextArea(settings.getBodies())).start();
        updateTimer = new Timer(100, e -> updateTextArea(settings.getBodies()));
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            simulation.start();
            status.setText("Simulation Started");
        } else if (e.getSource() == stopButton) {
            simulation.stop();
            status.setText("Simulation Stopped");
        } else if (e.getSource() == settingsButton) {
            Settings s = new Settings(this);
        } else if (e.getSource() == presetComboBox) {
            String selectedPreset = (String) presetComboBox.getSelectedItem();
            ArrayList<OrbitalBody> bodies = getPresetBodies(selectedPreset);
            simulation.stop();  
            settings.setBodies(bodies);
            simulation.configure(settings);
            simulation.start();  
        }
    }

    private ArrayList<OrbitalBody> getPresetBodies(String presetName) {
        ArrayList<OrbitalBody> bodies = new ArrayList<>();
        switch (presetName) {
            case "Simple Orbit":
                bodies.add(new OrbitalBody(new double[]{100, 0}, new double[]{0, 70.5}, 1, Color.RED));
                bodies.add(new OrbitalBody(new double[]{-100, 0}, new double[]{0, -70.5}, 1, Color.GREEN));
                bodies.add(new OrbitalBody(new double[]{0, 0}, new double[]{0, -0}, 1, Color.BLUE));
                break;
            case "Asymmetric Orbit":
                bodies.add(new OrbitalBody(new double[]{100, 100}, new double[]{30, 0}, 1, Color.RED));
                bodies.add(new OrbitalBody(new double[]{-100, -100}, new double[]{-30, 0}, 1, Color.GREEN));
                bodies.add(new OrbitalBody(new double[]{0, 0}, new double[]{0, 0}, 1, Color.BLUE));
                break;
            case "Lagrangian Orbit":
                bodies.add(new OrbitalBody(new double[]{0, -100}, new double[]{47.97, 0}, 1, Color.RED));
                bodies.add(new OrbitalBody(new double[]{-86.60254038, 50}, new double[]{-23.985, -41.54323862}, 1, Color.GREEN));
                bodies.add(new OrbitalBody(new double[]{86.60254038, 50}, new double[]{-23.985, 41.54323862}, 1, Color.BLUE));
                break;
            case "Figure-8 Orbit":
                bodies.add(new OrbitalBody(new double[]{0, 0}, new double[]{-43.735986, -67.123728}, 1, Color.RED));
                bodies.add(new OrbitalBody(new double[]{-100, 0}, new double[]{21.867993, 33.561864}, 1, Color.GREEN));
                bodies.add(new OrbitalBody(new double[]{100, 0}, new double[]{21.867993, 33.561864}, 1, Color.BLUE));
                break;
            case "Chaotic Orbit":
                bodies.add(new OrbitalBody(new double[]{150, 50}, new double[]{-15, -30}, 3, Color.RED));
                bodies.add(new OrbitalBody(new double[]{50, 0}, new double[]{21.867993, 33.561864}, 3, Color.GREEN));
                bodies.add(new OrbitalBody(new double[]{100, 0}, new double[]{21.867993, 33.561864}, 3, Color.BLUE));
                break;
        }
        return bodies;
    }

    private void updateTextArea(java.util.List<OrbitalBody> bodies) {
        StringBuilder sb = new StringBuilder();
        for (OrbitalBody body : bodies) {
            sb.append(body.toString()).append("\n");
        }
        textArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        new Demo();
    }
}
