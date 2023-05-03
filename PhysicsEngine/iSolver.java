package PhysicsEngine;

import SolarSystem.CelestialBody;

public interface iSolver {

    public double[][][] solve(double timestep, double[][][] oldState);
}
