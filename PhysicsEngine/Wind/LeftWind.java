package PhysicsEngine.Wind;
import java.lang.Math;
import java.util.Random;


/**
 * The wind arrives from the left
 */
public class LeftWind extends Wind {
    /**
     *@param maxWindVelocityKmh is the maximum velocity the wind can have in km/h, it descreases getting closer titan
     */
    public LeftWind(double maxWindVelocityKmh){
        super(maxWindVelocityKmh);
        
    }

    @Override
    public double[] applyWind(double[] currentProbeVelocity) {

        // Chosing an angle such that cos(angle) is negative. Using degrees, in the range 90-180
        final double[] angleBoundaries = {90, 180};
        
        //calculate the X and Y 
        double[] windVelocity = super.calculateWindVelocity(angleBoundaries);

        double[] velocityAfterWind = {currentProbeVelocity[0] - windVelocity[0], currentProbeVelocity[1] - windVelocity[1]};
        return velocityAfterWind;
    }
}
