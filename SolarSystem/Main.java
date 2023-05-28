package SolarSystem;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

import PhysicsEngine.Simulations.Simulation;
import PhysicsEngine.Solvers.iSolver;

public class Main {

    public static CelestialBody body = new CelestialBody();
    public static Draw drawPanel = new Draw();
  

    public static void run(iSolver solver){

        //Enter time step in seconds here:
        double timeStep = 50;

        

        Simulation simulation = new Simulation(timeStep, solver);
        simulation.planetarySetUp();

        //frame
        JFrame mainFrame = new JFrame("Solar System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1000, 600);
        mainFrame.add(new CelestialBody(0, 0, 0, null));
        mainFrame.setLocationRelativeTo(null);
        mainFrame.add(drawPanel);

       

        ImageIcon icon = new ImageIcon("icon.jpg");
        mainFrame.setIconImage(icon.getImage());
        // timer to move celestial bodies
        Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            int a = 0;
            @Override
            public void run() {
                drawPanel.repaint();
                if (a == 99) { // change to 199, using 99 for testing
                    t.cancel();
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            FuelConsumption f = new FuelConsumption();
                            f.setVisible(true);
                           // mainFrame.dispose();
                        }
                    });
                }
                a++;
            }
        };
        t.schedule(tt, 0, 100);
        
        mainFrame.setVisible(true);
}
}
