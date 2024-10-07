package NBobodySimulation;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class SimulationSettings extends JFrame{
    private Color backgroundColor;
    private boolean isInfinite;
    private boolean showTrail;
    private boolean showGrid;
    private boolean showCenterOfGravity;
    private int frameRate;
    private double simulationSpeed;
    private int skipAhead;
    private ArrayList<OrbitalBody> bodies;

    
    public SimulationSettings() {
        this.bodies = new ArrayList<>();
        this.backgroundColor = Color.BLACK; 
        this.isInfinite = false; 
        this.showTrail = true; 
        this.showGrid = false; 
        this.showCenterOfGravity = false; 
        this.frameRate = 60; 
        this.simulationSpeed = 1.0; 
        this.skipAhead = 0; 
        
    }
    
    public SimulationSettings(ArrayList<OrbitalBody> bodies) {
        this.bodies = bodies;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public boolean isInfinite() {
        return isInfinite;
    }

    public void setInfinite(boolean isInfinite) {
        this.isInfinite = isInfinite;
    }

    public boolean showTrail() {
        return showTrail;
    }

    public void setShowTrail(boolean showTrail) {
        this.showTrail = showTrail;
    }

    public boolean showGrid() {
        return showGrid;
    }

    public void setShowGrid(boolean showGrid) {
        this.showGrid = showGrid;
    }

    public boolean showCenterOfGravity() {
        return showCenterOfGravity;
    }

    public void setShowCenterOfGravity(boolean showCenterOfGravity) {
        this.showCenterOfGravity = showCenterOfGravity;
    }

    public void setBodies(ArrayList<OrbitalBody> bodies) {
        this.bodies = bodies;
    }
    
    public void addOrbitalBody(OrbitalBody b) {
        this.bodies.add(b);
    }
    
    public void removeOrbitalBody(OrbitalBody b) {
        this.bodies.remove(b);
    }
    
    public void setSkipAhead(int skipAhead) {
        this.skipAhead = skipAhead;
    }
    
    public int getSkipAhead() {
        return skipAhead;
    }
    
    public ArrayList<OrbitalBody> getBodies() {
        return bodies;
    }
    
    public double[] getMasses() {
        double[] masses = new double[bodies.size()];
        for (int i = 0; i < bodies.size(); i++) {
            masses[i] = bodies.get(i).getMass();
        }
        return masses;
    }
    
    public int getDimensions() {
        return bodies.isEmpty() ? 0 : bodies.get(0).getDimensions();
    }
    
    public double getSimulationSpeed() {
        return simulationSpeed;
    }
    
    public void setSimulationSpeed(double simulationSpeed) {
        this.simulationSpeed = simulationSpeed;
    }
    
    public int getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(int frameRate) {
        this.frameRate = frameRate;
    }

    public double totalMass() {
        double total = 0;
        for (OrbitalBody body : bodies) {
            total += body.getMass();
        }
        return total;
    }

    public double avgMass() {
        return bodies.isEmpty() ? 0 : totalMass() / bodies.size();
    }
}
