
package PhysicsEngine.Wind;
import java.lang.Math;
import java.util.Random;

/**
 * Create an instance of Wind using LeftWind, RightWind or LeftAndRightWind classes.
 * Call the constructor with the preferred maxWindVelocity in km/h value.
 * Call the method applyWind (i.e. currentVelocity = instanceName.applyWind(currentVelocity); )
 * Calling applyWind with the parameters (currentVelocity, distanceProbeTitan), maxWindVelocity is adapted to the atmosphere layer
*/
public abstract class Wind {

    protected final double maxWindVelocity;
    
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
    public abstract double[] applyWind(double[] currentProbeVelocity);

    //adapt the the wind to the altitude (lower altitude, more wind)
    public abstract double[] applyWind(double[] currentProbeVelocity, double distanceFromSurface);


    public double[] calculateWindVelocity(double[] angleBoundaries, double distanceFromSurface) {

        Random random = new Random();
        double windChangeX = 0;// wind velocity on x
        double windChangeY = 0;// wind velocity on y
        double angle; //the angle from wich the wind will arrive

        
        // since the boundaries could be in multiple ranges(ie 270-365, 0-90), choose randomly two boundaries
        int randomIndex = random.nextInt(angleBoundaries.length / 2); // Select a random pair of angle boundaries
        double startAngle = angleBoundaries[randomIndex * 2];
        double endAngle = angleBoundaries[randomIndex * 2 + 1];
        
        //randomizing a wind strength in the boundaries 1-maxWindVelocity, always in km/s
        double windVelocity = random.nextDouble(0, maxWindVelocity);

        //find the angle the wind arrives from
        angle = random.nextDouble(endAngle - startAngle) + startAngle;

        windChangeX = adaptWindToLayerOfAtmosphere(distanceFromSurface,windVelocity) * Math.cos(Math.toRadians(angle)); // radiants are expected in imput, not degrees
        windChangeY = adaptWindToLayerOfAtmosphere(distanceFromSurface,windVelocity) * Math.sin(Math.toRadians(angle)); // not changing it for now

        return new double[] {windChangeX, windChangeY};

    }

    /**
     * 
     * @param currentDistanceFromSurface
     * @return the maxWindvelocity relative to the layer of the atmosphere the probe is in and the maxVelocity bound
     * Layers of Titan's atmosphere:
            1. Troposphere: 0-32 km (0-20 mi)
            2. Stratosphere: 32-100 km (20-62 mi)
            3. Mesosphere: 100-210 km (62-130 mi)
            4. Thermosphere: Above 210 km (130 mi)
     */
    private double adaptWindToLayerOfAtmosphere(double currentDistanceFromSurface, double windVelocity){

        if (currentDistanceFromSurface <= 32) {  //Troposphere
            return 4/4 * windVelocity;

        } else if (currentDistanceFromSurface <= 100) { //Stratosphere
            return 3/4 * windVelocity;

        } else if (currentDistanceFromSurface <= 210) { //Mesosphere
            return 2/4 * windVelocity;

        } else {     //Thermosphere over 210km
            return 1/4 * windVelocity;
        } 
    }

}
