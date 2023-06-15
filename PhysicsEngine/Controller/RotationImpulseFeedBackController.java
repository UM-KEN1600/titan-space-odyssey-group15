package PhysicsEngine.Controller;

public class RotationImpulseFeedBackController {

    private double targetAngle;
    private double torque;
    private int time;

    public final double VMAX = 60;
    public final int maxTimeRotation = 3;


    public RotationImpulseFeedBackController(double angle, double torque)
    {
        targetAngle = angle;
        this.torque = torque;
    }

    public void setTargetAngle(double angle){
        targetAngle = angle;
    }

    private double calculateVMax(double angle, double t){
        return angle/Math.pow(t,2);
    }

    private int calculateTimeNeeded(double targetAngle){

        double intermediateAngle = targetAngle/2;

        for (int t = 0; t < maxTimeRotation; t++) {
            if(intermediateAngle/t <= VMAX){
                return t;
            }
        }
        return 0;
    }
    public void changeAngle(){

        time = calculateTimeNeeded(targetAngle);
        torque = calculateVMax(targetAngle, calculateTimeNeeded(targetAngle));
    }


}
