package PhysicsEngine;


public class Thrust {

    //Method to calculate the impulse in the timestep
    //Takes in an array of forces (a force vector) and the timestep being used
    public double[] impulse(double[] forceVector, double timestep){
        //does a check if the total force is bigger or not than the max force possible 
        if (VectorOperations.magnitude(forceVector[0], forceVector[1], forceVector[2]) > (3*10^7)){
            return null;
        }
        
        double[] impulse = new double[3];
        impulse[0] = (forceVector[0] * timestep)/50000;
        impulse[1] = (forceVector[1] * timestep)/50000;
        impulse[2] = (forceVector[2] * timestep)/50000;

        return impulse; //returns how much velocity has to be added 
    }

    public double fuelConsumption()
}
