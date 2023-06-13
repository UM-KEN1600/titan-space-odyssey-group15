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
    
    //Angle of the probe
    public double currentAngle;
    //Torque that will be used
    public double torque; //rad s^2

    //Position of Titan after one year, used for calculation of angle
    final double[] CENTER_OF_TITAN = {1.3680484627624216E9,-4.8546124152074784E8};

    public FeedbackController(double torque){
        this.torque = torque;
    }
    @Override
    public double[][] getNextState(double[] currentVelocity, double[] currentPosition, double u, double v, double theta) {
        return new double[0][0];
    }

    public void xRotation(double newAngle){
        double turnTime = calculateAngleChangeTime(newAngle);
        setAngle(newAngle);
        double xComponentAcceleration = xAcceleration(newAngle);
        //modify the x component with timestep and the Xacceleration
    }

    public void yMovement(double ){

    }

    public double calculateAngleChangeTime(double newAngle){
        double angleChange = Math.abs(currentAngle - newAngle);
        double time = angleChange / torque;
        return time;
    } 
    
    public static double xAcceleration(double angle){
        return maxThrust * Math.sin(angle);
    }

    public static double yAcceleration(double angle){
        return  (maxThrust * Math.cos(angle)) - g;
    }

    public void setAngle(double newAngle){
        currentAngle = newAngle;
    }

    public double necessaryAngle(){
        
    }


}
