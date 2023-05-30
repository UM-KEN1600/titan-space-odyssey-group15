package PhysicsEngine.States;

import java.awt.Color;
import java.io.ObjectInputStream.GetField;

import SolarSystem.CelestialBody;

/**
 * Holds a matrix with the position and velocity of each celestial body: Sun, Venus, Earth, Moon, Mars, Jupiter, Saturn, Titan and Spaceship respectively
 */
public class State {

    //change spaceship starting coordinates and veloctiy here
    double xCoor = -148186906.893642;
    double yCoor = -27829528.5715694;
    double zCoor = 33746.8987977113;
    
    double velocity1 = 0;//49.58313440693111;//35;
    double velocity2 = 0;//38.29506290304066;//-55;
    double velocity3 = 0;//1.9666588900013093;//0

    public static double[][] positions = new double[12][3];
    public static double[][] velocities = new double[12][3];
    public static double[][] forces = new double[12][3];

    public double[][][] state = new double[12][2][3];

    public double fuelConsumption = 0;

    //frames which are getting displayed all 10 seconds
    static int framesPer10Seconds = 200;
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

    public void setSpaceshipVelocity(double[] newVelocity)
    {
        state[8][1] = newVelocity;
    }

    public double[] getSpaceshipVelocity()
    {
        return state[8][1];
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

    public double getFuelConsumption(){
        return fuelConsumption;
    }
    
    /**
     * Prints positions of all celestial bodies
     */
    public void printPositions()
    {
        for(int i = 0; i < 12; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                System.out.println(state[i][0][j] + " ");
            }

            System.out.println("");
        }
    }

    public void printVelocities()
    {
        for(int i = 0; i < 12; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                System.out.println(state[i][1][j] + " ");
            }

            System.out.println("");
        }
    }

    public State()
    {
        //Venus
        state[1][0][0] = -28216773.9426889;
        state[1][0][1] = 103994008.541512;
        state[1][0][2] = 3012326.64296788;

        state[1][1][0] = -34.0236737066136;
        state[1][1][1] = -8.96521274688838;
        state[1][1][2] = 1.84061735279188;

        //earth
        state[2][0][0] = -148186906.893642;
        state[2][0][1] = -27823158.5715694;
        state[2][0][2] = 33746.8987977113;

        state[2][1][0] = 5.05251577575409;
        state[2][1][1] = -29.3926687625899;
        state[2][1][2] = 0.00170974277401292;
       
        //Moon
        state[3][0][0] = -148458048.395164;
        state[3][0][1] = -27524868.1841142;
        state[3][0][2] = 70233.6499287411;

        state[3][1][0] = 4.34032634654904;
        state[3][1][1] = -30.0480834180741;
        state[3][1][2] = -0.0116103535014229;

        //Mars
        state[4][0][0] = -159116303.422552;
        state[4][0][1] = 189235671.561057;
        state[4][0][2] = 7870476.08522969;

        state[4][1][0] = -17.6954469224752;
        state[4][1][1] = -13.4635253412947;
        state[4][1][2] = 0.152331928200531;

        //Jupiter
        state[5][0][0] = 692722875.928222;
        state[5][0][1] = 258560760.813524;
        state[5][0][2] = -16570817.7105996;

        state[5][1][0] = -4.71443059866156;
        state[5][1][1] = 12.8555096964427;
        state[5][1][2] = 0.0522118126939208;

        //Saturn
        state[6][0][0] = 1253801723.95465;
        state[6][0][1] = -760453007.810989;
        state[6][0][2] = -36697431.1565206;

        state[6][1][0] = 4.46781341335014;
        state[6][1][1] = 8.23989540475628;
        state[6][1][2] = -0.320745376969732;

        //Titan
        state[7][0][0] = 1254501624.95946;
        state[7][0][1] = -761340299.067828;
        state[7][0][2] = -36309613.8378104;

        state[7][1][0] = 8.99593229549645;
        state[7][1][1] = 11.1085713608453;
        state[7][1][2] = -2.25130986174761;

        //Spaceship
        state[8][0][0] = xCoor;
        state[8][0][1] = yCoor;
        state[8][0][2] = zCoor;

        state[8][1][0] = velocity1;
        state[8][1][1] = velocity2;
        state[8][1][2] = velocity3;

        //Mercury
        state[9][0][0] = 7833268.43923962;
        state[9][0][1] = 44885949.3703908;
        state[9][0][2] = 2867693.20054382;

        state[9][1][0] = -57.4967480139828;
        state[9][1][1] = 11.52095127176;
        state[9][1][2] = 6.21695374334136;

        //Neptune
        state[10][0][0] = 4454487339.09447;
        state[10][0][1] = -397895128.763904;
        state[10][0][2] = -94464151.3421107;

        state[10][1][0] = 0.447991656952326;
        state[10][1][1] = 5.44610697514907;
        state[10][1][2] = -0.122638125365954;

        //Uranus
        state[11][0][0] = 1958732435.99338; 
        state[11][0][1] = 2191808553.21893;
        state[11][0][2] = 17235283.8321992;

        state[11][1][0] = -5.12766216337626;
        state[11][1][1] = 4.22055347264457;
        state[11][1][2] = 0.0821190336403063;

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
