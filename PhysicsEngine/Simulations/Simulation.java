package PhysicsEngine.Simulations;
import java.util.Arrays;

import PhysicsEngine.Functions;
import PhysicsEngine.Solvers.iSolver;
import PhysicsEngine.States.State;
import PhysicsEngine.Operations.VectorOperations;
import SolarSystem.CelestialBody;

public class Simulation {

    final boolean SHOWENDPOSITIONS = true;

    Functions functions = new Functions();
    iSolver solver;
    State state = new State();

    double timeStep; // in seconds
    double framesTotal = 200;
    int lengthOfSimulation = 31536000 * 2; //seconds in a year //

    double[] startingVelocity = {49.58313440693111, 38.29506290304066, 1.9666588900013093};
    double[] wayBackVelocity = {-42.6320877941698, 20.36288058367364, 2.0895448230614537};

    boolean goIntoOrbit = true;

    public Simulation(double timeStep, iSolver solver)
    {
        this.timeStep = timeStep;
        this.solver = solver;
    }

    public void planetarySetUp() 
    {   

        CelestialBody.setupCelestialBodies();

        int timesPerSimulation = (int) Math.ceil(lengthOfSimulation / timeStep);
        int framesPer10Seconds = (int) Math.ceil(timesPerSimulation / framesTotal + ((timesPerSimulation/timeStep)%100));
        

        state.setTimedPosition();

        double[][][] nextState = new double[12][2][3];
        
        spacecraftEngine(startingVelocity);

        for(int i = 0 ; i < (timesPerSimulation); i++)
        {   
            wayBack();

            nextState = solver.solve(timeStep, state.getState());
            
            state.setState(nextState);

            orbit(); //orbits if distance is below 300

            //this stores the positions of a planet 100 times a year
            if(i % framesPer10Seconds == 0)
            {
                state.setTimedPosition();
            }
        }

        double[][][] b = State.allPositions;

        if(SHOWENDPOSITIONS)
        {   
            //System.out.println(state.getState());
            state.printPositions();
             
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

    /**
     * Checks if the spaceship is in orbit and within 300km of Titan, if yes, sets the spaceships velocity to that of being in an orbit
     */
    private void orbit()
    {
        if(getDistaceProbeTitan() < 300 && goIntoOrbit == true)
        {
            spacecraftEngine(Functions.getVelocityForOrbit(state.getState()));
            goIntoOrbit = false;
        }
    }

    private void wayBack(){
        if(!goIntoOrbit){
            spacecraftEngine(wayBackVelocity);
        }
    }
    public void spacecraftEngine(double[] newVelocity){
        state.setSpaceshipVelocity(newVelocity);
        double fuelUsed = Thrust.fuelConsumption(Functions.changeInVelocity(state.getState(), newVelocity));
        state.fuelConsumption += fuelUsed;
    }
    

}
