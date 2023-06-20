package PhysicsEngine.Controller;

import java.util.Arrays;

import PhysicsEngine.Operations.VectorOperations;

public class MainThrusterImpulse {

    private double thrust;
    private double startTimeOfImpulse;
    private double EndTimeOfPulse;
    double[] currentVelocity;

    public MainThrusterImpulse(double timeOfImpulse, double thrust, double[] currentVelocity)
    {
        this.thrust = thrust;
        this.currentVelocity = currentVelocity;
        this.startTimeOfImpulse = timeOfImpulse;
    }

    /**
     * @return the change in velocity when applying the thrusters. Adds acceleration to the same direction.
     */
    public double[] changeInVelocity()
    {
        double h = Math.sqrt(Math.pow(currentVelocity[0], 2) + Math.pow(currentVelocity[1], 2));

        h += thrust;


        double a = Math.cos(VectorOperations.calculateAngle(currentVelocity, new double[]{10,0})) * h;

        double b = Math.sin(VectorOperations.calculateAngle(currentVelocity, new double[]{10,0})) * h;

        double[] velocity = new double[2];
        velocity[0] = a;
        velocity[1] = b;

        return velocity;
    }

    public double getEndTimeOfPulse() {
        return EndTimeOfPulse;
    }

    public double getStartTimeOfImpulse()
    {
        return startTimeOfImpulse;
    }

    public double getThrust() {
        return thrust;
    }
}
