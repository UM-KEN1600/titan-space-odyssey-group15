package PhysicsEngine.Controller;

public class FeedbackController implements iController{

    //Final Values needed
    //NOTE: Values are in km, not m. (Apart from angle)
    final double xFINAL = 0.0001;
    final double xVelocityFINAL = 0.0001;
    final double yVelocityFINAL = 0.0001;
    final double thetaFINAL = 0.02;
    final double angularVelocityFINAL = 0.01;

    //Max constraints for some values
    static final double g = 0.001352;
    static final double maxThrust = 10*g;
    static final double maxTorque = 1;


    //Current Values of the probe
    public double[] currentPosition;
    public double[] currentVelocity;


    //Timestep being used in the current instance
    public double timestep;
    
    //Angle that will be used in the calculations that moment
    public double theta;
    //Torque that will be used
    public double torque;

    //Position of Titan after one year, used for calculation of angle
    final double[] CENTER_OF_TITAN = {1.3680484627624216E9,-4.8546124152074784E8};


    @Override
    public double[][] getNextState(double[] currentVelocity, double[] currentPosition, double u, double v, double theta) {
        return new double[0][0];
    }

    public void xRotation(double newAngle){
        
    }

    public void yMovement(double ){

    }

    public double calculateAngleChangeTime(double newAngle){
        double angleChange = Math.abs(theta - newAngle);
        double time = angleChange / torque;
        return time;
    } 
    
    public static double XAcceleration(double angle){
        return maxThrust * Math.sin(angle);
    }

    public static double yAcceleration(double angle){
        return  (maxThrust * Math.cos(angle)) - g;
    }


}
