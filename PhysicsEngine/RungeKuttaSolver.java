package PhysicsEngine;

import SolarSystem.CelestialBody;

public class RungeKuttaSolver implements iSolver {
    double heunValue;

    public RungeKuttaSolver(double a)
    {
        heunValue = a;
    }

    public double[] solve(CelestialBody body, double timestep)
    {
        double d = heunValue;
        return null;
    }
}
