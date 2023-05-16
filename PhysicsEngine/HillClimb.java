package PhysicsEngine;

import java.time.LocalDate;

public class HillClimb {
    
    double[] velocity;
    char axis;






    HillClimb(double[] initialVelocity,char axis){
        velocity = initialVelocity;
        this.axis = axis;
    }








    public double runTest(double[] velocity){
        
        double[][] positions = SimulationHelp.planetarySetUp(velocity[0], velocity[1], velocity[2]);
        double[] probePosition = positions[0];
        double[] titanPosition = positions[1];

        return getFitness(probePosition, titanPosition);
    }
}
