package PhysicsEngine;

import SolarSystem.CelestialBody;

public class RungeKuttaSolver implements iSolver {
    double a;
    EulerSolver euler = new EulerSolver();

    public RungeKuttaSolver(double a)
    {
        this.a = a;
    }

    public double[][] solve(CelestialBody body, double timestep)
    {
        double[][] newState = new double[2][3];
        newState[0] = State.getPosition(body.rowInState);
        newState[1] = State.getVelocity(body.rowInState);

        int position = 0;
        int velocity = 1;
        double[][] k1 = new double[2][3]; // stores velocities and accelerations
        double[][] k2 = new double[2][3];
        double[][] k3 = new double[2][3];

        //k1 = Euler Step = yi+1 = yi + a*h*f(t,y)) gives a temporary position at y(ti + a*h)
        k1 = euler.solve(body, a*timestep, newState);  

        //k2 = (1/2*a) * f( (ti + a*hi), (pi + k1) )
        k2 = euler.solve(body, a*timestep, k1);

    }
}
