package PhysicsEngine.Wind;
import java.lang.Math;
import java.util.Random;

public class LeftAndRightWind extends Wind{
    
    /**
     *@param maxWindVelocityKmh is the maximum velocity the wind can have in km/h, it descreases getting closer titan
     */
    public LeftAndRightWind(double maxWindVelocityKmh){
        super(maxWindVelocityKmh);
    }

    @Override
    public double[] applyWind(double[] currentProbeVelocity) {
        // Chosing an angle such that cos(angle) is negative. Using degrees, in the range 90-270
        double[] boundaries = {0, 365}; 

        Random random = new Random();
        double windChangeX = 0;
        double windChangeY = 0;
        double angle;
        
        double angleInDegrees = random.nextDouble(boundaries[1] - boundaries[0]) + boundaries[0];

        windChangeX = maxWindVelocity * Math.cos(Math.toRadians(angleInDegrees));
        //windChangeY = maxWindVelocity * Math.sin(Math.toRadians(angleInDegrees)); // not changing y for now

        double[] velocityAfterWind = {currentProbeVelocity[0] + windChangeX, currentProbeVelocity[1] - windChangeY};
        return velocityAfterWind;
    }
}
