package PhysicsEngine.Wind;
import java.lang.Math;
import java.util.Random;

/**
 * The wind arrives from the right and affects the x velocity of the probe. Is using the 
 */
public class RightWind extends Wind {

    /**
     *@param maxWindVelocityKmh is the maximum velocity the wind can have in km/h, it descreases getting closer titan
     */
    public RightWind(double maxWindVelocityKmh){
        super(maxWindVelocityKmh);
    }

    @Override
    public double[] applyWind(double[] currentProbeVelocity) { // thinking about another parameter currentDistance, to adapt the maxWind to the distance from titan since near the wind is lower
        
        Random random = new Random();
        double windChangeX = 0;// how much the wind will be changes
        double windChangeY = 0;
        double angle; //the angle from wich the wind will arrive (this case from the right)

        // Chosing an angle such that cos(angle) is positive. Using degrees, in the range 270-360, 0-90
        final double[] angleBoundaries = {270, 360, 0, 90};
        int randomIndex = random.nextInt(angleBoundaries.length / 2); // Select a random pair of angle boundaries
        double startAngle = angleBoundaries[randomIndex * 2];
        double endAngle = angleBoundaries[randomIndex * 2 + 1];
        
        angle = random.nextDouble(endAngle - startAngle) + startAngle;
        windChangeX = maxWindVelocity * Math.cos(Math.toRadians(angle)); // radiants are expected in imput, not degrees
        
        //windChangeY = maxWindVelocity * Math.sin(Math.toRadians(angle)); // not changing it for now

        double[] velocityAfterWind = {currentProbeVelocity[0] - windChangeX, currentProbeVelocity[1] - windChangeY};
        return velocityAfterWind;
    }
}
