package PhysicsEngine.Controller;

import java.util.Arrays;

import PhysicsEngine.Operations.VectorOperations;

public class MainThrusterImpulse {

    double thrust;
    double[] currentVelocity;

    public MainThrusterImpulse(double thrust, double[] currentVelocity)
    {
        this.thrust = thrust;
        this.currentVelocity = currentVelocity;
    }

    /**
     * @return the change in velocity when applying the thrusters. Adds acceleration to the same direction.
     */
    public double[] changeInVelocity()
    {
        double h = Math.sqrt(Math.pow(currentVelocity[0], 2) + Math.pow(currentVelocity[1], 2));

        h += thrust;


        double a = Math.cos(calculateAngle(currentVelocity, new double[]{10,0})) * h;

        double b = Math.sin(calculateAngle(currentVelocity, new double[]{10,0})) * h;

        double[] velocity = new double[2];
        velocity[0] = a;
        velocity[1] = b;

        return velocity;
    }

    private double calculateAngle(double[] vectorA, double[] vectorB)
    {
        double dotProduct = VectorOperations.dotProduct(vectorA,vectorB);
        double aMag = VectorOperations.magnitude(vectorA);
        double bMag = VectorOperations.magnitude(vectorB);

        double check = vectorA[0]*vectorB[1] - vectorA[0]*vectorB[0];
        
        if(check < 0)
        {
            return 2*Math.PI - Math.acos(dotProduct/(aMag * bMag));
        }

        return Math.acos(dotProduct/(aMag * bMag));
    }
}
