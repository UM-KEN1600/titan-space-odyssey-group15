package PhysicsEngine;
import java.util.Arrays;

import SolarSystem.CelestialBody;

public class Simulation {

    final boolean SHOWENDPOSITIONS = true;

    Functions functions = new Functions();
    iSolver solverRK4 = new RungeKutta4Solver();
    iSolver solverHeuns = new HeunsSolver();
    AdamBashforthSolver solverAdamBashforth = new AdamBashforthSolver();

    State state = new State();

    double timeStep; // in seconds
    int lengthOfSimulation = 31536000; //seconds of one year // if the simulation is runned in 333536000, the probe visually touches titan

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

        double[][][] previousState = new double[12][2][3];
        

        //Uncomment the method to test them
        for(int i = 0 ; i < (timesPerSimulation); i++)
        {   
            //nextState = solverRK4.solve(timeStep, state.getState());// solve with rk4

            //nextState = solverHeuns.solve(timeStep, state.getState());// solve with heuns

            /// AB2 needs the bootstrapping, so the first execution myust be done with another method 
                if(i==0){
                    previousState = state.getState(); 
                    nextState = solverRK4.solve(timeStep, state.getState());// bootstrap with with RK4
                }
                else {
                    double[][][] temp = nextState;
                    //previouState is w(i-1), while nextState is wi
                    nextState = solverAdamBashforth.solve(timeStep, nextState, previousState);// solve with adam bashforth
                    previousState=temp;
                }  
            ///
            
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

        double[] probePosition = State.getPosition(8);
        double[] titanPosition = State.getPosition(7);
        double firstPart = Math.pow(probePosition[0] - titanPosition[0], 2) + Math.pow(probePosition[1] - titanPosition[1], 2) + Math.pow(probePosition[2] - titanPosition[2], 2);
        double distance = Math.sqrt(firstPart);

        return distance;
    }

    

}
