package EulerSolver;

public class State {

    double[][] positions = new double[7][3];
    double[][] velocities = new double[7][3];

    public State()
    {
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
        positions[2][0] = -148458048.395164;
        positions[2][1] = -27524868.1841142;
        positions[2][2] = 70233.6499287411;

        //Mars
        positions[3][0] = -159116303.422552;
        positions[3][1] = 189235671.561057;
        positions[3][2] = 7870476.08522969;

        //Jupiter
        positions[4][0] = 692722875.928222;
        positions[4][1] = 258560760.813524;
        positions[4][2] = -16570817.7105996;

        //Saturn
        positions[5][0] = 1253801723.95465;
        positions[5][1] = -760453007.810989;
        positions[5][2] = -36697431.1565206;

        //Titan
        positions[6][0] = 1254501624.95946;
        positions[6][1] = -761340299.067828;
        positions[6][2] = -36309613.8378104;

        //velocity here
        //Sun first row again

        //Venus
        velocities[1][] = 
        velocities[1][] = 
        velocities[1][] = 

    }
}
