package PhysicsEngine;
import SolarSystem.CelestialBody;


//implementation of Euler's method

public class EulerSolver implements iSolver{
    // need initial values for x and y
   
    public static double[] nextVelocity = new double[3];

    /**
     * calculates the acceleration of a particular celestial body
     * @param mass the mass of the celestial body
     * @param force the force of the celestial body
     * @return a vector array containing the acceleration
     */
    public double[] accelerationCalculation(double mass, double[] force) {
        double[] acceleration = new double[3];
        acceleration = VectorOperations.vectorScalarDivision(force, mass);
        //System.out.println(acceleration);
        return acceleration;
    
    }

    /**
     * calculates the next position of a celestial body
     * @param currentPosition a vector array of the current position of the celestial body
     * @param currentVelocity a vector array of the current velocity of the celestial body
     * @param timestep the timestep used in the vector calculation
     * @return a vector array containing the new position of the body
     */
    public double[] nextPosition(double[] currentPosition, double[] currentVelocity, double timestep) {
        double[] nextPosition = new double[3];
        nextPosition = VectorOperations.vectorAddition(currentPosition, VectorOperations.vectorScalarMultiplication(currentVelocity, timestep));
        currentPosition = nextPosition;
        //System.out.println(currentPosition);
        return currentPosition;
    }

    /**
     * calculates a new velocity of a celestial body
     * @param currentVelocity a vector array of the current velocity of the celestial body
     * @param acceleration a vector array of the current acceleration of the celestial body
     * @param timestep the timestep used for the vector calculation
     * @return a vector array containing the new velocity of the body
     */
    public double[] nextVelocity(double[] currentVelocity, double[] acceleration, double timestep){
        double[] nextVelocity = new double[3];
        nextVelocity = VectorOperations.vectorAddition(currentVelocity, VectorOperations.vectorScalarMultiplication(acceleration, timestep)); 
        currentVelocity = nextVelocity;
        //System.out.println(currentVelocity);
        return currentVelocity; 
    }

    /**
     * an implementation of Euler's solver that calculates a new position of a celestial body
     * @param body a celestial body
     * @param timestep the timestep used in vector calculations
     * @return a vector array containing a new position of the body
     */
    public double[] solve(CelestialBody body, double timestep){
        double[] acceleration = accelerationCalculation(body.getMass(), State.getForce(body.rowInState));
        double[] velocity = nextVelocity(State.getVelocity(body.rowInState), acceleration, timestep);
        State.setVelocity(body.rowInState, velocity);
        return (nextPosition(State.getPosition(body.rowInState), velocity, timestep));
    }


}
