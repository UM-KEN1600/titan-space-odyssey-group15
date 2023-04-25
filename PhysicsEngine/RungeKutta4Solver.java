package PhysicsEngine;

import SolarSystem.CelestialBody;

public class RungeKutta4Solver implements iSolver {
    EulerSolver euler = new EulerSolver();

    @Override
    public double[][] solve(CelestialBody body, double timestep, double[][] oldState) 
    {
        //aids to understand what is being calculated
        int position = 0;
        int velocity1 = 1;
        int velocity2 = 0;
        int acceleration = 1;

        double[][] newState = new double[2][3];
        newState[position] = oldState[position];
        newState[velocity1] = oldState[velocity1];

        
        double[][] k1 = new double[2][3]; // stores velocities and accelerations
        double[][] k2 = new double[2][3];
        double[][] k3 = new double[2][3];
        double[][] k4 = new double[2][3];

        //k1 = f(ti, wi) or the derivative at that position (velocity and acceleration since we do not have a function)
        k1[velocity2] = oldState[velocity1];
        k1[acceleration] = VectorOperations.vectorScalarDivision(State.getForce(body.rowInState), body.getMass());

        //k1 * h
        k1 = MatrixOperations.matrixScalarMultiplication(k1, timestep);

        //k2 = f( (ti + 1/2*hi), (pi + 1/2*k1) )
        k2 = euler.solve(body, 0.5*timestep, MatrixOperations.matrixScalarMultiplication(k1, 0.5));
        //k2 * h
        k2 = MatrixOperations.matrixScalarMultiplication(k2, timestep);

        //k3 = f( (ti + 1/2*hi), (pi + 1/2*k2) )
        k3 = euler.solve(body, 0.5*timestep, MatrixOperations.matrixScalarMultiplication(k2, 0.5));
        //k3 * h
        k2 = MatrixOperations.matrixScalarMultiplication(k2, timestep);

        //k4 = f(t+ 1/2*h, pi + k3)
        k4 = euler.solve(body, timestep, k3);
        //k4 * h
        k4 = MatrixOperations.matrixScalarMultiplication(k4, timestep);

        //k2 *2 and k3 *2
        k1 = MatrixOperations.matrixScalarMultiplication(k2, 1/6.0);
        k2 = MatrixOperations.matrixScalarMultiplication(k2, 2/6.0);
        k3 = MatrixOperations.matrixScalarMultiplication(k3, 2/6.0);
        k4 = MatrixOperations.matrixScalarMultiplication(k2, 1/6.0);

        //w (i+1) = w(i) + 1/6 * (k1 + k2 + k3 + k4) (k2, k3 times 2 happens above)

        double[][] a = MatrixOperations.matrixAddition(k1, k2);
        double[][] b = MatrixOperations.matrixAddition(k3, k4);
        double[][] c = MatrixOperations.matrixAddition(a, b);
        //c = MatrixOperations.matrixScalarMultiplication(c, 1/6.0);

        newState = MatrixOperations.matrixAddition(oldState, c);

        return newState;
    }
    
}
