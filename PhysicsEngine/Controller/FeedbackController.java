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
    public double stepTime;
    public double currentThrust;

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
        testOnce();
        double[][] nextState = rk4.solve(currentPosition, currentVelocity, currentThrust,  torque, stepTime, g);
        return nextState;
    }

    //public double[] solve(double[] oldState, double[] velocities, double mainThrust, double torque, double timestep, double g)

    public void xRotation(double newAngle){
        /* 
        double turnTime = calculateAngleChangeTime(newAngle);

        double xComponentAcceleration = xAcceleration(newAngle);
        double yComponentAcceleration = yAcceleration(newAngle);
        */
        //HAS TO BE REDONE, NO MODIFICATION OF THE STATE HERE, ONLY IN THE RK4.SOLVE
        //modify the x component with timestep and the Xacceleration
    }

    public void yMovement(double newAngle){
        //likely modification is needed here
        //Probably not correctly done
    }

    public double calculateAngleChangeTime(double newAngle){
        double angleChange = Math.abs(currentAngle - newAngle);
        double time = angleChange / torque;
        return time*time;
    } 
    
    /* 
    //USELESS SINCE RK4.SOLVE WILL TAKE CARE OF THIS
    public static double xAcceleration(double angle){
        return maxThrust * Math.sin(angle);
    } 

    public static double yAcceleration(double angle){
        return  (maxThrust * Math.cos(angle)) - g;
    }

    public void setAngle(double newAngle){
        currentAngle = newAngle;
    }
    */


    public void angleChange(){
        if(currentAngle > Math.PI){
            double angleChange = 2*Math.PI - currentAngle;
            xRotation(angleChange);
        }
        if(currentAngle < Math.PI){
            double angleChange = -1 * currentAngle;
            xRotation(angleChange);
        }
        //Probs more stuff has to be changed here, but for now this would rotate it at least
    }
    
    public void angularVelocityChange(){
        if(currentAngularVelocity < (angularVelocityFINAL * -1)){
            double changeInAngularVelocity = currentAngularVelocity;
            double thrustTime = changeAngularVelocityTime(changeInAngularVelocity); //WRONG WRONG WRONG wRONGV
            xRotation(thrustTime);
        }
        if(currentAngularVelocity > angularVelocityFINAL){
            double changeInAngularVelocity = -currentAngularVelocity;
            double thrustTime = changeAngularVelocityTime(changeInAngularVelocity);
            xRotation(thrustTime);

        }
    }

    public double changeAngularVelocityTime(double changeInAngularVelocity){
        double time = Math.abs(currentAngularVelocity)/torque;
        return time;
    }
    //Resets the angle to a 2PI base system
    public void fullCircle(){
        if (currentAngle < 0){
            currentAngle += 2* Math.PI;
        }
        if(currentAngle > 0){
            currentAngle -= 2* Math.PI;
        }
    }

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

    public void testOnce(){
        if(!testAngle()){
            //MODIFY ANGLE HERE
        }
        fullCircle();
        if(!testAngularVelocity()){
            //MODIFY ANGULAR VELOCITY HERE
        }
        fullCircle();
        if(!testXPosition()){
            //MODIFY X POSITION HERE
        }
        if(!testXVelocity()){
            //MODIFY X VELOCITY HERE
        }
        if(!testYVelocity()){
            //MODIFY Y VELOCITY HERE
        }
    }


}
