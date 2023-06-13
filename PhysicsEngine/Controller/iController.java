package PhysicsEngine.Controller;

public interface iController {
    public double[][] getNextState(double[] currentVelocity, double[] currentPosition, double u, double v, double theta);
}
