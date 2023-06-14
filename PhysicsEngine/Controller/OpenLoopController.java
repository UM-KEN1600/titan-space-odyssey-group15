package PhysicsEngine.Controller;

import PhysicsEngine.Operations.VectorOperations;

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
    final double[] CENTER_OF_TITAN = {1.3680484627624216E9,-4.8546124152074784E8};

    //Timestep being used in the current instance
    private double timestep;
    
    //Angle that will be used in the calculations that moment
    private double theta;

    public OpenLoopController(double[] startPositionSpaceship)
    {
        
    }

    @Override
    public double[][] getNextState(double[] currentVelocity, double[] currentPosition, double u, double v, double theta) 
    {
        return new double[0][0];
    }

    /**
     * Calculates the acceleration for x (horizontal)
     * @param u thrust acceleration
     * @param theta angle of rotation
     * @return horizontal acceleration
     */
    private double calculateXAcceleration(double u, double theta)
    {
        return u * Math.sin(theta);
    }

    /**
     * Calculates the acceleration for y (vertical)
     * @param u thrust acceleration
     * @param theta angle of rotation
     * @return vertical acceleration
     */
    private double calculateYAcceleration(double u, double theta)
    {
        return u * Math.cos(theta) - g;
    }

    /**
     * Calculates the angle of rotation
     * @param torque the torque calculated
     * @return angle of rotation
     */
    private double calculateTheta(double torque)
    {
        return torque * Math.pow(timestep,2); //timestep is currently 1, so has no effect
    }

    /**
     * Calculates the angle between the tail of the spaceship and Titan
     * @return angle between tail and Titan
     */
    private double calculateAngleBetweenTailAndTitan()
    {
        double[] relativePositionOfTitan = getPositionRelativeToSpaceship(CENTER_OF_TITAN);
        double[] relativePositionOfTail = getPositionRelativeToSpaceship(positionOfTail);

        //calculate the angle between spaceship "perpendicularness"
        double dotProduct = VectorOperations.dotProduct(relativePositionOfTitan, relativePositionOfTail);
        double aMag = VectorOperations.magnitude(relativePositionOfTitan);
        double bMag = VectorOperations.magnitude(relativePositionOfTail);

        return Math.acos(dotProduct/(aMag * bMag));
    }

    /**
     * Returns the difference in positions of the spaceship's current position and a celestial body's position
     * @param subject a celestial body's position
     * @return position of the celestial body relative to the spaceship
     */
    private double[] getPositionRelativeToSpaceship(double[] subject)
    {
        return VectorOperations.vectorSubtraction(subject, currentPosition);
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
