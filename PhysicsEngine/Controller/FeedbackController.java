package PhysicsEngine.Controller;

import PhysicsEngine.Solvers.RungeKutta4Solver;

public class FeedbackController implements iController{

    //Final Values needed
    //NOTE: Values are in km, not m. (Apart from angle)
    final double xFINAL = 0.0001;
    final double xVelocityFINAL = 0.0001;
    final double yVelocityFINAL = 0.0001;
    final double thetaFINAL = 0.02;
    final double angularVelocityFINAL = 0.01;

    //Max constraints for some values
    static final double g = 0.001352;
    static final double maxThrust = 10*g;
    static final double maxTorque = 1;


    //Current Values of the probe
    public double[] currentPosition;
    public double[] currentVelocity;


    //Timestep being used in the current instance
    public double timestep;
    
    //Angle of the probe
    public double currentAngle;
    public double currentAngularVelocity;


    //Torque that will be used
    public double torque; //rad s^2
    public double turnTime;
    public double halfTurn;
    public double stepTime = 1;
    public double currentThrust;
    public double turnAngle = 0.175; //Angle at which the probe will be positioned at when turning. Will be written as an addition to PI/2 radians
    public double thrustTime;
    public double halfThrust;
    public RotationImpulse rotator = new RotationImpulse(0, 0);

    //Position of Titan after one year, used for calculation of angle
    final double[] CENTER_OF_TITAN = {1.3680484627624216E9,-4.8546124152074784E8};

    //RK4
    public RungeKutta4Solver rk4 = new RungeKutta4Solver();

    public FeedbackController(double torque, double timestep){
        this.torque = torque;
        this.timestep = timestep;
    }

    @Override
    public double[][] getNextState(double[] currentVelocity, double[] currentPosition, double u, double v, double theta) {

        this.currentVelocity = currentVelocity;
        this.currentPosition = currentPosition;
        this.currentAngle = theta;

        rotating();
        testOnce();
        

        double[][] nextState = rk4.solve(currentPosition, currentVelocity, currentThrust,  torque, stepTime, g);

        //Corrects the angle just in case it is not in 2PI system
        currentAngle = nextState[0][2];
        fullCircle();
        nextState[0][2] = currentAngle;

        return nextState;
    }

    //public double[] solve(double[] oldState, double[] velocities, double mainThrust, double torque, double timestep, double g)

    

    public void xCorrection(){
        double movement = 0 - currentPosition[0];
        if(currentAngle == 0){
            double movementAngle = turnAngle * Math.signum(movement);
            doRotation(movementAngle);
        }
        doMovement(movement);



    }

    public void thrustController(){
        if(thrustTime > 0){
            thrustTime--; //Ticks down the turn time
        }
        if(thrustTime == halfTurn){
            currentAngle = -turnAngle;
            currentThrust = -currentThrust; //Starts the deceleration phase
        }
        if(thrustTime <= 0){
            currentThrust = 0; //X movement has been finished
        }
    }
    public void doMovement(double movement){
        double halfDistance = movement/2;
        double time = Math.ceil(xTime(halfDistance));
        double thrust = xThrust(time, halfDistance);
        currentThrust = thrust;
        thrustTime = time;
        halfThrust = thrustTime/2;
    }

    public double xTime(double movement){
        double acceleration = currentThrust * Math.sin(currentAngle);
        double time = Math.sqrt((movement*2)/acceleration);
        return time;
    }

    public double xThrust(double time, double movement){
        double acceleration = (movement*2) / (time*time);
        double thrust = acceleration/Math.asin(currentAngle);
        return thrust;
    }

    /**
     * Main method to calculate for how long and how much to decelerate
     */
    public void yCorrection(){
        if(ydecelerationTime(maxThrust) + 50 < fallTime()){
            return;
        }
        if(currentVelocity[1] < maxThrust - g){
            double time = fallTime();
            currentThrust = yAcceleration(time);
        } else{
            currentThrust = maxThrust;
        }

    }
    
    /**
     * Calculates the time that it would take in the current state for the probe to reach titan
     * @return time that it would take to reach titan;
     */
    //s = v0t - 0.5gt^2
    //0 = -0.5gt^2 + v0t - s
    public double fallTime(){
        double height = currentPosition[1];
        double currentYVelocity = currentVelocity[1];
        double time = quadraticEquation(-0.5*g, currentYVelocity, height);
        return time;
    }

    /**
     * Calculates the  time needed to decelerate from the current velocity to 0
     * @return amount of time needed to decelerate
     */
    public double ydecelerationTime(double thrust){
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
        double discriminant = Math.sqrt(Math.abs((b*b) - 4 * a * c));
        double x = (-b + discriminant)/ 2*a;
        return x;
    }

    /**
     * Calculates the acceleration that would be needed to decelerate in a given amount of time to 0
     * @param time
     * @return deceleration amount
     */
    public double yAcceleration(double time){
        double currentYVelocity = currentVelocity[1];
        double acceleration = currentYVelocity/time;
        return acceleration + g;
    }

    //Rotates the probe back to vertical position
    public void rotationCorrection(){
        doRotation(0);
    }

    
    /**
     * Finds which direction the spacecraft has to rotate
     * @param newAngle
     */
    public void direction(double newAngle){
        if (newAngle - currentAngle < 0){
            torque = -torque; 
        }
    }

    //Manages the current rotation
    //Checks how long the rotation has to be for and manages when to start the deceleration phase
    public void rotating(){
        if(turnTime > 0){
            turnTime--; //Ticks down the turn time
        }
        if(turnTime == halfTurn){
            torque = -torque; //Starts the deceleration phase
        }
        if(turnTime <= 0){
            torque = 0; //Turn has been finished
        }
    }

    
    /**
     * Calculates all necessary factors to implement the desired rotation to reach a new angle
     * @param newAngle 
     */
    public void doRotation(double newAngle){
        //Find the amount of angle that needs to be displaced
        double changeInAngle = Math.abs(newAngle - currentAngle);

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
        double fixRotate = currentAngularVelocity;
        doRotation(fixRotate);
    }
    
    //Resets the angle to a 2PI base system (Prevents negative values or values above 2PI)
    public void fullCircle(){
        if (currentAngle < 0){
            currentAngle += 2* Math.PI;
        }
        if(currentAngle > 0){
            currentAngle -= 2* Math.PI;
        }
    }


    //Constraint Tester Block
    //---------------------------------------------------------------------------------------------------------
    public boolean testAngle(){
        return (currentAngle%(2*Math.PI)) < thetaFINAL;
    }

    public boolean testXVelocity(){
        return Math.abs(currentVelocity[0]) < xVelocityFINAL;
    }

    public boolean testYVelocity(){
        return Math.abs(currentVelocity[1]) < yVelocityFINAL;
    }

    public boolean testXPosition(){
        return currentPosition[0] < xFINAL;
    }

    public boolean testAngularVelocity(){
        return Math.abs(currentAngularVelocity) < angularVelocityFINAL;
    }
    //---------------------------------------------------------------------------------------------------------


    //Checks all constraints and corrects the probe as Necessary
    public void testOnce(){

        if(!testXPosition()){
            xCorrection();
        }
        if(!testXVelocity()){
            //MODIFY X VELOCITY HERE
        }
        if(!testAngle()){
            rotationCorrection();
        }
        fullCircle();
        if(!testAngularVelocity() && (turnTime == 0)){
            //MODIFY ANGULAR VELOCITY HERE
        }
        fullCircle();
        if(!testYVelocity() && (turnTime == 0)){
            yCorrection();
        }
    }


}
