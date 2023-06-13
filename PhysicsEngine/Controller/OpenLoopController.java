package PhysicsEngine.Controller;

public class OpenLoopController implements iController{
    @Override
    public double[][] getNextState(double[] currentVelocity, double[] currentPosition, double u, double v, double theta) {
        return new double[0][0];
    }
}
