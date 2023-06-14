package PhysicsEngine.Controller;

import PhysicsEngine.Operations.VectorOperations;
import SolarSystem.CelestialBody;

public class OpenLoopController implements iController{
    //Final Values needed
    //NOTE: Values are in km, not m. (Apart from angle)
    final double xFINAL = 0.0001;
    final double xVelocityFINAL = 0.0001;
    final double yVelocityFINAL = 0.0001;
    final double thetaFINAL = 0.02;
    final double angularVelocityFINAL = 0.01;

    //Max constraints for some values
    final double g = 0.001352;
    final double maxThrust = 10*g;
    final double maxTorque = 1;

    final double SIZE_OF_SPACESHIP = 0.1; //100 meters :)
    private double[] positionOfTail = new double[0]; //TO BE DONE LATER 

    //Current Values of the probe
    private double[] currentPosition;
    private double[] currentVelocity;

    //Position of Titan after one year, used for calculation of angle
    //Top of titan
    final double[] LANDING_POSITION = {1.3680484627624216E9,-4.8546124152074784E8 + CelestialBody.bodyList[7].getRadius()};

    //Timestep being used in the current instance
    private double timestep;
    
    //Angle that will be used in the calculations that moment
    private double theta;

    public OpenLoopController(double[] startPositionSpaceship){}

    @Override
    public double[][] getNextState(double[] currentVelocity, double[] currentPosition, double u, double v, double theta) 
    {
        return new double[0][0];
    }

    /**
     * ASSUMPTIONS:
     * - The rocket facing "right" is 0 degrees
     * - Could arrive at an an
     * @param currentPosition
     * @param currentVelocity
     */
    public void planLanding(double[] currentPosition, double[] currentVelocity)
    {
        //calculate the positions of tail and head
        //calculate the angle
        //based on angle calculate thrusters
        //later include wind
        // 
    }
    
    /**
     * Calculates the angle between the velocity of the spaceship and the wanted landing position
     * Assumes that the landing angle is 90 degrees and therefore the yAxis
     * @return angle between tail and Titan
     */
    private static double calculateAngleBetweenSpaceshipAndTitan(double[] passedVelocity)
    {
        double[] velocities = {passedVelocity[0],passedVelocity[1]};
        double[] xAxis = {10,0};

        //calculate the angle between spaceship "perpendicularness"
        double dotProduct = VectorOperations.dotProduct(xAxis,velocities);
        double aMag = VectorOperations.magnitude(velocities);
        double bMag = VectorOperations.magnitude(xAxis);

        double check = passedVelocity[0]*xAxis[1] - passedVelocity[0]*xAxis[0];
        
        if(check < 0)
        {
            return 2*Math.PI - Math.acos(dotProduct/(aMag * bMag));
        }

        return Math.acos(dotProduct/(aMag * bMag));
    }


    /**
     * Returns the difference in positions of the spaceship's current position and a celestial body's position
     * @param subject a celestial body's position
     * @return position of the celestial body relative to the spaceship
     */
    private double[] getPositionRelativeToTitan(double[] subject)
    {
        return VectorOperations.vectorSubtraction(subject, LANDING_POSITION);
    }

    /**
     * Calculates the position of the spaceship's tail
     * @return the position of the tail
     */
    private double[] calculatePositionOfTail() //needs to be redone ?
    {
        double[] change = {SIZE_OF_SPACESHIP,0};
        return VectorOperations.vectorSubtraction(currentPosition, change);
    }
}
