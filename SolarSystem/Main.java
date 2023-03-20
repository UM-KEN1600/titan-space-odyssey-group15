package SolarSystem;

import java.awt.Color;
import java.awt.Graphics2D;
import java.security.cert.CertSelector;
import java.util.ArrayList;
import EulerSolver.*;
import javax.swing.*;

public class Main {
    

    public static void main(String[] args)  {

        
        //frame
        JFrame mainFrame = new JFrame("Solar System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1000, 600);
        mainFrame.add(new CelestialBody(0, 0, 0, null, null));
        mainFrame.setLocationRelativeTo(null);
        mainFrame.add(new Draw());
        mainFrame.setVisible(true);

            
        
        
        
    }
}
