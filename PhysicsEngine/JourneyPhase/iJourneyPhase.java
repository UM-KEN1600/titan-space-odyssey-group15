package PhysicsEngine.JourneyPhase;

import PhysicsEngine.Solvers.iSolver;

/**
 * Enables the storing of different conditions for the different phases in the journey, namely travel to titan and landing on titan
 */
public interface iJourneyPhase {
    public double getStepSize();
    public iSolver getSolver();
    public int getAmountOfPositionsStored(double seconds, double stepSize);
    public int getAmountOfFramesNeeded(double amountOfPositionsStored, double framesTotal, double stepSize);
}
