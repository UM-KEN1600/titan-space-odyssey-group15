package PhysicsEngine;
import java.util.Arrays;

import SolarSystem.CelestialBody;

public class Simulation {

    final boolean SHOWENDPOSITIONS = true;

    Functions functions = new Functions();
    EulerSolver eulerSolver = new EulerSolver();

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

        //State.printPositions();
        
        for(int j = 1; j < 9; j++)
        {
            State.setTimedPosition(CelestialBody.list[j]);
        }

        State.iterations++; 
                            //63072
        for(int i = 0 ; i < (timesPerSimulation); i++)
        {
            for(int j = 1; j < 12; j++)
            {
                State.setForce(j, functions.forceOnPlanet(CelestialBody.list[j]));
            }
    
            for(int j = 1; j < 12; j++)
            {

                State.setPosition(j, eulerSolver.solve(CelestialBody.list[j], timeStep));

                //this stores the positions of a planet 100 times a year
                if(j < 9 && i % framesPer10Seconds == 0)
                {
                    State.setTimedPosition(CelestialBody.list[j]);
                }
            }

            if(i % framesPer10Seconds == 0)
            {
                State.iterations++;
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
        //State.printPositions();

        
    }

}
