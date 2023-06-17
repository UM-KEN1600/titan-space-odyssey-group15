package PhysicsEngine.Wind;
import java.lang.Math;
import java.util.Random;

public class WindMachine {
    private Random random;

    private final double MAX_WIND;// km/s
    private double currentMaxWind;
    private final String DIRECTION;

    public WindMachine(String direction){

        this.random = new Random();
        this.MAX_WIND = 200 /3600; //km per hour to km per second
        this.DIRECTION = direction;
        

    }


    //The wind arrives form the right every timestep from different angles and affects randomly the x velocity
    private double[] windRightX(double[] currentVelocity){

    double windChangeX = 0;
    double windChangeY = 0;

    //the angle from wich the wind will arrive (this case from the right)
    double[] angleBoundaries = {0, 90};
    double angle = random.nextDouble(angleBoundaries[1] - angleBoundaries[0] + 1) + angleBoundaries[0];

    //changing the x velocity
    double windChangeX = MAX_WIND * Math.cos(angle);
    

    double[] velocityAfterWind = {currentVelocity[0] - windChangeX, currentVelocity[1] - windChangeY};
    return velocityAfterWind;
    }

    public double[] applyWind(double[] currentVelocity){
        switch(DIRECTION){
        case "right":
            return windRightX(currentVelocity);
        case "left" :
            return windLeftX(currentVelocity);
    }

    }


}
