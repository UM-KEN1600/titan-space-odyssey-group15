package PhysicsEngine.JourneyPhase;

import PhysicsEngine.Solvers.RungeKutta4Solver;
import PhysicsEngine.Solvers.iSolver;

public class LandingPhase implements iJourneyPhase{

    private double timestep = 1;
    private iSolver solver = new RungeKutta4Solver();

    @Override
    public double getStepSize() {
        return timestep;
    }

    @Override
    public iSolver getSolver() {
        return solver;
    }
    
}
