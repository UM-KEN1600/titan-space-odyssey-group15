package PhysicsEngine.Controller;

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


    //Current Values of the probe
    public double[] currentPosition;
    public double[] currentVelocity;

    //Timestep being used in the current instance
    public double timestep;
    
    //Angle that will be used in the calculations that moment
    public double theta;


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
}
