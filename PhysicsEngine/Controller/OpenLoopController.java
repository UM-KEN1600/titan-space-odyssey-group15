package PhysicsEngine.Controller;

import PhysicsEngine.Operations.VectorOperations;
import SolarSystem.CelestialBody;

import java.util.LinkedList;
import java.util.Queue;

public class OpenLoopController implements iController{
    //Final Values needed
    //NOTE: Values are in km, not m. (Apart from angle)
    final double xFINAL = 0.0001;
    final double xVelocityFINAL = 0.0001;
    final double yVelocityFINAL = 0.0001;
    final double thetaFINAL = 0.02;
    final double angularVelocityFINAL = 0.01;

    //Max constraints for some values
    final double g = 0.001352;
    final double maxThrust = 10*g;
    final double maxTorque = 1.0;
    final double SIZE_OF_SPACESHIP = 0.1; //100 meters :)

    //Position of Titan after one year, used for calculation of angle
    //Top of titan
    final static public double[] LANDING_POSITION = {1368066052.585550,-485587471.846701 + CelestialBody.bodyList[7].getRadius()};

    //Timestep being used in the current instance
    private double timestep;
    
    //90 degrees in radians
    final double ANGLE_TOWARDS_SURFACE = 1.571;

    double[] UV = new double[2];

    RotationImpulseOLC currentRotationImpulse;
    MainThrusterImpulse currentMainThrustImpulse;

    //stores precalculated rotation operations which are needed during the landing process
    private Queue<RotationImpulseOLC> DataStorageRotationImpulse = new LinkedList();
    private Queue<MainThrusterImpulse> DataStorageMainThrustImpulse = new LinkedList();

    public OpenLoopController(double[] landingPosition)
    {
        initialDataStorageRotationImpulse();
        initialDataStorageMainThrustImpulse();

    }

    public void initialDataStorageRotationImpulse(){
    }

    public void initialDataStorageMainThrustImpulse(){
    }


    @Override
    public double[] getUV(double[][] state, int time) {


    if(!DataStorageRotationImpulse.isEmpty()) {

        if(DataStorageRotationImpulse.peek().getStartTimeTorqueAcceleration() == time){
            currentRotationImpulse = DataStorageRotationImpulse.peek();
            DataStorageRotationImpulse.remove();
        }
    }
    if(currentRotationImpulse != null){
        handleCurrentRotation((int)time);
    }



    if(!DataStorageMainThrustImpulse.isEmpty()){

        if(DataStorageMainThrustImpulse.peek().getStartTimeOfImpulse() == time){
            currentMainThrustImpulse = DataStorageMainThrustImpulse.peek();
            DataStorageMainThrustImpulse.remove();

        }
    }
    if(currentMainThrustImpulse != null){
        handleCurrentMainThrust((int)time);
    }
        return UV;
    }


    /**
     * calculates the time needed to appraoch the landing position based on the starting velocity and the alowed thrust
     * @param distanceVector distance from spaceship to titan
     * @param velocity beginning velocity
     * @return
     */
    private static double calculateTimeNeededToApproach(double[] distanceVector, double[] velocity)
    {
        double distance = Math.sqrt(Math.pow(distanceVector[0], 2) + Math.pow(distanceVector[1], 2));
        double velocityReach = Math.sqrt(Math.pow(velocity[0], 2) + Math.pow(velocity[1], 2));

        return distance/velocityReach;
    }

    /**
     * Methods sets the appropriate torque if a change is required given by time
     * @param time
     */
    public void handleCurrentRotation(int time){

        if(time>= currentRotationImpulse.getStartTimeTorqueAcceleration() && time<= currentRotationImpulse.getStartTimeTorqueDeceleration()){

            if(time<= currentRotationImpulse.getStartTimeTorqueAcceleration() && time < currentRotationImpulse.getStartTimeTorqueDeceleration()){
                UV[1]= currentRotationImpulse.getTorqueAcceleration();
            }
            else if((time >= currentRotationImpulse.getStartTimeTorqueDeceleration()) && ((currentRotationImpulse.getStartTimeTorqueDeceleration() + currentRotationImpulse.getTime()) >= time)){
                UV[1]= currentRotationImpulse.getTorqueDeceleration();
            }
            else{
                UV[1] = 0;
            }

        }
    }

    public void handleCurrentMainThrust(int time){
        if(time>= currentMainThrustImpulse.getStartTimeOfImpulse() && time<= currentMainThrustImpulse.getEndTimeOfPulse()){
            UV[0] = currentMainThrustImpulse.getThrust();
        }
        else{
            UV[0] = 0;
        }

    }
    
    private double calculateNeededThrust(double wantedChangeInAngle)
    {
        if(wantedChangeInAngle % maxTorque > 1)
        {
            return maxTorque;
        }

        return wantedChangeInAngle % maxTorque;
    }


    /**
     * Calculates the angle between the velocity of the spaceship and the wanted landing position
     * Assumes that the landing angle is 90 degrees and therefore the yAxis
     * @return angle between tail and Titan
     */
    private static double calculateAngleBetweenSpaceshipAndTitan(double[] passedVelocity)
    {
        double[] velocities = {passedVelocity[0],passedVelocity[1]};
        double[] xAxis = {10,0};

        //calculate the angle between spaceship "perpendicularness"
        double dotProduct = VectorOperations.dotProduct(xAxis,velocities);
        double aMag = VectorOperations.magnitude(velocities);
        double bMag = VectorOperations.magnitude(xAxis);

        double check = passedVelocity[0]*xAxis[1] - passedVelocity[0]*xAxis[0];
        
        if(check < 0)
        {
            return 2*Math.PI - Math.acos(dotProduct/(aMag * bMag));
        }

        return Math.acos(dotProduct/(aMag * bMag));
    }

    /**
     * Calculates the angle between the velocity of the spaceship and the wanted landing position
     * Assumes that the landing angle is 90 degrees and therefore the yAxis
     * @return angle between tail and Titan
     */
    private static double calculateAngleBetweenSpaceshipAndLandigAxis(double[] passedVelocity)
    {
        double[] velocities = {passedVelocity[0],passedVelocity[1]};
        double[] xAxis = {0,10};

        //calculate the angle between spaceship "perpendicularness"
        double dotProduct = VectorOperations.dotProduct(xAxis,velocities);
        double aMag = VectorOperations.magnitude(velocities);
        double bMag = VectorOperations.magnitude(xAxis);

        double check = passedVelocity[0]*xAxis[1] - passedVelocity[0]*xAxis[0];
        
        if(check < 0)
        {
            return 2*Math.PI - Math.acos(dotProduct/(aMag * bMag));
        }

        return Math.acos(dotProduct/(aMag * bMag));
    }




    /**
     * Returns the difference in positions of the spaceship's current position and a celestial body's position
     * @param subject a celestial body's position
     * @return position of the celestial body relative to the spaceship
     */
    private double[] getPositionRelativeToTitan(double[] subject)
    {
        return VectorOperations.vectorSubtraction(subject, LANDING_POSITION);
    }

    private double[] getPositionRelativeToSpaceship(double[] subject)
    {
        return VectorOperations.vectorSubtraction(LANDING_POSITION,subject);
    }



}
