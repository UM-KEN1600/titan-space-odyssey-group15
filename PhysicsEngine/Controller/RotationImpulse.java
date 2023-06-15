package PhysicsEngine.Controller;

import PhysicsEngine.States.RocketState;

public class RotationImpulse {

    private double angleOfRotation;
    private double angularVelocity;
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
    
    //Getter for the angle
    public double getAngle(){
        return angleOfRotation;
    }

    //Setter for the angle
    public void setAngle(double newAngle){
        angleOfRotation = newAngle;
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


    /**
     * Rotates the spacecraft to the desired angle
     * @param newAngle
     */
    public void xRotation(double newAngle){
        //calculates half the newAngle since the rotation will have 2 phases (acceleration and deceleration)
        double halfAngle = newAngle/2;
        
        //calculates the full amount of acceleration time needed
        double averageAccelerationAngle = Math.ceil(halfAngle);
        double accelerationTime = calculateAccelerationTime(averageAccelerationAngle);
        double decelerationTime = accelerationTime;

        //calculates the average acceleration that will be used in the rotation
        double acceleration = calculateAcceleration(halfAngle, accelerationTime);
        double deceleration = -acceleration;

        //does the rotation on the spacecraft
        rotation(halfAngle, accelerationTime, acceleration);
        rotation(halfAngle, decelerationTime, deceleration);
    }

    /**
     * Calculates the necessary amount of time to rotate the spacecraft at full acceleration
     * @param newAngle the new angle that you want the rocket to face 
     * @return the amount of time that the rocket has to accelerate to achieve the necessary velocity
     */
    public double calculateAccelerationTime(double newAngle){
        double time = Math.sqrt(newAngle);
        return time;
    }

    /**
     * Calculates the necessary acceleration to rotate the rocket at for it to reach the necessary angle in the specified amount of time
     * @param newAngle the new angle that you want the rocket to face 
     * @param time the amount of time that the rocket has to turn in
     * @return the acceleration needed 
     */
    public double calculateAcceleration(double newAngle, double time){
        double acceleration = newAngle / time;
        return acceleration;
    }

    /**
     * Calculates the necessary acceleration to rotate the rocket at for it to reach the necessary angle in the specified amount of time
     * @param newAngle the new angle that you want the rocket to face 
     * @param time the amount of time that the rocket has to turn in
     * @param torque the amount of torque that the spacecraft will use
     */
    public void rotation(double newAngle, double time, double torque){

        for(int t = 0; t < time; t++){

            //calculates the new angular velocity
            //angular velocity = angular velocity * time
            double oldAngularVelocity = angularVelocity;
            angularVelocity += torque;

            //calculates the new displacement using the change in angular velocity
            //change in angular velocity = (initial angular velocity + angular velocity) / 2
            double changeInAngularVelocity = (oldAngularVelocity + angularVelocity) / 2;
            // theta = change in angular velocity / time
            angleOfRotation += changeInAngularVelocity;
        }
        RocketState.getInstance().setAngle(angleOfRotation);
    }

}
