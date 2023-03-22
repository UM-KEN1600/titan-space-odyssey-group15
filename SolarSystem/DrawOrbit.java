package SolarSystem;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.*;
import javax.swing.*;

public class DrawOrbit extends Canvas{

    // Define the semi-major axis, semi-minor axis, and eccentricity of the orbit
    double a; // distance between the center and the aphelion
    double b; // distance between the center and the perihelion
    CelestialBody celestialBody;

    public DrawOrbit(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double computeEccentricity(double a, double b) {
        double aSquared = Math.pow(a, 2);
        double bSquared = Math.pow(b, 2);
        double distance = (aSquared-bSquared);
        double eccentricity = ((Math.sqrt(distance)) / a);
        return eccentricity;
    }

    public void paint(Graphics2D g2) {
        super.paint(g2);
    
    }
}


    

