package PhysicsEngine.Wind;
import java.lang.Math;
import java.util.Random;


/**
 * The wind arrives from all the angles
 */
public class LeftAndRightWind extends Wind{
    
    /**
     *@param maxWindVelocityKmh is the maximum velocity the wind can have in km/h, it descreases getting closer titan
     */
    public LeftAndRightWind(double maxWindVelocityKmh){
        super(maxWindVelocityKmh);
    }

    @Override
    public double[] applyWind(double[] currentProbeVelocity) {

        // Chosing a random angle from wich the wind will arrive. Using degrees, in the range 0-365
        final double[] angleBoundaries = {0, 365};
    
        //calculate the X and Y 
        double[] windVelocity = super.calculateWindVelocity(angleBoundaries);

        double[] velocityAfterWind = {currentProbeVelocity[0] - windVelocity[0], currentProbeVelocity[1] - windVelocity[1]};
        return velocityAfterWind;
        }
}
