package SolarSystem;

import java.awt.*;
import java.awt.geom.Point2D;
import javax.swing.*;
import EulerSolver.*;

/**
 * represents planets, sun, extends spaceship
 * contains all methods, properties and getters and setters for celestial bodies
 */

public class CelestialBody extends JPanel{
    public double mass;
    public double radius;

    public int rowInState;
    public static CelestialBody[] list = new CelestialBody[12];

    String name;
    Color color;
    double x1;
    double x2;
    int newX;
    int newY;
    int diameter = 20;
    static double scaleFactor = 7.0;
    State state = new State();
    Functions functions;

    CelestialBody(double mass, double radius, int rowInState, String name, Color color){

        this.name = name;
        this.color= color;
        this.mass = mass;
        this.radius = radius;

        this.rowInState = rowInState;
    }

    CelestialBody() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor (Color color) {
        this.color = color;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(Double mass) {
        this.mass = mass;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    /**
     * retrieves the x1 value of a celestial body stored in the 2D positions array 
     * @param rowInState the index representing the row that the specific celestial body is in
     * @return the x1 value stored in the 2D positions array
     */
    public static double getX1(int rowInState) {
        return State.positions[rowInState][0];
    }

     /**
     * retrieves the x2 value of a celestial body stored in the 2D positions array 
     * @param rowInState the index representing the row that the specific celestial body is in
     * @return the x2 value stored in the 2D positions array
     */
    public static double getX2(int rowInState) {
        return State.positions[rowInState][1];
    }

    /**
     * reduces the x1 and x2 coordinates of each celestial body to fit onto a 1000x600 frame
     * @param xValue the x value being scaled down
     * @param index the index representing the row of the 2D positions array
     * @return the x value after scaling
     */
    public static double scaleDownPosition(double xValue, int index){
        if (index <= 4) {
            return (scaleFactor / 2 * (xValue/10000000));
        }
        else {
            return ((scaleFactor / 2.5) * (xValue/10000000));
        }
    }

    public void updatePosition(double [] newPosition, int index) {
        this.newX = (int)Math.round(scaleDownPosition(newPosition[0],index));
        this.newY = (int)Math.round(scaleDownPosition(newPosition[1],index));
    }

    public int getNewX() {
        return this.newX;
    }

    public int getNewY() {
        return this.newY;
    }

    /**
     * sets up the celestial bodies with their respective mass, radisu and row in the State matrix. They are then added to the list of celestial bodies
     */

    public static void setupCelestialBodies()
    {
        //temporary colour for initialization, can be changed for GUI later
        Color tempColor = new Color(0, 0, 0);

        CelestialBody sun = new CelestialBody(1.9885 * Math.pow(10, 30), 1, 0, "Sun", Color.YELLOW);
        CelestialBody venus = new CelestialBody(48.685 * Math.pow(10, 23), 6051.8, 1, "Venus", Color.DARK_GRAY);
        CelestialBody earth = new CelestialBody(5.97219 * Math.pow(10, 24), 6370, 2, "Earth", Color.GREEN);
        CelestialBody moon = new CelestialBody(7.349* Math.pow(10, 22), 1, 3, "Moon", Color.GRAY);
        CelestialBody mars = new CelestialBody(6.4171* Math.pow(10, 23), 3389.5, 4, "Mars", Color.RED);
        CelestialBody jupiter = new CelestialBody(189818722* Math.pow(10, 19),69911 , 5, "Jupiter", Color.PINK);
        CelestialBody saturn = new CelestialBody(5.6834* Math.pow(10, 26), 58232, 6, "Saturn", Color.LIGHT_GRAY);
        CelestialBody titan = new CelestialBody(13455.3* Math.pow(10, 19), 2575, 7, "Titan", Color.ORANGE);
        CelestialBody spaceship = new Spaceship(50000, 1, 11, "Spaceship", tempColor);
        CelestialBody mercury = new CelestialBody(3.302* Math.pow(10, 23), 2439, 8, "Mercury", Color.RED);
        CelestialBody neptune = new CelestialBody(102.409* Math.pow(10, 24), 24622, 9, "Neptune", Color.BLUE);
        CelestialBody uranus = new CelestialBody(86.813* Math.pow(10, 24), 25362, 10, "Uranus", Color.CYAN);

        list[0] = sun;
        list[1] = venus;
        list[2] = earth;
        list[3] = moon;
        list[4] = mars;
        list[5] = jupiter;
        list[6] = saturn;
        list[7] = titan;
        list[8] = mercury;
        list[9] = neptune;
        list[10] = uranus;
        list[11] = spaceship;
        
    }

}
