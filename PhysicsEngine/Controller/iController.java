package PhysicsEngine.Controller;

public interface iController {

    double[] getNextPosition(double[] currentVelocity,double[] currentPosition, double u, double v,double theta);

}
