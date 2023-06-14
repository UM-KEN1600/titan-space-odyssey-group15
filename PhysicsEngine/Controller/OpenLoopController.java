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

    private double calculateXAcceleration(double u, double theta)
    {
        return u * Math.sin(theta);
    }

    private double calculateYAcceleration(double u, double theta)
    {
        return u * Math.cos(theta) - g;
    }

    private double calculateTheta(double torque)
    {
        return torque * Math.pow(timestep,2); //timestep is currently 1, so has no effect
    }

    private double calculateAngleBetweenTailAndTitan()
    {
        double[] relativePositionOfTitan = getPositionRelativeToTitan(currentPosition);
        double[] relativePositionOfTail = getPositionRelativeToTitan(positionOfTail);

        //calculate the angle between spaceship "perpendicularness"
        double dotProduct = VectorOperations.dotProduct(relativePositionOfTitan, relativePositionOfTail);
        double aMag = VectorOperations.magnitude(relativePositionOfTitan);
        double bMag = VectorOperations.magnitude(relativePositionOfTail);

        return Math.acos(dotProduct/(aMag * bMag));
    }

    private double[] getPositionRelativeToTitan(double[] subject)
    {
        return VectorOperations.vectorSubtraction(subject, LANDING_POSITION);
    }

    private double[] calculatePositionOfTail() //needs to be redone ?
    {
        double[] change = {SIZE_OF_SPACESHIP,0};
        return VectorOperations.vectorSubtraction(currentPosition, change);
    }
}
