package SolarSystem;

import javax.swing.event.SwingPropertyChangeSupport;

import PhysicsEngine.Simulations.Simulation;
import PhysicsEngine.Solvers.EulerSolver;
import PhysicsEngine.States.State;

public class ScalingTest {
   
    public static void main(String[] args) {


         for (int i = 0; i < 100; i++) {
        int spaceshipX =-(int)Math.round(CelestialBody.scaleDownLanding(State.landingPositionsAngle[i][0]));
        double spaceshipY =-(CelestialBody.scaleDownLanding(State.landingPositionsAngle[i][1])) + 400;
        System.out.println(spaceshipY);
    }
    }
}
    