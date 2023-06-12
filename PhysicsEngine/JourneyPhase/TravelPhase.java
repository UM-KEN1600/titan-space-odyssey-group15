package PhysicsEngine.JourneyPhase;

import PhysicsEngine.Solvers.RungeKutta4Solver;
import PhysicsEngine.Solvers.iSolver;

public class TravelPhase implements iJourneyPhase{

    private double timestep;
    private iSolver solver = new RungeKutta4Solver();

    public TravelPhase (double timeStep)
    {
        this.timestep = timeStep;
    }

    @Override
    public double getStepSize() {
        return timestep;
    }

    @Override
    public iSolver getSolver() {
        return solver;
    }

}
