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
    private double positionOfTail = 0;

    //Current Values of the probe
    private double[] currentPosition;
    private double[] currentVelocity;

    //Position of Titan after one year, used for calculation of angle
    final double[] CENTER_OF_TITAN = {1.3680484627624216E9,-4.8546124152074784E8};

    //Timestep being used in the current instance
    private double timestep;
    
    //Angle that will be used in the calculations that moment
    private double theta;


    @Override
    public double[][] getNextState(double[] currentVelocity, double[] currentPosition, double u, double v, double theta) {
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

    private double calculateTorque(double[] forceApplied)
    {
        //The angle between two 3D vectors a and b is calculate by dividing the dot product a*b by their norms |a| and |b|, and taking the cos^-1

    }

    private double[] getPositionRelativeToSpaceship(double[] spaceshipPosition)
    {
        return VectorOperations.vectorSubtraction(CENTER_OF_TITAN, spaceshipPosition);
    }
}
