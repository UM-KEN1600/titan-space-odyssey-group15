package EulerSolver;

import SolarSystem.CelestialBody;

public class Simulation {
    public static void main(String[] args) {
        State state = new State();
        Functions functions = new Functions();
        CelestialBody.setupCelestialBodies();

        double t = 50;

        double[] a = {1,2,3};
        double b = 0;
        b = VectorOperations.euclideanForm(a, VectorOperations.vectorScalarMultiplication(a, 2));

        for(int i = 0; i < 3; i++)
        {
            System.out.println(a[i]);
        }

        //System.out.println(b);

        State.printPositions();

        for(int i = 0 ; i < (31536000 / t); i++)
        {
            for(int j = 1; j < 11; j++)
            {
                State.setForce(j, functions.forceOnPlanet(CelestialBody.list[j]));
            }
    
            for(int j = 1; j < 11; j++)
            {
                State.setVelocity(j, functions.velocityOfBody(t, CelestialBody.list[j]));

                State.setPosition(j, functions.newPositionOfBody(t, CelestialBody.list[j]));
            }
            //System.out.println("I wanna die");
        }

        System.out.println("New Positions:");

        State.printPositions();
        
    }
}
