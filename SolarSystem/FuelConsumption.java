package SolarSystem;


import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class FuelConsumption extends JFrame{
    
    JFrame frame;
    public FuelConsumption(){

        JLabel text = new JLabel("Mission Accomplished");
        text.setFont(new Font("Consolas", Font.BOLD, 15));
        
        JLabel fuel = new JLabel("Fuel Consumption:00000000");
        
        JLabel titan = new JLabel("Distance from Titan:0000000");
        

      
        
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
        new FuelConsumption();
    }
}
