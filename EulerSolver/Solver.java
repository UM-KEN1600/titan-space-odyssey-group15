package EulerSolver;

import java.util.Arrays;
import java.util.Vector;

//implementation of Euler's method

public class Solver {
    // need initial values for x and y, coordinates I guess
    public static double[] currentPosition = new double[3];
    public static double[] nextPosition = new double[3];
    public static double[] currentVelocity = new double[3];
    public static double[] nextVelocity = new double[3];
    public static double h = 0.05;
    public static double[] accerleration = new double[3];

    public static void main(String[] args) {
        currentPosition[0] = State.positions[11][0];
        currentPosition[1] = State.positions[11][1];
        currentPosition[2] = State.positions[11][2];
    }


    public static double[] accerlerationCalculation(double mass, double[] force) {
        accerleration = VectorOperations.vectorScalarDivision(force, mass);
        return accerleration;
    
    }

    public static double[] nextPosition(double[] currentPosition, double[] currentVelocity, double h) {
        nextPosition = VectorOperations.vectorAddition(currentPosition, VectorOperations.vectorScalarMultiplication(currentVelocity, h));

        return nextPosition;
    }

    public static double[] nextVelocity(double[] currentVelocity, double[] accerleration){
        nextVelocity = VectorOperations.vectorAddition(currentVelocity, VectorOperations.vectorScalarMultiplication(accerleration, h)); 
    

        return nextVelocity;
        
    }

    


}
