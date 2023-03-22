package EulerSolver;

import java.util.Arrays;
import java.util.Vector;

import SolarSystem.CelestialBody;
import SolarSystem.Spaceship;

//implementation of Euler's method

public class Solver {
    // need initial values for x and y, coordinates I guess
    public static double[] currentPosition = new double[3];
    public static double[] nextPosition = new double[3];
    public static double[] currentVelocity = new double[3];
    public static double[] nextVelocity = new double[3];
    public static double h = 0.05;
    public static double[] acc = new double[3];

    public static void main(String[] args) {
        currentPosition[0] = State.positions[11][0];
        currentPosition[1] = State.positions[11][1];
        currentPosition[2] = State.positions[11][2];
        acc[0] = 0;
        acc[1] = 60;
        acc[2] = 60;
        
    }


    public static double[] accelerationCalculation(double mass, double[] force) {
        acc = VectorOperations.vectorScalarDivision(force, mass);
        System.out.println(acc);
        return acc;
    
    }

    public static double[] nextPosition(double[] currentPosition, double[] currentVelocity, double h) {
        nextPosition = VectorOperations.vectorAddition(currentPosition, VectorOperations.vectorScalarMultiplication(currentVelocity, h));
        currentPosition = nextPosition;
        System.out.println(currentPosition);
        return currentPosition;
    }

    public static double[] nextVelocity(double[] currentVelocity, double[] accerleration){
        nextVelocity = VectorOperations.vectorAddition(currentVelocity, VectorOperations.vectorScalarMultiplication(accerleration, h)); 
        currentVelocity = nextVelocity;
        System.out.println(currentVelocity);
        return currentVelocity; 
    }

    


}
