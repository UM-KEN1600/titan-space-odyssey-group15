package PhysicsEngine;

import java.util.Arrays;

public class HillClimbAllAxes {


    HillClimbOneIteration climbX;
    HillClimbOneIteration climbY;
    HillClimbOneIteration climbZ;


    //best use 6 for adjustmentNumber and 0.1 for adjustmentStepSize
    HillClimbAllAxes(double[] initialVelocity, double currentDistance, int numNeighbors, double adjustmentStepSize, double adjustmentNumber) {


        climbX = new HillClimbOneIteration(initialVelocity,currentDistance,0,numNeighbors,adjustmentStepSize,adjustmentNumber);

        climbY = new HillClimbOneIteration(initialVelocity, currentDistance, 1, numNeighbors,adjustmentStepSize,adjustmentNumber);

        climbZ = new HillClimbOneIteration(initialVelocity, currentDistance, 2, numNeighbors,adjustmentStepSize,adjustmentNumber);
    }

    public double RunAllAxesOnce(double[] velocities, double currentDistance){

        System.out.println("Changes for X\n");
        currentDistance = climbX.VelocityFinder(velocities,currentDistance);
        System.out.println("\n" + Arrays.toString(velocities));
        System.out.println("Current distance  after X is:"+ currentDistance + "\n");

        System.out.println("Changes for Y\n");
        currentDistance = climbY.VelocityFinder(velocities,currentDistance);
        System.out.println(Arrays.toString(velocities));
        System.out.println("Current distance after Y is:"+ currentDistance + "\n");

        System.out.println("Changes for Z\n");
        currentDistance= climbZ.VelocityFinder(velocities,currentDistance);
        System.out.println(Arrays.toString(velocities));
        System.out.println("Current distance after Z is:"+ currentDistance + "\n");

        return currentDistance;
    }


    public static void main(String[] args) {

        boolean goal = false;
        double target = 300000;
        double[] velocity = {49.606555730731216, 38.223000000000006, 1.9999999999999996};
        double currentDistance = 2673178.0295476587;
        HillClimbAllAxes climbAll = new HillClimbAllAxes(velocity, currentDistance, 8, 0.1, 7);


        while (!goal) {

            if (currentDistance < target) {
                goal = true;
            } else {

                currentDistance = climbAll.RunAllAxesOnce(velocity, currentDistance);
                System.out.println(Arrays.toString(velocity));
                System.out.println("Current distance is:" + currentDistance);
            }
        }


    }

}
