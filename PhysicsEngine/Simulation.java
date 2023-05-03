package PhysicsEngine;
import java.util.Arrays;

import SolarSystem.CelestialBody;

public class Simulation {

    final boolean SHOWENDPOSITIONS = true;

    Functions functions = new Functions();
    iSolver solver = new RungeKutta4Solver();
    State state = new State();

    double timeStep; // in seconds
    int lengthOfSimulation = 31536000; //seconds of one year

    public Simulation(double timeStep)
    {
        this.timeStep = timeStep;
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

            double firstPart = Math.pow(probePosition[0] - titanPosition[0], 2) + Math.pow(probePosition[1] - titanPosition[1], 2) + Math.pow(probePosition[2] - titanPosition[2], 2);
            double distance = Math.sqrt(firstPart);
            System.out.println("Distance:");
            System.out.println(distance);
        }

        //System.out.println("New Positions:");
        //state.printPositions();

        
    }

}
