package PhysicsEngine.Controller;

public interface iController {
    public double[] getNextPosition(double[] currentVelocity, double[] currentPosition, double u, double v, double theta);
}
