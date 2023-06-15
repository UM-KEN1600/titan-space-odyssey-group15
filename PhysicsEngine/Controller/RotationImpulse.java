package PhysicsEngine.Controller;

public class RotationImpulse {

    private double angleOfRotation;
    private double torque;

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

    public double getTimeNeededForRotation()
    {
        return angleOfRotation/torque;
    }

    /**
     * calculates the angle that is overshot based on the time needed to rotate, since we can only
     * check the angle every timestep we do calculations, if we use the maxTorque, the
     * rotation will never be perfect
     * @return
     */
    private double getOvershoot()
    {
        return torque - angleOfRotation%torque;
    }

    /**
     * rotates the overshoot back
     * @return torque needed to correct the overshoot
     */
    public double correctOverShoot()
    {
        return getOvershoot()*-1.0;
    }

    /**
     * stops correction of overshoot
     * @return torque needed to stand still in wanted angle
     */
    public double standStill()
    {
        return correctOverShoot()*-1.0;
    }
}
