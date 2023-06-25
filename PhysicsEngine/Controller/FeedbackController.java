package PhysicsEngine.Controller;

import java.util.Arrays;

import javax.transaction.xa.Xid;

import PhysicsEngine.Solvers.RungeKutta4Solver;
import SolarSystem.CelestialBody;

public class FeedbackController implements iController{

    //Position of Titan after one year, used for calculation of angle
    static public double[] LANDING_POSITION = {1368066052.585550,-485587471.846701 + CelestialBody.bodyList[7].getRadius()};

    private final double zeroAngleCalibration = 0;
    //Final Values needed
    //NOTE: Values are in km, not m. (Apart from angle)
    private final double xFINAL = 0.0001;
    private final double xVelocityFINAL = 0.0001;
    private final double yVelocityFINAL = 0.0001;
    private final double thetaDifference = 0.02;
    private final double thetaFINAL = thetaDifference + zeroAngleCalibration;
   
    private final double angularVelocityFINAL = 0.0001; //0.01 is og

    //Max constraints for some values
    private final double g = 0.001352;
    private final double maxThrust = 10*g;
    private final double maxTorque = 1;

    //timestep of the simulation
    private double timeStep = 0;

    //Current Values of the probe
    private double[] currentPosition;
    private double[] currentVelocity;
    
    //Angle of the probe
    private double currentAngle;
    private double currentAngularVelocity;


    //Torque that will be used
    private double torque = 0; //rad s^2
    private double turnTime = 0;
    private double halfTurn = 0;
    private double currentThrust = 0;
    private double turnAngle = 1.3; //Angle at which the probe will be positioned at when turning. Will be written as an addition to PI/2 radians
    private double thrustTime = 0;
    private double tempThrust = 0;
    private double halfThrust = 0;
    private RotationImpulse rotator = new RotationImpulse(0, 0);

 

    //RK4
    public RungeKutta4Solver rk4 = new RungeKutta4Solver();

    public FeedbackController(double timestep, double[] LANDING_POSITION){
        this.timeStep = timestep;
        this.LANDING_POSITION = LANDING_POSITION;
    }

    
    @Override
    public double[] getUV(double[][] state, int time) {

        // state [0][0] -> Xposition, [0][1] -> Yposition, [0][2] -> angle
        // state [1][0] -> Xvel, [1][1] -> Yvel, [1][2] -> angularVelocity
        this.currentPosition = state[0];
        this.currentVelocity = state[1];
        this.currentAngle = currentPosition[2];
        this.currentAngularVelocity = currentVelocity[2];

        
        System.out.println("Positions");
        System.out.println(Arrays.toString(currentPosition));

        System.out.println("Velocity");
        System.out.println(Arrays.toString(currentVelocity));

        System.out.println("Angle:");
        System.out.println(currentPosition[2]);

        double xDistanceProbeLandingPoint = LANDING_POSITION[0] - currentPosition[0];
        
        System.out.println("Movement: ");
        System.out.println(xDistanceProbeLandingPoint);
        
        System.out.println("Thrust time");
        System.out.println(thrustTime);
        rotatingController();

        //Sets and controls any parameters that are needed for a successful x displacement
        thrustController();
        testOnce();

    
        
        double nextState[] = new double[2];
        nextState[0] = currentThrust;
        nextState[1] = torque;

        System.out.println("Current thrust:");
        System.out.println(currentThrust);

        System.out.println("Torque:");
        System.out.println(torque);
        return nextState;
    }

    /* Controls the thrust, if is active decreases the amount of time it will further be active.
     * If at halftime, it starts the deceleration phase
     * else If the time is zero, it finishes.
     */
    //DO NOT REMOVE
    private void thrustController(){
        if(thrustTime == 0){
            currentThrust = 0; //X movement has been finished
            thrustTime = 0;
            halfThrust = 0;
            tempThrust = 0;
        }
        if(thrustTime >= 1 && (currentThrust == 0) && (turnTime == 0)){
            currentThrust = tempThrust;
        }
        if((thrustTime >= 1) && (turnTime == 0)){
            thrustTime += timeStep*-1; //Ticks down the turn time
        }
        if((thrustTime == halfThrust) && (thrustTime > 0) && (turnTime == 0)){
            double newAngle = 2* Math.PI - currentAngle;
            doRotation(newAngle); //Starts the deceleration phase
        }
        if((turnTime > 1)){
            currentThrust = 0;
        }
        
        
        
        
    }   

