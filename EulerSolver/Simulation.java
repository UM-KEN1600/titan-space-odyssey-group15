package EulerSolver;

import SolarSystem.CelestialBody;

public class Simulation {
    public static void main(String[] args) {
        State state = new State();
        Functions functions = new Functions();
        CelestialBody.setupCelestialBodies();

        double t = 0.1;


        State.printPositions();

        for(int i = 0 ; i < (31536000 / t); i++)
        {
            for(int j = 1; j < 8; j++)
            {
                State.setForce(j, functions.forceOnPlanet(CelestialBody.list[j]));
            }

            for(int j = 1; j < 8; j++)
            {
                State.setVelocity(j, functions.velocityOfBody(t, CelestialBody.list[j]));

                State.setPosition(j, functions.newPositionOfBody(t, CelestialBody.list[j]));
            }
            System.out.println("I wanna die");
        }

        System.out.println("New Positions:");

        State.printPositions();
        
    }
}
