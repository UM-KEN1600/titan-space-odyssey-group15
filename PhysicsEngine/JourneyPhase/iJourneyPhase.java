package PhysicsEngine.JourneyPhase;

import PhysicsEngine.Solvers.iSolver;

public interface iJourneyPhase {
    public double getStepSize();
    public iSolver getSolver();
    public int getAmountOfPositionsStored(double seconds, double stepSize);
    public int getAmountOfFramesNeeded(double amountOfPositionsStored, double framesTotal, double stepSize);
}
