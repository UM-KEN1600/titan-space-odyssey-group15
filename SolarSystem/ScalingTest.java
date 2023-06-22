package SolarSystem;

import javax.swing.event.SwingPropertyChangeSupport;

import PhysicsEngine.Simulations.Simulation;
import PhysicsEngine.Solvers.EulerSolver;
import PhysicsEngine.States.State;

public class ScalingTest {
   
    public static void main(String[] args) {
        
        double xSpaceship = 1.3680655320287082E9;
        double ySpaceship = -4.8558482154349047E8;

        //double newX = (int)Math.round(CelestialBody.scaleDownPosition(xSpaceship, 5));
       // double newY =-(int)Math.round(CelestialBody.scaleDownPosition(ySpaceship, 5));


        //double titanX = (int)Math.round(CelestialBody.scaleDownPosition(State.allPositions[7][199][0],7));
        //double titanY =-(int)Math.round(CelestialBody.scaleDownPosition(State.allPositions[7][199][1],7));

        int titanX = 382;
        int titanY = 138;

        int newX = 376;
        int newY = 133;

        
        
        
      
        //78

        scaleX(titanX);
        scaleY(titanY);
        scaleX(newX);
        scaleY(newY);
        //29328
        
    }

    public static void scaleX(int x) {
            x = x * 80 - 30080;
            System.out.println(x);
        }

    public static void scaleY(int x) {
            x = x * 80 - 10640;
            System.out.println(x);
        }
    
}