package PhysicsEngine.Wind;


/**
 * The wind arrives from the left
 */
public class LeftWind extends Wind {

    // Chosing an angle such that cos(angle) is negative. Using degrees, in the range 90-180
    private final double[] angleBoundaries= {90, 270};

    /**
     *@param maxWindVelocityKmh is the maximum velocity the wind can have in km/h, it descreases getting closer titan
     */
    public LeftWind(double maxWindVelocityKmh){
        super(maxWindVelocityKmh);
    }

    @Override
    public double[] applyWind(double[] currentProbeVelocity) {
        //calculate the X and Y 
        double[] windVelocity = super.calculateWindVelocity(angleBoundaries, 0); // passing 0 since the distance is not adapted

        double[] velocityAfterWind = {currentProbeVelocity[0] - windVelocity[0], currentProbeVelocity[1] - windVelocity[1]};
        return velocityAfterWind;
    }

    @Override
    public double[] applyWind(double[] currentProbeVelocity, double distanceFromSurface) {

        //calculate the X and Y 
        double[] windVelocity = super.calculateWindVelocity(angleBoundaries, distanceFromSurface);

        double[] velocityAfterWind = {currentProbeVelocity[0] - windVelocity[0], currentProbeVelocity[1] - windVelocity[1]};
        return velocityAfterWind;
    }

}
