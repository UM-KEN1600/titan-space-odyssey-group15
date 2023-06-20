package Test;
import static org.junit.Assert.*;

import org.junit.Test;
import java.util.Arrays;
import PhysicsEngine.Wind.LeftAndRightWind;
import PhysicsEngine.Wind.LeftWind;
import PhysicsEngine.Wind.RightWind;
import PhysicsEngine.Wind.Wind;


public class WindTest {
    private Wind windRight =  new RightWind(200);
    private Wind windLeft = new LeftWind(200);
    private Wind windLeftAndRight = new LeftAndRightWind(200);

    private final double[] testData = new double[]{0,0};

    // to test applyWind with boundaries and without 
    // passing a currentVelocity 0,0,0 the method should return a velocity positive for left, negative for right and changed for windLeftAndRight
    
    //Applying the wind from the right it should reduces the velocity
    @Test public  void testWindRight(){
        assertTrue("Right wind goes wrong", (windRight.applyWind(testData)[0] <= testData[0]) && (windRight.applyWind(testData)[1] != testData[1]));
    }
    
    //Applying the wind from the right it should enhance the velocity
    @Test  public void testWindLeft(){
        assertTrue("Left wind goes wrong", windLeft.applyWind(testData)[0] >= testData[0] && windLeft.applyWind(testData)[1] != testData[1]);
    }

    //Applying the wind the velocity changes
    @Test  public void testWindLeftAndRight(){
        assertNotSame(Arrays.toString(windRight.applyWind(testData)), Arrays.toString(testData));
    }
    
}
