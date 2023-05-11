package PhysicsEngine;
import java.util.Arrays;

import SolarSystem.CelestialBody;

public class Simulation {

    final boolean SHOWENDPOSITIONS = true;

    Functions functions = new Functions();
    iSolver solver;;
    State state = new State();

    double timeStep; // in seconds
    int lengthOfSimulation = 31536000; //seconds of one year // if the simulation is runned in 333536000, the probe visually touches titan

    public Simulation(double timeStep, iSolver solver)
    {
        this.timeStep = timeStep;
        this.solver = solver;
    }

    public void planetarySetUp() 
    {
        CelestialBody.setupCelestialBodies();

        int timesPerSimulation = (int) Math.ceil(lengthOfSimulation / timeStep);
        int framesPer10Seconds = (int) Math.ceil(timesPerSimulation / 100 + ((timesPerSimulation/timeStep)%100));
        

        state.setTimedPosition();

        double[][][] nextState = new double[12][2][3];

        for(int i = 0 ; i < (timesPerSimulation); i++)
        {   
            nextState = solver.solve(timeStep, state.getState());
            
            state.setState(nextState);

            //this stores the positions of a planet 100 times a year
            if(i % framesPer10Seconds == 0)
            {
                state.setTimedPosition();
            }
        }

        if(SHOWENDPOSITIONS)
        {
            double[] probePosition = State.getPosition(8);
            double[] titanPosition = State.getPosition(7);

            System.out.println("Probe:");
            System.out.println(Arrays.toString(probePosition));
            System.out.println("Titan:");
            System.out.println(Arrays.toString(titanPosition));
            System.out.println("distance:");
            System.out.println(getDistaceProbeTitan());
        }

        //System.out.println("New Positions:");
        //state.printPositions();

    }

    /*
     * returns the actual distance from Titan to the probe
     */
    private double getDistaceProbeTitan(){
        return VectorOperations.euclideanForm(state.getState()[8][0],state.getState()[7][0]);
    }

    

}