    /*Controls the rotation, if is running decreases the time of rotation and starts the decrease 
     * of the velocity if at halftime. If the time is zero, it finishes.
     */
    private void rotatingController(){
        if(turnTime > 1){// the turning is still in progress
            turnTime += timeStep * -1; //Ticks down the turn time

            if((turnTime == halfTurn)){
                torque = -torque; //Starts the deceleration phase
            }
        }
        else{
            torque = 0; //Turn has been finished
            turnTime = 0;
            halfTurn = 0;
        }
    }


    
    /**
     * Main controller to do any correction in the X axis
     * Finds a suitable angle for the rocket to be at or uses the existing one
     * Plans out exactly for how long to run the engine to accelerate and then decelerate until it reaches the wanted position
     */
    //DO NOT REMOVE
    private void xCorrection(){
        double movement = LANDING_POSITION[0] - currentPosition[0];

        System.out.println("Current X Position:");
        System.out.println(currentPosition[0]);
            
        double movementAngle = (turnAngle * Math.signum(movement));
        if(Math.signum(movementAngle) == -1){
            movementAngle += 2*Math.PI;
        }
        System.out.println("movement angle");
        System.out.println(movementAngle);
        if(!((currentAngle > movementAngle-thetaDifference) && (currentAngle < movementAngle+thetaDifference))  && (turnTime == 0)){
            System.out.println("modifed movement angle");
            doRotation(movementAngle);
            return;
        }

        System.out.println("Movement2: ");
        System.out.println(movement);
        doMovement(movement);
        System.out.println("half thurst");
        System.out.println(halfThrust);
    }


    /**
     * Sets any necessary values for the rocket to move in the x axis
     * @param movement displacement amount
     */
    private void doMovement(double movement){
        System.out.println("doMovement:");
        System.out.println(movement);
        double halfDistance = movement/2;
        currentThrust = maxThrust;
        double time = Math.ceil(xTime(halfDistance));
        double thrust = xThrust(time, halfDistance);
        System.out.println("Half thrust");
        System.out.println(thrust);
        currentThrust = thrust;
        
        halfThrust = time;
        thrustTime = (halfThrust * 2) -2;
        if(thrustTime < 2){
            thrustTime = halfThrust+1;
            currentThrust = (movement/4)/Math.sin(currentAngle);
        }
        tempThrust = (currentThrust+1)-1;
    
    }

    /**
     * Calculates how long it would take for the spacecraft to move a certain distance at max thrust
     * @param movement displacement
     * @return the time needed 
     */
    private double xTime(double movement){
        System.out.println("xTime values");
        System.out.println(movement);
        double acceleration = Math.abs(currentThrust * Math.sin(currentAngle));
        System.out.println(acceleration);
        System.out.println("Angle:");
        System.out.println(Math.sin(currentAngle));
        double time = Math.sqrt(Math.abs(((movement-currentVelocity[0])*2)/acceleration));
        System.out.println("xTime:");
        System.out.println(time);
        return time;
    }

    /**
     * Calculates the acceleration you would need for the probe to move a certain distance in a set amount of time
     * @param time Time you want the probe to take
     * @param movement The displacement you want the probe to make
     * @return the thrust needed to push the rocket by for it to get to the deisre position
     */
    private double xThrust(double time, double movement){
        double acceleration = ((movement-currentVelocity[0])*2) / (time*time);
        double thrust = Math.abs(acceleration/Math.sin(currentAngle));
        System.out.println("xThrust:");
        System.out.println(thrust);
        System.out.println("xTIme thrust");
        System.out.println(time);
        double correctedThrust = Math.abs((movement / (((time*time)/2) -8))/Math.sin(currentAngle));
        return correctedThrust;
    }

    /**
     * Handles the x velocity correction
     * Basically sets the thrust for one second in 1 direction for it to counteract any residual thrust
     */
    private void xVelocityCorrection(){
        if(Math.signum(currentVelocity[0]) != Math.signum(Math.sin(currentAngle))){
            double velocityCorrection = Math.abs(currentVelocity[0]/Math.sin(currentAngle));
            if (Math.abs(currentVelocity[0]/Math.sin(currentAngle)) > maxThrust){
                velocityCorrection = maxThrust;
            }
            currentThrust = velocityCorrection;
            System.out.println("correction thrust");
            System.out.println(currentThrust);

            thrustTime = 0;
            halfThrust = 1;
        } else{

            double movementAngle = 0 + turnAngle * -Math.signum(currentVelocity[0]);
            doRotation(movementAngle);
        }
    }

    /**
     * Main method to calculate for how long and how much to decelerate
     */
    private void yCorrection(){
        double height = currentPosition[1] - LANDING_POSITION[1];
        System.out.println(ydecelerationTime(maxThrust));
        if((ydecelerationTime(maxThrust) + 50 < fallTime() || height > 50) && (height >1)){
            return;
        }
        if(Math.abs(currentVelocity[1]) < maxThrust - g){
            System.out.println("WTF IS it DOING");
            System.out.println(currentVelocity[1]);
            double time = fallTime();
            currentThrust = yAcceleration(time);
            System.out.println("MAJOR FUCKUP POINT");
            System.out.println(currentThrust);
        } else{
            currentThrust = maxThrust;
        }
        if(fallTime() < 2){
            currentThrust = (Math.abs(currentVelocity[1]) + g) - 0.5*yVelocityFINAL;
        }
        System.out.println("yCorrection thrust");
        System.out.println(currentThrust);

    }
        
