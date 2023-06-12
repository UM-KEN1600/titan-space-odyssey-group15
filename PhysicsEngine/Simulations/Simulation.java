package PhysicsEngine.Simulations;
import java.util.Arrays;

import PhysicsEngine.Functions;
import PhysicsEngine.Thrust;
import PhysicsEngine.JourneyPhase.LandingPhase;
import PhysicsEngine.JourneyPhase.TravelPhase;
import PhysicsEngine.JourneyPhase.iJourneyPhase;
import PhysicsEngine.Solvers.iSolver;
import PhysicsEngine.States.State;
import PhysicsEngine.Operations.VectorOperations;
import SolarSystem.CelestialBody;

public class Simulation {

    final boolean SHOWENDPOSITIONS = true;

    Functions functions = new Functions();
    iSolver solver;
    State state = new State();

    //controls the timestep and solver used in respective phases
    iJourneyPhase journeyPhase;

    double framesTotal = 200;
    double secondsOfTravel = 31536000; //seconds in a year //
    double secondsOfLanding = 86400; //seconds in a day

    //These are the velocities that have to be changed to modify the probe at the beginning or at the point to go back
    double[] startingVelocity = {49.58313440693111, 38.29506290304066, 1.9666588900013093};

    //Both of these are used to change the phases of the mission
    boolean goIntoOrbit = true;

    public Simulation(double timeStep, iSolver solver)
    {
        this.journeyPhase = new TravelPhase(timeStep);
        this.solver = solver;
    }

    public void planetarySetUp() 
    {   
        CelestialBody.setupCelestialBodies();

        int amountOfPositionsStoredTravel = (int) Math.ceil(secondsOfTravel / journeyPhase.getStepSize());
        int framesPer10Seconds = (int) Math.ceil(amountOfPositionsStoredTravel / (framesTotal/2.0) + ((amountOfPositionsStoredTravel/journeyPhase.getStepSize())%100));
        

        state.setTimedPosition();

        double[][][] nextState = new double[12][2][3];
        
        spacecraftEngine(startingVelocity);

        for(int i = 0 ; i < (amountOfPositionsStoredTravel); i++)
        {   
            nextState = solver.solve(journeyPhase.getStepSize(), state.getState());
            
            state.setState(nextState);

            orbit(); //orbits if distance is below 300

            //this stores the positions of a planet 100 times a year
            if(i % framesPer10Seconds == 0)
            {
                state.setTimedPosition();
            }
        }

        journeyPhase = new LandingPhase();
        int amountOfPositionsStoredLanding = (int) Math.ceil(secondsOfLanding / journeyPhase.getStepSize());
        framesPer10Seconds = (int) Math.ceil(amountOfPositionsStoredLanding / (framesTotal/2.0) + ((amountOfPositionsStoredLanding/journeyPhase.getStepSize())%100));

        for(int i = 0 ; i < (amountOfPositionsStoredLanding); i++)
        {  
            //next State = whatever the controller says, only for spaceship
            




            //IMPLEMENT EVERYTHING ABOVE -----------------------------------------
            if(i % framesPer10Seconds == 0)
            {
                state.setTimedPosition();
            }
        }


        if(!SHOWENDPOSITIONS)
        {   
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

    }

    /*
     * returns the actual distance from Titan to the probe
     */
    private double getDistaceProbeTitan(){
        return VectorOperations.euclideanForm(state.getState()[8][0],state.getState()[7][0]);
    }

    double timeInOrbit = 10200; //seconds, derived from calculations of ORBITAL TIME with respect to Titan's mass and the spaceship's distance
    double startTimeOrbit = 0;

    /**
     * Checks if the spaceship is in orbit and within 300km of Titan, if yes, sets the spaceships velocity to that of being in an orbit
     */
    private void orbit()
    {
        if(getDistaceProbeTitan() < (300 + CelestialBody.bodyList[7].radius) && goIntoOrbit == true)
        {
            spacecraftEngine(Functions.getVelocityForOrbit(state.getState()));
            goIntoOrbit = false;
        }

        if(goIntoOrbit == false)
        {
            startTimeOrbit += journeyPhase.getStepSize();
        }  
    }



    /*This method is used for any change in velocity for the probe
     * Calculates how much fuel is consumed and changes the fuelConsumption variable
     * Changes the velocity of the probe in state
     */ 
    public void spacecraftEngine(double[] newVelocity){
        double fuelUsed = Thrust.fuelConsumption(Functions.changeInVelocity(state.getState(), newVelocity));
        state.fuelConsumption += fuelUsed;
        state.setSpaceshipVelocity(newVelocity);
       
    }

    static double closestDistanceToTitan = Double.MAX_VALUE;

    private void checkClosestDistance()
    {
        double distance = getDistaceProbeTitan();

        if(distance < closestDistanceToTitan)
        {
            closestDistanceToTitan = distance;
        }
    }

    public double getClosestDistanceToTitan()
    {
        return closestDistanceToTitan;
    }
}
