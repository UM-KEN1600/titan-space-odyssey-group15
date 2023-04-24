package PhysicsEngine;
import SolarSystem.*;


// implementation of the Adams-Bashforth solver

public class AdamsBashforthSolver  implements iSolver {
    private int order;

    public AdamsBashforthSolver(int order) {
        this.order = order;
    }
    
  /* public double[][] solve(CelestialBody body, double timestep) {
        double[][] newState = new double[2][3];
        newState[0] = State.getPosition(body.rowInState);
        newState[1] = State.getVelocity(body.rowInState);
    
        double[][] prevStates = new double[order][2][3];
        prevStates[0] = newState;
    
        // Run the Euler method to obtain initial values for Adams-Bashforth
        EulerSolver euler = new EulerSolver();
        for (int i = 1; i < order; i++) {
            prevStates[i] = euler.solve(body, timestep, prevStates[i-1]);
        }
    
        // Use Adams-Bashforth to iteratively calculate the new state
        for (int i = order; i < State.getNumBodies(); i++) {
            double[][] prevVelocities = new double[order][3];
            for (int j = 0; j < order; j++) {
                prevVelocities[j] = prevStates[j][1];
            }
            double[] velocity = nextVelocity(prevVelocities, timestep);
            double[] position = nextPosition(newState[0], velocity, timestep);
            newState[0] = position;
            newState[1] = velocity;
    
            // Shift the previous states one index to the left
            for (int j = 0; j < order-1; j++) {
                prevStates[j] = prevStates[j+1];
            }
            prevStates[order-1][0] = position;
            prevStates[order-1][1] = velocity;
        }
    
        return newState;
    }*/
   

}
