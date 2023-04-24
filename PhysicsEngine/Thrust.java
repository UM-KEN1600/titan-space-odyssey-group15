package PhysicsEngine;


public class Thrust {

    //Method to calculate the impulse in the timestep
    //Takes in an array of forces (a force vector) and the timestep being used
    public double impulse(double[] forceVector, double timestep){
        //does a check if the total force is bigger or not than the max force possible 
        if (VectorOperations.magnitude(forceVector[0], forceVector[1], forceVector[2]) > (3*10^7)){
            return -1;
        }
        double impulse = (3*10^7)*timestep;
        return impulse/50000; //returns how much velocity has to be added 
    }
}
