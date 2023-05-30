package SolarSystem;


import java.awt.FlowLayout;
import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;

import PhysicsEngine.Thrust;
import PhysicsEngine.Simulations.Simulation;
import PhysicsEngine.States.State;

public class FuelConsumption extends JFrame{
    
    JFrame frame;
    public FuelConsumption(Simulation simulation){

        double distance = simulation.getClosestDistanceToTitan();
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String distance2decimals = decimalFormat.format(distance);
        JLabel text = new JLabel("Mission Accomplished");
        text.setFont(new Font("Consolas", Font.BOLD, 15));
        
        JLabel fuel = new JLabel("Fuel Consumption:");
        
        JLabel titan = new JLabel("Distance from Titan: " + distance2decimals + "km");
        
        this.setSize(250, 250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10,20));
        this.add(text);
        this.add(fuel);
        this.add(titan);
        this.setVisible(true);
        

    }

    public static void main(String[] args) {
      //  new FuelConsumption(new Simulation(ABORT, null));
    }
}
