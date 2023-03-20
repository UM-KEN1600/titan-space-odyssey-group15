package SolarSystem;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import EulerSolver.*;

//represents comets, asteroids, moons etc

public class CelestialBody {

    String name;
    Color color;
    double mass;
    double radius;
    double x1;
    double x2;
    int diameter;
    double semiMajorAxisLength;
    double semiMinorAxisLength;
    EulerSolver.State state = new State();
    
    public CelestialBody(String name, Color color, double mass, double radius){

        this.name = name;
        this.color= color;
        this.mass = mass;
        this.radius = radius;
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

    public double getSemiMinorAxisLength() {
        return semiMinorAxisLength;
    }

}
