package PhysicsEngine;

import java.util.Arrays;

public class HillClimbOneIteration {

    double[] velocity;
    int axis;
    double currentDistance;
    int numNeighbors; //should be an even number
    double changePercentage;
    final double adjustmentStepSize;

    double adjustmentNumber;
    double[] probePosition = new double[3];
    double[] titanPosition = new double[3];



    public HillClimbOneIteration(double[] initialVelocity, double currentDistance,int axis,int numNeighbors,double adjustmentStepSize, double adjustmentNumber){
        this.velocity = initialVelocity;
        this.axis = axis;
        this.numNeighbors = numNeighbors;
        this.adjustmentStepSize = adjustmentStepSize;
        this.adjustmentNumber = adjustmentNumber;
        this.currentDistance = currentDistance;
        this.changePercentage = percentageAdjustment(adjustmentStepSize);
    }


    public double VelocityFinder(double[] currentVelocity, double currentDistance) {


        velocity = Arrays.copyOf(currentVelocity,currentVelocity.length);
        this.currentDistance = currentDistance;

        double[][] neighborVelocities;

        neighborVelocities = neighborVelocities();

        double[] distanceAndIndex = getBest(neighborVelocities);

        if(distanceAndIndex[0]<currentDistance){

            currentDistance = distanceAndIndex[0];
            velocity = Arrays.copyOf(neighborVelocities[(int)distanceAndIndex[1]],3);
            currentVelocity[0] = velocity[0];
            currentVelocity[1] = velocity[1];
            currentVelocity[2] = velocity[2];

        }
        else{
            changePercentage = percentageAdjustment(adjustmentStepSize);

            System.out.println("new change Percentage for axis: " + axis );
            System.out.println(changePercentage);
        }
        return currentDistance;

    }

    public double[][] neighborVelocities(){

        double [][] neighbors =  new double[numNeighbors][3];

        for (int i = 0; i < neighbors.length; i++) {
            neighbors[i] = Arrays.copyOf(velocity, velocity.length);
        }

        for (int i = 0; i < numNeighbors/2; i++) {
            neighbors[i][axis] =  ((1+(i+1)*changePercentage)*velocity[axis]);
        }

        int count = 1;

        for (int j = numNeighbors/2; j < numNeighbors ; j++) {
            neighbors[j][axis] = ((1 - count * changePercentage) * velocity[axis]);
            count++;
        }
        return neighbors;
    }

    public double runTest(double[] velocity){

        SimulationHelpNew simulation = new SimulationHelpNew(50);
        double[][] positions = simulation.planetarySetUp(velocity[0], velocity[1], velocity[2]);
        probePosition = positions[0];
        titanPosition = positions[1];

        return getDistance(probePosition, titanPosition);
    }

    public double getDistance(double[] probePosition, double[] titanPosition){
        return VectorOperations.euclideanForm(probePosition, titanPosition);
    }

    public double[] getBest(double[][] velocities){

        double bestIndex = 0;
        double bestDistance = currentDistance;
        double distanceCurrentNeighbor;

        for (int index = 1; index < numNeighbors; index++) {

            distanceCurrentNeighbor = runTest(velocities[index]);
            System.out.println("Velocities belonging to the distance:");
            System.out.println(Arrays.toString(velocities[index]));

            if(distanceCurrentNeighbor< bestDistance){
                bestIndex = index;
                bestDistance = distanceCurrentNeighbor;
            }
        }

        double[] distanceAndIndex = new double[2];
        distanceAndIndex[0] = bestDistance;
        distanceAndIndex[1] = bestIndex;

        return distanceAndIndex;
    }

    public double percentageAdjustment(double adjustmentStepSize){
        System.out.println("adjustNumber: "+ adjustmentNumber);
        double percentageChangeNew = Math.exp(-adjustmentNumber);
        adjustmentNumber = adjustmentNumber + adjustmentStepSize;


        return percentageChangeNew;
    }
}
