package SolarSystem;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import EulerSolver.*;

//represents comets, asteroids, moons etc

public class CelestialBody {
    public double mass;
    public double radius;
    public int rowInState;
    CelestialBody[] list = new CelestialBody[9];

    String name;
    Color color;

    double x1;
    double x2;
    int diameter;
    double semiMajorAxisLength;
    double semiMinorAxisLength;
    EulerSolver.State state = new State();


    CelestialBody(double mass, double radius, int rowInState, String name, Color color){

        this.name = name;
        this.color= color;
        this.mass = mass;
        this.radius = radius;

        this.rowInState = rowInState;

    }

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

    public void setPosition(int rowIndex, int colIndex) {
        this.x1 = State.positions[rowIndex][colIndex];
        this.x2 = State.positions[rowIndex][colIndex+1];
        //this.x3 = state.positions[rowIndex][colIndex+2];
    }

    public Point2D.Double setCoordinates(double x1, double x2) {
        double x = x1;
        double y = x2;
        return new Point2D.Double(x, y);
    }

    public double getX1() {
        return x1;
    }

    public double getX2() {
        return x2;
    }

    public void setX1 (double x1) {
        this.x1 = x1;
    }
    
    public void setX2 (double x2) {
        this.x2 = x2;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public void drawCelestialBody(Graphics2D g2) {

        int diameter = (int)Math.round(this.diameter);
        int x1 = (int)Math.round(this.x1);
        int x2 = (int)Math.round(this.x2);

         // checking for correct casting
        if (diameter != (int)Math.round(this.diameter) &&  x1 != (int)Math.round(this.x1) && x2 != (int)Math.round(this.x2)) {
            System.out.println("Casting from double to integer in paintComponent is not correct.");
        }
            
        g2.setColor(this.color);
        g2.fillOval(x1, x2, diameter, diameter);
    }

    public double getSemiMajorAxisLength() {
        return semiMajorAxisLength;
    }


    /**
     * sets up the celestial bodies with their respective mass, radisu and row in the State matrix. They are then added to the list of celestial bodies
     */
    public void setupCelestialBodies()
    {
        //temporary colour for initialization, can be changed for GUI later
        Color tempColor = new Color(0, 0, 0);

        CelestialBody sun = new CelestialBody(1.9885 * Math.pow(10, 30), 1, 0, "Sun", tempColor);
        CelestialBody venus = new CelestialBody(48.685 * Math.pow(10, 23), 1, 1, "Venus", tempColor);
        CelestialBody earth = new CelestialBody(5.97219 * Math.pow(10, 24), 6370, 2, "Earth", tempColor);
        CelestialBody moon = new CelestialBody(7.349* Math.pow(10, 22), 1, 3, "Moon", tempColor);
        CelestialBody mars = new CelestialBody(6.4171* Math.pow(10, 23), 1, 4, "Mars", tempColor);
        CelestialBody jupiter = new CelestialBody(189818722* Math.pow(10, 19), 1, 5, "Jupiter", tempColor);
        CelestialBody saturn = new CelestialBody(5.6834* Math.pow(10, 26), 1, 6, "Saturn", tempColor);
        CelestialBody titan = new CelestialBody(13455.3* Math.pow(10, 19), 2575, 7, "Titan", tempColor);
        CelestialBody spaceship = new Spaceship(50000, 1, 8, "Spaceship", tempColor);

        list[0] = sun;
        list[1] = venus;
        list[2] = earth;
        list[3] = moon;
        list[4] = mars;
        list[5] = jupiter;
        list[6] = saturn;
        list[7] = titan;
        list[8] = spaceship;

    }

}
