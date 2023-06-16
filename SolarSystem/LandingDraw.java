package SolarSystem;

import java.awt.*;
import java.awt.geom.AffineTransform;

import javax.swing.*;

public class LandingDraw extends JPanel {

    int x = 0;
    int y = 0;
    double rotationAngle = 0.0;
    int spaceshipCenterX;
    int spaceshipCenterY; 

    Image titan;
    Image spaceShip;
    Image background;


    public LandingDraw() {
        // Scaling Titan and spaceship to appropriate sizes
        ImageIcon temp = new ImageIcon("titan.png");
        Image edit = temp.getImage();
        Image finalImg = edit.getScaledInstance(1600, 1600, java.awt.Image.SCALE_SMOOTH);
        titan = new ImageIcon(finalImg).getImage();

        temp = new ImageIcon("spaceship.png");
        Image edit8 = temp.getImage();
        Image finalImg8 = edit8.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        spaceShip = new ImageIcon(finalImg8).getImage();

        temp = new ImageIcon("space.png");
        Image edit9 = temp.getImage();
        Image finalImg9 = edit9.getScaledInstance(1000, 600, java.awt.Image.SCALE_SMOOTH);
        background = new ImageIcon(finalImg9).getImage();

        setFocusable(true);
    }

    /**
     * Displays one frame for the given coordinates which are retrieved from the allPositions array in the State class
     * 
     * @param Graphics
     * @return
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // For higher resolution
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.drawImage(background, 0, 0, null);
        // Draw Titan
        x = -300; // x-coordinate for Titan
        y = 400; // y-coordinate for Titan
        
        g2.drawImage(titan, x, y, null);

        
        // Draw spaceship
        int spaceshipX = 485; // x-coordinate for spaceship
        int spaceshipY = 20; // y-coordinate for spaceship
        
        rotateSpaceship(-135);

        int spaceshipCenterX = spaceshipX + spaceShip.getWidth(null) / 2;
        int spaceshipCenterY = spaceshipY + spaceShip.getHeight(null) / 2;

        AffineTransform oldTransform = g2.getTransform();

        g2.rotate(Math.toRadians(rotationAngle), spaceshipCenterX, spaceshipCenterY);

        g2.drawImage(spaceShip, spaceshipX, spaceshipY, null);

        g2.setTransform(oldTransform);
        
    }

    public void rotateSpaceship(double degrees) {
        rotationAngle = degrees;
        repaint();
    }


}
