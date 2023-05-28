package PhysicsEngine;
import java.util.Arrays;

public class HillClimb {
    
    double[] velocity;
    int axis;
    double target = 300000;
    double currentDistance;
    int numNeighbors; //should be an even number
    double changePercentage;
    final double adjustmentStepSize;

    double adjustmentNumber;
    double[] probePosition = new double[3];
    double[] titanPosition = new double[3];




    public static void main(String[] args) {
    double[] initialVelocity = {49.606555730731216, 38.223000000000006, 1.9999999999999996};
    double distance = 2673178.0295476587;

    HillClimb climb = new HillClimb(initialVelocity,distance,1, 8,0.1,7);

    System.out.println(Arrays.toString(climb.startVelocityFinder()));

    }


    public HillClimb(double[] initialVelocity, double currentDistance,int axis,int numNeighbors,double adjustmentStepSize, double adjustmentNumber){
        this.velocity = initialVelocity;
        this.axis = axis;
        this.numNeighbors = numNeighbors;
        this.adjustmentStepSize = adjustmentStepSize;
        this.adjustmentNumber = adjustmentNumber;
        this.currentDistance = currentDistance;
        this.changePercentage = percentageAdjustment(adjustmentStepSize);
    }


    public double[] startVelocityFinder() {

        boolean goal = false;
        double[][] neighborVelocities;
        

        while(!goal){

            if(currentDistance<target){
                goal = true;
            }
            else{

                neighborVelocities = neighborVelocities();
                
                double[] distanceAndIndex = getBest(neighborVelocities);

                if(distanceAndIndex[0]<currentDistance){

                    currentDistance = distanceAndIndex[0];
                    velocity = neighborVelocities[(int)distanceAndIndex[1]];
                }
                else{
                    changePercentage = percentageAdjustment(adjustmentStepSize);
                    System.out.println("new change Percentage: ");
                    System.out.println(changePercentage);
                }        
            }
            System.out.println("\n");
            System.out.println(Arrays.toString( velocity));
            System.out.println("Current distance is:"+ currentDistance + "\n");
            
        }

        return velocity;
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
        double bestDistance = runTest(velocities[(int) (bestIndex)]);
        double distanceCurrentNeighbor;

        for (int index = 1; index < numNeighbors; index++) {
        
            distanceCurrentNeighbor = runTest(velocities[index]);
            System.out.println("Velocities belonging to the distance:");
            System.out.println(Arrays.toString(velocities[index]));
            System.out.println("\n");

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
       double percentageChangeNew = Math.exp(-adjustmentNumber);
       adjustmentNumber += adjustmentStepSize;

        System.out.println("Percentage got changed");
        System.out.println(percentageChangeNew);

        return percentageChangeNew;
    }

    public static void print2DArray(double[][] array) {
        for (double[] row : array) {
            for (double element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
}
    


    
