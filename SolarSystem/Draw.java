package SolarSystem;

import java.awt.*;
import java.awt.Graphics2D;
import EulerSolver.*;


import javax.swing.JComponent;

public class Draw extends JComponent {

    CelestialBody [] celestialBodies = new CelestialBody[11];
    Spaceship spaceship;

    public Draw(CelestialBody [] celestialBodies, Spaceship spaceship) {
        this.celestialBodies = celestialBodies;
        this.spaceship = spaceship;
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // looping through the celestial body array and drawing a planet / moon / sun
        for (CelestialBody body : celestialBodies) {
            body.drawCelestialBody(g2d);
        }

        //drawing spaceship
        spaceship.drawSpaceship(g2d);
    }

    
}
