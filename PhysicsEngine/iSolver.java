package PhysicsEngine;

import SolarSystem.CelestialBody;

public interface iSolver {
    public double[][] solve(CelestialBody body, double timestep);
}
