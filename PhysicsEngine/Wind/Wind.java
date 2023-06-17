
package PhysicsEngine.Wind;
import java.lang.Math;
import java.util.Random;

/**
 * Create an instance of Wind using LeftWind, RightWind or LeftAndRightWind classes.
 * Call the constructor with the preferred maxWindVelocity in km/h value.
 * Call the method applyWind (i.e. currentVelocity = instanceName.applyWind(currentVelocity); )
*/
public abstract class Wind {

    protected double[] currentProbeVelocity;
    protected double maxWindVelocity;
    
    /**
     * @param maxWindVelocityKmh is the maximum velocity the wind can have in km/h, it descreases getting closer titan
     */
    protected Wind(double maxWindVelocityKmh){
        this.maxWindVelocity = maxWindVelocityKmh /3600;// transform km/h in km/s
    }

    /**
     * @param currentProbeVelocity is the velocity the wind have to be applied to
     * @return new probe velocity after the wind effect, i.e. currentVelocity = applyWind(currentVelocity)
     */
    public abstract double[] applyWind(double[] currentProbeVelocity);// thinking about another parameter currentDistance, to adapt the maxWind 


    //to the distance from titan since near the surface the wind is lower

    public double[] calculateWindVelocity(double[] angleBoundaries) {

        Random random = new Random();
        double windChangeX = 0;// wind velocity on x
        double windChangeY = 0;// wind velocity on y
        double angle; //the angle from wich the wind will arrive

        // since the boundaries could be in multiple ranges(ie 270-365, 0-90), choose randomly two boundaries
        int randomIndex = random.nextInt(angleBoundaries.length / 2); // Select a random pair of angle boundaries
        double startAngle = angleBoundaries[randomIndex * 2];
        double endAngle = angleBoundaries[randomIndex * 2 + 1];
        
        //find the angle the wind arrives from
        angle = random.nextDouble(endAngle - startAngle) + startAngle;

        windChangeX = maxWindVelocity * Math.cos(Math.toRadians(angle)); // radiants are expected in imput, not degrees
        //windChangeY = maxWindVelocity * Math.sin(Math.toRadians(angle)); // not changing it for now

        return new double[] {windChangeX, windChangeY};

    }
}
