package EulerSolver;

import java.util.Arrays;
import java.util.Vector;

import SolarSystem.CelestialBody;
import SolarSystem.Spaceship;

//implementation of Euler's method

public class Solver {
    // need initial values for x and y, coordinates I guess
   
    public static double[] nextVelocity = new double[3];
    

    public static void main(String[] args) {
        
      
        
    }


    public static double[] accelerationCalculation(double mass, double[] force) {
        double[] acceleration = new double[3];
        acceleration = VectorOperations.vectorScalarDivision(force, mass);
        System.out.println(acceleration);
        return acceleration;
    
    }

    public static double[] nextPosition(double[] currentPosition, double[] currentVelocity, double timestep) {
        double[] nextPosition = new double[3];
        nextPosition = VectorOperations.vectorAddition(currentPosition, VectorOperations.vectorScalarMultiplication(currentVelocity, timestep));
        currentPosition = nextPosition;
        System.out.println(currentPosition);
        return currentPosition;
    }

    public static double[] nextVelocity(double[] currentVelocity, double[] acceleration, double timestep){
        double[] nextVelocity = new double[3];
        nextVelocity = VectorOperations.vectorAddition(currentVelocity, VectorOperations.vectorScalarMultiplication(acceleration, timestep)); 
        currentVelocity = nextVelocity;
        
        System.out.println(currentVelocity);
        return currentVelocity; 
    }

    public static double[] solve(CelestialBody body, double timestep){
        double[] acceleration = accelerationCalculation(body.getMass(), State.getForce(body.rowInState));
        double[] velocity = nextVelocity(State.getVelocity(body.rowInState), acceleration, timestep);
        State.setVelocity(body.rowInState, velocity);
        return (nextPosition(State.getPosition(body.rowInState), velocity, timestep));
    }


}
