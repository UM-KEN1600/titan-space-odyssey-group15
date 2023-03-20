package EulerSolver;

import SolarSystem.CelestialBody;

public class Simulation {
    public static void main(String[] args) {
        State state = new State();
        Functions functions = new Functions();
        CelestialBody.setupCelestialBodies();

        double t = 60;

        for(int i = 0; i < State.positions.length; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                System.out.println(State.positions[i][j] + " ");
            }

            System.out.println("");
        }

        for(int i = 0 ; i < (31536000 / t); i++)
        {
            for(int j = 1; j < State.forces.length -1; j++)
            {
                State.forces[j] = functions.forceOnPlanet(CelestialBody.list[j]);
            }

            for(int j = 1; j < State.forces.length -1; j++)
            {
                State.velocities[j] = functions.velocityOfBody(t, CelestialBody.list[j]);

                State.positions[j] = functions.newPositionOfBody(t, CelestialBody.list[j]);
            }
        }

        System.out.println("New Positions:");

        for(int i = 0; i < State.positions.length; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                System.out.println(State.positions[i][j] + " ");
            }
            System.out.println("");
        }
        
    }
}
