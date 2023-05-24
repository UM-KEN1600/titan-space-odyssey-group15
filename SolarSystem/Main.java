package SolarSystem;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

import PhysicsEngine.*;

public class Main {

    public static CelestialBody body = new CelestialBody();
    public static Draw drawPanel = new Draw();
    public static  String[] solvers = {"Euler" , "Runge-Kutta4" , "Runge-Kutta2", "Adam-B}shforth" , "Heuns" };
    public static void main(String[] args)  {

        //Enter time step in seconds here:
        double timeStep = 100;

        //Choose Solver:
        iSolver solver = new RungeKutta4Solver();
        //--------------------------------------

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

       
        JComboBox choooser = new JComboBox(solvers);
        chooser.set
        mainFrame.add(choooser);

        // timer to move celestial bodies
        Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            int a = 0;
            @Override
            public void run() {
                drawPanel.repaint();
                if(a == 199)
                {
                    t.cancel();
                }
                a++;
            }
        };
        t.schedule(tt, 0, 100);

        mainFrame.setVisible(true);

    }

}
