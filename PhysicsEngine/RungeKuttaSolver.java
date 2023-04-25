package PhysicsEngine;

import SolarSystem.CelestialBody;

public class RungeKuttaSolver implements iSolver {
    double a;
    EulerSolver euler = new EulerSolver();

    public RungeKuttaSolver(double a)
    {
        this.a = a;
    }

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

        //k1 = f(ti, wi) or the derivative at that position (velocity and acceleration since we do not have a function)
        k1[velocity2] = oldState[velocity1];
        k1[acceleration] = VectorOperations.vectorScalarDivision(State.getForce(body.rowInState), body.getMass());

        //k1 * h
        k1 = MatrixOperations.matrixScalarMultiplication(k1, timestep);

        //k2 = f( (ti + a*hi), (pi + k1*a) )
        k2 = euler.solve(body, a*timestep, MatrixOperations.matrixScalarMultiplication(k1, a));

        //k2 * h
        k2 = MatrixOperations.matrixScalarMultiplication(k2, timestep);

        //k2 = k2 * (1/2*a)
        k2 = MatrixOperations.matrixScalarMultiplication(k2, 1/2*a);

        //k1 = k1 * (1 - 1/2 *a)
        k1 = MatrixOperations.matrixScalarMultiplication(k1, 1 - 1/2*a);

        //w (i+1) = w(i) + k1 + k2
        newState = MatrixOperations.matrixAddition(oldState, MatrixOperations.matrixAddition(k1, k2));

        return newState;
    }
}
