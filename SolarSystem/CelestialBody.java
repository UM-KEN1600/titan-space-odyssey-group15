package SolarSystem;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

import EulerSolver.*;

//represents comets, asteroids, moons etc

public class CelestialBody extends JPanel {
    public double mass;
    public double radius;

    public int rowInState;
    public static CelestialBody[] list = new CelestialBody[9];

    String name;
    Color color;
    double x1;
    double x2;
    int diameter = 20;
    double semiMajorAxisLength;
    double semiMinorAxisLength;
    static double scaleFactor = 7.0;
    EulerSolver.State state = new State();


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


    // public void setPosition(int rowIndex, int colIndex) {
    //     this.x1 = State.positions[rowIndex][colIndex];
    //     this.x2 = State.positions[rowIndex][colIndex+1];
    //     //this.x3 = state.positions[rowIndex][colIndex+2];
    // }


    public Point2D.Double getCoordinates(double x1, double x2) {
        double x = x2;
        double y = x2;
        return new Point2D.Double(x, y);
    }

    public static double getX1(int rowInState) {
        return State.positions[rowInState][0];
    }

    public static double getX2(int rowInState) {
        return State.positions[rowInState][1];
    }



  //  public void drawCelestialBody(Graphics2D g2) {

    //   int x = (int)Math.round(scaleDownPosition(this.x1));
      // int y = (int)Math.round(scaleDownPosition(this.x2));

      // if (x != (int)Math.round(scaleDownPosition(this.x1)) && y != (int)Math.round(scaleDownPosition(this.x2))) {
        //System.out.println("Casting from double to integer in drawCelestialBody is not correct");
       //}

            
        //g2.setColor(this.color);
        //g2.fillOval(x, y, diameter, diameter);
    //}

    public static double scaleDownPosition(double xValue, int index){
        if (index <= 4) {
            return scaleFactor * (xValue/10000000);
        }
        else {
            return scaleFactor / 2.5 * (xValue/10000000);
        }
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
