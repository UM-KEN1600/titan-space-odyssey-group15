package SolarSystem;

import java.awt.Color;
import java.security.cert.CertSelector;
import java.util.ArrayList;

import javax.swing.*;

public class Main {
    

    public static void main(String[] args) {

        ArrayList<CelestialBody> celestialBodiesArrayList = new ArrayList<CelestialBody>();

        //panel
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1500, 700);
        panel.setBackground(Color.BLACK);

        //frame
        JFrame mainFrame = new JFrame("Solar System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1600, 800);
        mainFrame.add(panel);
        mainFrame.setVisible(true);

            
        
        
        
    }
}
