package EulerSolver;
import SolarSystem.CelestialBody;

public class Simulation {
    public static void planetarySetUp() {
        Functions functions = new Functions();
        CelestialBody.setupCelestialBodies();

        int t = 50;
        int timesPerYear = 31536000 / t;
        int framesPer10Seconds = timesPerYear / 100 + ((timesPerYear/t)%100);

        //State.printPositions();
        
        for(int j = 1; j < 9; j++)
        {
            State.setTimedPosition(CelestialBody.list[j]);
        }
        State.iterations++; 
                            //63072
        for(int i = 0 ; i < (timesPerYear); i++)
        {
            for(int j = 1; j < 12; j++)
            {
                State.setForce(j, functions.forceOnPlanet(CelestialBody.list[j]));
            }
    
            for(int j = 1; j < 12; j++)
            {

                State.setPosition(j, Solver.solve(CelestialBody.list[j], t));

                //this stores the positions of a planet 50 times a year
                if(j < 9 && i % framesPer10Seconds == 0)
                {
                    State.setTimedPosition(CelestialBody.list[j]);
                }
            }

            if(i % framesPer10Seconds == 0)
            {
                State.iterations++;
            }
        }

        double[] probePosition = State.getPosition(8);
        double[] titanPosition = State.getPosition(7);

        System.out.println("Probe:");
        System.out.println(Arrays.toString(probePosition));
        System.out.println("Titan:");
        System.out.println(Arrays.toString(titanPosition));

        double firstPart = Math.pow(probePosition[0] - titanPosition[0], 2) + Math.pow(probePosition[1] - titanPosition[1], 2) + Math.pow(probePosition[2] - titanPosition[2], 2);
        double distance = Math.sqrt(firstPart);
        System.out.println("Distance:");
        System.out.println(distance);
        //System.out.println("New Positions:");
        //State.printPositions();

        
    }

    public static void main(String[] args) {
        planetarySetUp();
    }
}
