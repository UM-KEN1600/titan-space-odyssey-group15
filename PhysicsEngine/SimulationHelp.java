package PhysicsEngine;

import java.util.Arrays;
import java.util.Scanner;

import SolarSystem.CelestialBody;
import SolarSystem.Spaceship;

public class SimulationHelp {
    public static void main(String[] args) {
      
    }


    public static double[][] planetarySetUp(double valuex, double valuey, double valuez) {
        State state = new State();
        Functions functions = new Functions();
        CelestialBody.setupCelestialBodies();

        State.velocities[8][0] = valuex;
        State.velocities[8][1] = valuey;
        State.velocities[8][2] = valuez;
        
        

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

                State.setPosition(j, EulerSolver.solve(CelestialBody.list[j], t));
                
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

        System.out.println("1 iteration");
        
        
        double[] probePosition = State.getPosition(8);
        double[] titanPosition = State.getPosition(7);
        double[][] twopositions = new double[2][3];
        twopositions[0] = probePosition;
        twopositions[1] = titanPosition;
        System.out.println(Arrays.deepToString(twopositions));
        
        return twopositions;

        /* 
        //Venus
        State.positions[1][0] = -28216773.9426889;
        State.positions[1][1] = 103994008.541512;
        State.positions[1][2] = 3012326.64296788;

        //Earth
        State.positions[2][0] = -148186906.893642;
        State.positions[2][1] = -27823158.5715694;
        State.positions[2][2] = 33746.8987977113;

        //Moon
        State.positions[3][0] = -148458048.395164;
        State.positions[3][1] = -27524868.1841142;
        State.positions[3][2] = 70233.6499287411;

        //Mars
        State.positions[4][0] = -159116303.422552;
        State.positions[4][1] = 189235671.561057;
        State.positions[4][2] = 7870476.08522969;

        //Jupiter
        State.positions[5][0] = 692722875.928222;
        State.positions[5][1] = 258560760.813524;
        State.positions[5][2] = -16570817.7105996;

        //Saturn
        State.positions[6][0] = 1253801723.95465;
        State.positions[6][1] = -760453007.810989;
        State.positions[6][2] = -36697431.1565206;

        //Titan
        State.positions[7][0] = 1254501624.95946;
        State.positions[7][1] = -761340299.067828;
        State.positions[7][2] = -36309613.8378104;

        //Spaceship
        State.positions[8][0] = -148186906.893642;
        State.positions[8][1] = -27829528.5715694;
        State.positions[8][2] = 33746.8987977113;

        //Mercury
        State.positions[9][0] = 7833268.43923962;
        State.positions[9][1] = 44885949.3703908;
        State.positions[9][2] = 2867693.20054382;
        
        //Neptune
        State.positions[10][0] = 4454487339.09447;
        State.positions[10][1] = -397895128.763904;
        State.positions[10][2] = -94464151.3421107;
        
        //Uranus
        State.positions[11][0] = 1958732435.99338; 
        State.positions[11][1] = 2191808553.21893;
        State.positions[11][2] = 17235283.8321992;
        

        //----Velocities -------------------------------------------
        //Sun first row all 0

        //Venus
        State.velocities[1][0] = -34.0236737066136;
        State.velocities[1][1] = -8.96521274688838;
        State.velocities[1][2] = 1.84061735279188;

        //Earth
        State.velocities[2][0] = 5.05251577575409;
        State.velocities[2][1] = -29.3926687625899;
        State.velocities[2][2] = 0.00170974277401292;

        //Moon
        State.velocities[3][0] = 4.34032634654904;
        State.velocities[3][1] = -30.0480834180741;
        State.velocities[3][2] = -0.0116103535014229;

        //Mars
        State.velocities[4][0] = -17.6954469224752;
        State.velocities[4][1] = -13.4635253412947;
        State.velocities[4][2] = 0.152331928200531;

        //Jupiter
        State.velocities[5][0] = -4.71443059866156;
        State.velocities[5][1] = 12.8555096964427;
        State.velocities[5][2] = 0.0522118126939208;

        //Saturn
        State.velocities[6][0] = 4.46781341335014;
        State.velocities[6][1] = 8.23989540475628;
        State.velocities[6][2] = -0.320745376969732;

        //Titan
        State.velocities[7][0] = 8.99593229549645;
        State.velocities[7][1] = 11.1085713608453;
        State.velocities[7][2] = -2.25130986174761;

        //Spaceship
        State.velocities[8][0] = 0;
        State.velocities[8][1] = 0;
        State.velocities[8][2] = 0;

        //Mercury
        State.velocities[9][0] = -57.4967480139828;
        State.velocities[9][1] = 11.52095127176;
        State.velocities[9][2] = 6.21695374334136;
        
        //Neptune
        State.velocities[10][0] = 0.447991656952326;
        State.velocities[10][1] = 5.44610697514907;
        State.velocities[10][2] = -0.122638125365954;
        
        //Uranus
        State.velocities[11][0] = -5.12766216337626;
        State.velocities[11][1] = 4.22055347264457;
        State.velocities[11][2] = 0.0821190336403063;

        */
        

    }
}

