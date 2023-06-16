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
    public double turnAngle; //Angle at which the probe will be positioned at when turning. Will be written as an addition to PI/2 radians

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

        testOnce();
        rotating();

        double[][] nextState = rk4.solve(currentPosition, currentVelocity, currentThrust,  torque, stepTime, g);
        currentAngle = nextState[0][2];
        fullCircle();
        nextState[0][2] = currentAngle;
        return nextState;
    }

    //public double[] solve(double[] oldState, double[] velocities, double mainThrust, double torque, double timestep, double g)

    

    public void xCorrection(){
        double movement = 0 - currentPosition[0];

    }

    public void yMovement(double newAngle){
        //likely modification is needed here
        //Probably not correctly done
    }



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
            turnTime--;
        }
        if(turnTime == halfTurn){
            torque = -torque;
        }
        if(turnTime <= 0){
            torque = 0;
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
        return currentVelocity[0] < xVelocityFINAL;
    }

    public boolean testYVelocity(){
        return currentVelocity[1] < yVelocityFINAL;
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
        if(!testAngle()){
            rotationCorrection();
        }
        fullCircle();
        if(!testAngularVelocity() && (turnTime == 0)){
            //MODIFY ANGULAR VELOCITY HERE
        }
        fullCircle();
        if(!testXPosition()){
            xCorrection();
        }
        if(!testXVelocity()){
            //MODIFY X VELOCITY HERE
        }
        if(!testYVelocity() && testAngle()){
            //MODIFY Y VELOCITY HERE
        }
    }


}