    /**
     * Calculates the time that it would take in the current state for the probe to reach titan
     * @return time that it would take to reach titan;
     */
    //s = v0t - 0.5gt^2
    //0 = -0.5gt^2 + v0t - s
    private double fallTime(){
        double height = currentPosition[1] - LANDING_POSITION[1];
        System.out.println("wtf is the height even");
        System.out.println(height);
        double currentYVelocity = currentVelocity[1];
        System.out.println("WTF IS THE VELOCITY");
        System.out.println(currentYVelocity);
        double time = quadraticEquation(-0.5*g, currentYVelocity, height);
        System.out.println("IS IT TIME?");
        System.out.println(time);
        return time;
    }

    /**
     * Calculates the  time needed to decelerate from the current velocity to 0
     * @return amount of time needed to decelerate
     */
    private double ydecelerationTime(double thrust){
        double currentYVelocity = currentVelocity[1];
        double totalDeceleration = thrust - g;
        double decelerationTime = -currentYVelocity/totalDeceleration;
        return decelerationTime;
    }

    /**
     * Quadratic Equation solver
     * ax^2 + bx + c = 0
     * x = (-b + sqrt(b^2 -4ac))/2a
     * @param a 
     * @param b
     * @param c
     * @return solved equation for x
     */
    static public double quadraticEquation(double a, double b, double c){
        double discriminant = Math.sqrt(Math.abs((b*b) - (4*a*c)));
        double x = (-b - discriminant)/ (2*a);
        return x;
    }

    /**
     * Calculates the acceleration that would be needed to decelerate in a given amount of time to 0
     * @param time
     * @return deceleration amount
     */
    private double yAcceleration(double time){
        double currentYVelocity = currentVelocity[1];
        double acceleration = currentYVelocity/time;
        return acceleration + g;
    }

    //Rotates the probe back to vertical position
    private void rotationCorrection(){
        doRotation(zeroAngleCalibration);
    }

    
    /**
     * Finds which direction the spacecraft has to rotate
     * @param newAngle
     */
    private void direction(double newAngle){
        System.out.println("direciton");
        System.out.println(newAngle - currentAngle);
        System.out.println(newAngle);
        System.out.println(currentAngle);
        if (newAngle - currentAngle < 0){
            torque = -torque; 
        }
        System.out.println("New torque");
        System.out.println(torque);
    }
    
    /**
     * Calculates all necessary factors to implement the desired rotation to reach a new angle
     * @param newAngle 
     */
    public void doRotation(double newAngle){
        //Find the amount of angle that needs to be displaced
        double changeInAngle = Math.abs(newAngle - currentAngle);

        System.out.println("New Angle");
        System.out.println(changeInAngle);
        //calculates for how long an amount of torque has to be applied to reach the wanted angle
        rotator.xRotationPlan(changeInAngle);
        turnTime = rotator.getRotationTime();
        torque = rotator.getTorque();

        //checks which direction to turn to
        direction(newAngle);

        //used to decide when to decelerate
        halfTurn = turnTime/2;
    }


    //Fixes the angularVelocity in case any error has been made
    public void angularVelocityCorrection(){
        torque = -currentAngularVelocity;
        if(torque > maxTorque){
            torque = -maxTorque;
        }
    }
    
    /* REDUNDANT SINCE IT'S ALREADY IN SIM
    //Resets the angle to a 2PI base system (Prevents negative values or values above 2PI)
    public void fullCircle(){
        if (currentAngle < 0){
            currentAngle += 2* Math.PI;
        }
        if(currentAngle > 0){
            currentAngle -= 2* Math.PI;
        }
    }
    */


    //Constraint Tester Block
    //---------------------------------------------------------------------------------------------------------
    public boolean testAngle(){
        return (currentAngle%(2*Math.PI)) < thetaFINAL;
    }

    public boolean testXVelocity(){
        //return Math.abs(currentVelocity[0]) < xVelocityFINAL;
        return Math.abs(currentVelocity[0]) == 0;
    }

    public boolean testYVelocity(){
        return (Math.abs(currentVelocity[1]) < yVelocityFINAL) && (fallTime() > 2);
    }

    public boolean testXPosition(){
        double xdistance = Math.abs(LANDING_POSITION[0] - currentPosition[0]);
        System.out.println("xdistance");
        System.out.println(xdistance);
        return xdistance < xFINAL ;
    }

    public boolean testAngularVelocity(){
        return Math.abs(currentAngularVelocity) < angularVelocityFINAL;
    }
    //---------------------------------------------------------------------------------------------------------


    //Checks all constraints and corrects the probe as Necessary
    public void testOnce(){

        if(!testXVelocity() && (thrustTime == 0) && (turnTime == 0)){
            System.out.println("xVelocityCorrection");
            xVelocityCorrection();
        }
        if(!testXPosition() && (turnTime == 0) && (thrustTime == 0)){
            System.out.println("xCorrection");
            xCorrection();
        }
        
        if(!testAngle() && (thrustTime == 0) && (turnTime == 0) && (halfThrust == 0)){
            System.out.println("AngleCorrection");
            rotationCorrection();
        }

        if(!testAngularVelocity() && (turnTime == 0)){
            System.out.println("AngularCorrection");
            angularVelocityCorrection();
        }
    
        if(!testYVelocity() && (turnTime == 0)){
            System.out.println("yCorrection");
            yCorrection();
        }
        System.out.println("Rotation time");
        System.out.println(turnTime);
    }


}
