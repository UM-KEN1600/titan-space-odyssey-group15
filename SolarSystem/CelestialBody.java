package SolarSystem;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import EulerSolver.*;

//represents comets, asteroids, moons etc

public class CelestialBody {

    String name;
    Color color;
    double mass;
    double radius;
    double x1;
    double x2;
    EulerSolver.State state = new State();
    
    CelestialBody(String name, Color color, double mass, double radius, double x1, double x2){

        this.name = name;
        this.color= color;
        this.mass = mass;
        this.radius = radius;
        this.x1 = x1;
        this.x2 = x2;
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

    public Point getPosition(double x1, double x2) {
        int x = (int)Math.round(x1);
        int y = (int)Math.round(x2);
        return new Point(x, y);
    }

    public double getX1() {
        return x1;
    }

    public double getX2() {
        return x2;
    }

    public void setPosition(int rowIndex) {
        this.x1 = State.positions[rowIndex][0];
        this.x2 = State.positions[rowIndex][1];
        //this.x3 = state.positions[rowIndex][2];
    }

    public void setX1 (double x1) {
        this.x1 = x1;
    }
    
    public void setX2 (double x2) {
        this.x2 = x2;
    }

    public void drawCelestialBody(Graphics2D g2) {

        int radius = (int)Math.round(this.radius);
        int x1 = (int)Math.round(this.x1);
        int x2 = (int)Math.round(this.x2);

         // checking for correct casting
        if (radius != (int)Math.round(this.radius) &&  x1 != (int)Math.round(this.x1) && x2 != (int)Math.round(this.x2)) {
            System.out.println("Casting from double to integer in paintComponent is not correct.");
        }
            
        g2.setColor(this.color);
        g2.fillOval(x1, x2, radius, radius);
    }

    public void drawOrbit(Graphics2D g2) {
        //TO DO
    }



}
