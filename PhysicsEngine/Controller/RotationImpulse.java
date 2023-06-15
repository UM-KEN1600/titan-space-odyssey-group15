package PhysicsEngine.Controller;

public class RotationImpulse {

    private double angleOfRotation;
    private double torque;

    public final double VMAX = 60;
    public final int maxTimeRotation = 3;

    /**
     * A Rotation Impulse can be used to store the combination of an angle with a given torque
     * This allows the calculation of how long the rotation will take, how far it will overshoot,
     * and how to correct the overshoot.
     * @param angle
     * @param torque
     */
    public RotationImpulse(double angle, double torque)
    {
        angleOfRotation = angle;
        this.torque = torque;
    }

    private double calculateVMax(double angle, double t){
        return angle/Math.pow(t,2);
    }

    private double calculateTimeNeeded(double targetAngle){

        double intermediateAngle = targetAngle/2;

        for (int t = 0; t < maxTimeRotation; t++) {
            if(intermediateAngle/t <= VMAX){
               return t;
            }
        }
        return 0;
    }


}
