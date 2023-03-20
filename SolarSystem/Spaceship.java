package SolarSystem;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;

import EulerSolver.*;

//represents solar system with planets, celestial bodies, the sun etc.

public class Spaceship extends CelestialBody {
    public double velocity;
    public final int MASS = 50000;
    public double acceleration;
    public double x1;
    public double x2;
    //public double x3;
    CelestialBody celestialBody;

    double mass = 50000;


    public Spaceship(double mass, double radius, int rowInState, String name, Color color)
    {
        super(mass, radius, rowInState, name, color);
    }

    public Point2D.Double setCoordinates(double x1, double x2) {
        double x = x1;
        double y = x2;
        return new Point2D.Double(x, y);
    }

    public void setPosition(double x1, double x2) {
        this.x1 = x1;
        this.x2 = x2;
        //this.x3 = x3;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }    

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration (double acceleration) {
        this.acceleration = acceleration;
    }

    public void drawSpaceship(Graphics2D g2) {
        //TO DO
        
    }

    public void move(double distX, double distY) {
        this.x1 += distX;
        this.x2 += distY;
        //this.x3 += distZ;
    }


}
