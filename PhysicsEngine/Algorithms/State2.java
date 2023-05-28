package PhysicsEngine.Algorithms;

import java.awt.Color;
import java.io.ObjectInputStream.GetField;

import SolarSystem.CelestialBody;

/**
 * Holds a matrix with the position and velocity of each celestial body: Sun, Venus, Earth, Moon, Mars, Jupiter, Saturn, Titan and Spaceship respectively
 * 
 * THIS VERSION IS USED ONLY FOR CALCULATING THE WAY BACK FOR THE OPTIMIZATION  ALGOS
 * 
 * NOT USED FOR THE FINAL SIMULATION
 */
public class State2 {

    //change spaceship starting coordinates and veloctiy here
    double xCoor = 1.3680508018697374E9;
    double yCoor = -4.855728994424407E8;
    double zCoor = -4.645687646606402E7;

    double velocity1 = 39.45222033690388;//35;
    double velocity2 = -22.183531305965207;//-55;
    double velocity3 = 0.9081228937639554;//0

    public static double[][] positions = new double[12][3];
    public static double[][] velocities = new double[12][3];
    public static double[][] forces = new double[12][3];

    public double[][][] state = new double[12][2][3];

    public double fuelConsumption = 0;

    //frames which are getting displayed all 10 seconds
    static int framesPer10Seconds = 100;
    //used to store the positions 50 times a year
    public static double[][][] allPositions = new double[9][framesPer10Seconds][2]; 
    public static int iterations = 0;

    /**
     * updates the allPositions array with new coordinates of the celestial bodies
     * @param body the celestial body whose coordinates are being updated
     */
    public void setTimedPosition()
    {
        for(int i = 0; i < 9; i++)
        {
            if (iterations < framesPer10Seconds) 
            {
                allPositions[i][iterations][0] = state[i][0][0];
                allPositions[i][iterations][1] = state[i][0][1];
            }
        }

        iterations++;
    }

    public double[][][] getState()
    {
        return state;
    }

    public void setState(double[][][] newState)
    {
        this.state = newState;
    }

    public static double[] getPosition(int row){
        return positions[row];
    }

    public static double[] getVelocity(int row){
        return velocities[row];
    }

    public static double[] getForce(int row){
        return forces[row];
    }

    public static void setPosition(int row, double[] position)
    {
        positions[row] = position;
    }

    public static void setVelocity(int row, double[] velocity)
    {
        velocities[row] = velocity;
    }

    public static void setForce(int row, double[] force)
    {
        forces[row] = force;
    }
    
    public void printPositions()
    {
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                System.out.println(state[i][0][j] + " ");
            }

