package PhysicsEngine.Controller;

public class RotationImpulseOPC {

    private double targetAngle;
    private double torqueAcceleration;
    private double torqueDeceleration;
    private int startTimeTorqueAcceleration;
    private int startTimeTorqueDeceleration;
    private int time;
    public final double VMAX = 60;
    public final int maxTimeRotation = 3;



    public RotationImpulseOPC(double targetAngle)
    {
        this.targetAngle = targetAngle;
        changeAngle();
    }

    public void setTargetAngle(double angle){
        targetAngle = angle;
    }




    /**
     Calculates the time which is needed to do the acceleration period to change the rocket for a given angle. This acceleration will also be
     needed to slow to rotation down again to reach the desired angle.So in conclusion we first accelerate and then decelerate again to reach the desired angle
     we would like the rocket to have.
     * @param targetAngle the new angle that you want the rocket to face
     * @return the time needed to do one of the two operations
     */
    private int calculateTimeNeeded(double targetAngle){

        double intermediateAngle = targetAngle/2;

        for (int t = 0; t < maxTimeRotation; t++) {
            if(intermediateAngle/t <= VMAX){
                return t;
            }
        }
        return 0;
    }


    /**
     Calculates acceleration that is needed to rotate the rocket in a specific time to a given angle.
     * @param targetAngle the new angle that you want the rocket to face
     * @param t the amount of time that the rocket has to turn in
     * @return
     */
    private double calculateVMax(double targetAngle, double t){
        double intermediateAngle = targetAngle/2;
        return intermediateAngle/Math.pow(t,2);
    }


    /**
     Calculates acceleration and deceleration to rotate the rocket to the given angle.
     * @return
     */
    public void changeAngle(){

        time = calculateTimeNeeded(targetAngle);
        startTimeTorqueDeceleration = time;
        torqueAcceleration = calculateVMax(targetAngle, calculateTimeNeeded(targetAngle));
        torqueDeceleration = - torqueAcceleration;
    }


}