package EulerSolver;

import SolarSystem.CelestialBody;

public class Simulation {
    public static void planetarySetUp() {
        State state = new State();
        Functions functions = new Functions();
        CelestialBody.setupCelestialBodies();

        double t = 50;

        //State.printPositions();
        
        for(int j = 1; j < 8; j++)
        {
            State.setTimedPosition(CelestialBody.list[j]);
        }
        State.iterations++; 
                            //63072
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

                //this stores the positions of a planet 50 times a year
                if(j < 8 && i % 12616 == 0)
                {
                    State.setTimedPosition(CelestialBody.list[j]);
                }
            }

            if(i % 12616 == 0)
            {
                State.iterations++;
            }
        }

        System.out.println("New Positions:");

        //State.printPositions();
        
    }

    public static void main(String[] args) {
        planetarySetUp();
    }
}