            System.out.println("");
        }
    }

    public State2()
    {
        //Venus
        state[1][0][0] = 8.786210545272186E7;
        state[1][0][1] = -6.3920574015031755E7;
        state[1][0][2] = -5911587.924524576;

        state[1][1][0] = 20.19556029716628;
        state[1][1][1] = 28.48454462985651;
        state[1][1][2] = -0.7834067067628583;

        //earth
        state[2][0][0] = -1.5027709535737002E8;
        state[2][0][1] = -569930.598550442;
        state[2][0][2] = 30195.915016460207;

        state[2][1][0] = -0.2841053996517835;
        state[2][1][1] = -29.906532040009473;
        state[2][1][2] = 0.002589709697798522;
       
        //Moon
        state[3][0][0] = -1.501742248343446E8;
        state[3][0][1] = -925412.4678345652;
        state[3][0][2] = -3908.6943484025464;

        state[3][1][0] = 0.7050740922900979;
        state[3][1][1] = -29.55324556689985;
        state[3][1][2] = 0.008800072901348837;

        //Mars
        state[4][0][0] = 1.3287445415901673E8;
        state[4][0][1] = -1.6329225530018702E8;
        state[4][0][2] = -6683545.951956124;

        state[4][1][0] = 19.538532804612547;
        state[4][1][1] = 17.329677531006013;
        state[4][1][2] = -0.11666207233581659;

        //Jupiter
        state[5][0][0] = 4.4255657773720896E8;
        state[5][0][1] = 6.01814062963568E8;
        state[5][0][2] = -1.2395958192284402E7;

        state[5][1][0] = -10.687682970500934;
        state[5][1][1] =  8.341623865883939;
        state[5][1][2] =  8.341623865883939;

        //Saturn
        state[6][0][0] = 1.36735874905949E9;
        state[6][0][1] = -4.8628336837575215E8;
        state[6][0][2] = -4.5984794469409786E7;

        state[6][1][0] = 2.6970727847220504;
        state[6][1][1] = 9.083364436676955;
        state[6][1][2] = -0.26495964932576377;

        //Titan

        state[7][0][0] = 1.3680484627624216E9;
        state[7][0][1] = -4.8546124152074784E8;
        state[7][0][2] = -4.647756328568933E7;

        state[7][1][0] = -1.8157884054235318;
        state[7][1][1] = 12.341659587185951;
        state[7][1][2] = -1.495607469905818;

        //Spaceship
        state[8][0][0] = xCoor;
        state[8][0][1] = yCoor;
        state[8][0][2] = zCoor;

        state[8][1][0] = velocity1;
        state[8][1][1] = velocity2;
        state[8][1][2] = velocity3;

        //Mercury
        state[9][0][0] = -5.9161904077430196E7;
        state[9][0][1] = -1.6997748891509607E7;
        state[9][0][2] = 4092580.4179125316;

        state[9][1][0] = 4.146322816205533;
        state[9][1][1] = -43.85648571331846;
        state[9][1][2] = -3.8887122376669327;

        //Neptune
        state[10][0][0] = 4.465323253961243E9;
        state[10][0][1] = -2.2583934628115737E8;
        state[10][0][2] = -9.826213107943574E7;

        state[10][1][0] = 0.2389567977881779;
        state[10][1][1] = 5.460745733981066;
        state[10][1][2] = -0.11812204749546534;

        //Uranus
        state[11][0][0] = 1.79200263428518E9; 
        state[11][0][1] = 2.319109633732723E9;
        state[11][0][2] = 1.97784088672171E7;

        state[11][1][0] = -5.438748602589186;
        state[11][1][1] = 3.8461501804812035;
        state[11][1][2] = 0.0790377318005654;

        //----Positions -------------------------------------
        //Sun is first row :)
    
        //Venus
        positions[1][0] = -28216773.9426889;
        positions[1][1] = 103994008.541512;
        positions[1][2] = 3012326.64296788;

        //Earth
        positions[2][0] = -148186906.893642;
        positions[2][1] = -27823158.5715694;
        positions[2][2] = 33746.8987977113;

        //Moon
        positions[3][0] = -148458048.395164;
        positions[3][1] = -27524868.1841142;
        positions[3][2] = 70233.6499287411;

        //Mars
        positions[4][0] = -159116303.422552;
        positions[4][1] = 189235671.561057;
        positions[4][2] = 7870476.08522969;

        //Jupiter
        positions[5][0] = 692722875.928222;
        positions[5][1] = 258560760.813524;
        positions[5][2] = -16570817.7105996;

        //Saturn
        positions[6][0] = 1253801723.95465;
        positions[6][1] = -760453007.810989;
        positions[6][2] = -36697431.1565206;

        //Titan
        positions[7][0] = 1254501624.95946;
        positions[7][1] = -761340299.067828;
        positions[7][2] = -36309613.8378104;

        //Spaceship
        positions[8][0] = -148186906.893642;
        positions[8][1] = -27829528.5715694;
        positions[8][2] = 33746.8987977113;

        //Mercury
        positions[9][0] = 7833268.43923962;
        positions[9][1] = 44885949.3703908;
        positions[9][2] = 2867693.20054382;
        
        //Neptune
        positions[10][0] = 4454487339.09447;
        positions[10][1] = -397895128.763904;
        positions[10][2] = -94464151.3421107;
        
        //Uranus
        positions[11][0] = 1958732435.99338; 
        positions[11][1] = 2191808553.21893;
        positions[11][2] = 17235283.8321992;
        

        //----Velocities -------------------------------------------
        //Sun first row all 0

        //Venus
        velocities[1][0] = -34.0236737066136;
        velocities[1][1] = -8.96521274688838;
        velocities[1][2] = 1.84061735279188;

        //Earth
        velocities[2][0] = 5.05251577575409;
        velocities[2][1] = -29.3926687625899;
        velocities[2][2] = 0.00170974277401292;

        //Moon
        velocities[3][0] = 4.34032634654904;
        velocities[3][1] = -30.0480834180741;
        velocities[3][2] = -0.0116103535014229;

        //Mars
        velocities[4][0] = -17.6954469224752;
        velocities[4][1] = -13.4635253412947;
        velocities[4][2] = 0.152331928200531;

        //Jupiter
        velocities[5][0] = -4.71443059866156;
        velocities[5][1] = 12.8555096964427;
        velocities[5][2] = 0.0522118126939208;

        //Saturn
        velocities[6][0] = 4.46781341335014;
        velocities[6][1] = 8.23989540475628;
        velocities[6][2] = -0.320745376969732;

        //Titan
        velocities[7][0] = 8.99593229549645;
        velocities[7][1] = 11.1085713608453;
        velocities[7][2] = -2.25130986174761;

        //Spaceship
        velocities[8][0] = 46.41;
        velocities[8][1] = 37.95;
        velocities[8][2] = 2.0;

        //Mercury
        velocities[9][0] = -57.4967480139828;
        velocities[9][1] = 11.52095127176;
        velocities[9][2] = 6.21695374334136;
        
        //Neptune
        velocities[10][0] = 0.447991656952326;
        velocities[10][1] = 5.44610697514907;
        velocities[10][2] = -0.122638125365954;
        
        //Uranus
        velocities[11][0] = -5.12766216337626;
        velocities[11][1] = 4.22055347264457;
        velocities[11][2] = 0.0821190336403063;
    }
}
