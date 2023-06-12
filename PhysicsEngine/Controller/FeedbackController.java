package PhysicsEngine.Controller;

public class FeedbackController implements iController{

    //Final Values needed
    //NOTE: Values are in km, not m. (Apart from angle)
    final double xFINAL = 0.0001;
    final double xVelocityFINAL = 0.0001;
    final double yVelocityFINAL = 0.0001;
    final double angleFINAL = 0.02;
    final double angularVelocityFINAL = 0.01;

    @Override
    public double[] getNextPosition(double[] currentVelocity, double[] currentPosition, double u, double v, double theta) {
        return new double[0];
    }
}
