package SolarSystem;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;

import EulerSolver.*;

public class Spaceship {
    
    public double velocity;
    public final int MASS = 50000;
    public double acceleration;
    public double x1;
    public double x2;
    //public double x3;
    CelestialBody celestialBody;


    public Spaceship(double x1, double x2, double velocity, double acceleration) {
        this.x1 = x1;
        this.x2 = x2;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }

    public Point2D.Double getPosition(double x1, double x2) {
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

    public boolean checkCollision (CelestialBody celestialBody) {
        if (celestialBody.setCoordinates(x1, x2) == this.getPosition(x1, x2)) {
            return true;
        }
        return false;
    }

    public void computeAcceleration(double initialVelocity, double finalVelocity, double time) {
        double deltaV = (finalVelocity = initialVelocity);
        acceleration = (deltaV / time);
    }

}
