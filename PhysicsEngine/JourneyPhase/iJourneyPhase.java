package PhysicsEngine.JourneyPhase;

import PhysicsEngine.Solvers.iSolver;

public interface iJourneyPhase {
    public double getStepSize();
    public iSolver getSolver();
}
