package PhysicsEngine;

import SolarSystem.CelestialBody;

public interface iSolver {
    public double[][] solve(CelestialBody body, double timestep, double[][] oldState);
    public double[][][] solve(double timestep, double[][][] oldState);
}
