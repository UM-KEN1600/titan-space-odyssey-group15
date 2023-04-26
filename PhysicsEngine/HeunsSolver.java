package PhysicsEngine;
import SolarSystem.*;


// implementation of the Heun's solver 
// Figuuring out ho
public class HeunsSolver implements iSolver{
    private double a;

    public HeunsSolver(double a) {
        this.a = a;
    }

// there are some bugs relatives to the moltiplication of vectors, still trying to figure out them. The logic implemented should be right hopefully

  /*   public double[][] solve(CelestialBody body, double timestep, double[][] oldState) {
        // Define the indices for the position, velocity, and acceleration
        int position = 0;
        int velocity1 = 1;
        int velocity2 = 2; // Fix index for velocity2
        
        int acceleration = 2; // Fix index for acceleration
    
        // Create a new state array to hold the updated values
        double[][] newState = new double[2][3];
    
        // Copy the old state values into the new state array
        newState[position] = oldState[position];
        newState[velocity1] = oldState[velocity1];
    
        // Calculate the first estimate of the derivative (k1)
        double[][] k1 = new double[2][3];
        k1[velocity2] = oldState[acceleration]; // Fix k1 velocity2
        k1[acceleration] = VectorOperations.vectorScalarDivision(State.getForce(body.rowInState), body.getMass());
    
        // Calculate the second estimate of the derivative (k2)
        double[][] k2 = new double[2][3];
    
        double a = 0.5; // Define the constant 'a'
    
        k2[velocity2] = oldState[velocity1] + a * timestep * k1[acceleration]; // Fix k2 velocity2
        k2[acceleration] = VectorOperations.vectorScalarDivision(State.getForce(State.getNextState(body, timestep * a, oldState)), body.getMass());
    
        // Calculate the third estimate of the derivative (k3)
        double[][] k3 = new double[2][3];
        
        k3[velocity2] = oldState[velocity1] + a * timestep * (k1[acceleration] + 4 * k2[acceleration]) / 9; // Fix k3 velocity2
        k3[acceleration] = VectorOperations.vectorScalarDivision(State.getForce(State.getNextState(body, timestep * (2 - a), oldState)), body.getMass());
    
        // Calculate the new state values
        newState[position] = oldState[position] + timestep * (oldState[velocity1] + (2 * k2[velocity2] - k1[velocity2]) / 3);
        newState[velocity1] = oldState[velocity1] + timestep * (k1[acceleration] + 3 * k3[acceleration]) / 4;
    
        return newState;
    } */
}
