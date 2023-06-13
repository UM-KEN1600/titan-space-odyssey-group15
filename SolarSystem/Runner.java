package SolarSystem;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;
import PhysicsEngine.Simulations.Simulation;
import PhysicsEngine.Solvers.iSolver;

public class Runner {

    public static CelestialBody body = new CelestialBody();
    public static Draw drawPanel = new Draw();
  
    public static void run(iSolver solver){

        //Enter time step in seconds here:
        double timeStep = 50;

        Simulation simulation = new Simulation(timeStep, solver);
        simulation.planetarySetUp();
       
        JFrame mainFrame = new JFrame("Solar System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1000, 600);
        mainFrame.setLocationRelativeTo(null);
        //mainFrame.setUndecorated(true);
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
                if (a == 199) {
                    t.cancel();
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            Results f = new Results(simulation);
                            f.setVisible(true);
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
