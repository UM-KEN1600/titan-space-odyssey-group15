package EulerSolver;

public class State {

    public double[][] positions = new double[7][3];
    public double[][] velocities = new double[7][3];

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
    }
}
