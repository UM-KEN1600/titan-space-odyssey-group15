package SolarSystem;

import java.awt.*;
import java.awt.geom.AffineTransform;


import javax.swing.*;

public class LandingDraw extends JPanel {

    int titanX = 0;
    int titanY = 0;
    double rotationAngle = 0.0;
    int spaceshipCenterX;
    int spaceshipCenterY; 
    int arrowX = 0;
    int arrowY = 0;
    int permX = 0;
    int permY = 0;
    double arrowRotate = 0.0;


    Image titan;
    Image spaceShip;
    Image background;
    Image arrow;


    public LandingDraw() {
        // Scaling Titan and spaceship to appropriate sizes
        ImageIcon temp = new ImageIcon("titan.png");
        Image edit = temp.getImage();
        Image finalImg = edit.getScaledInstance(1600, 1600, java.awt.Image.SCALE_SMOOTH);
        titan = new ImageIcon(finalImg).getImage();

        temp = new ImageIcon("spaceship.png");
        Image edit1 = temp.getImage();
        Image finalImg1 = edit1.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        spaceShip = new ImageIcon(finalImg1).getImage();

        temp = new ImageIcon("space.png");
        Image edit2 = temp.getImage();
        Image finalImg2 = edit2.getScaledInstance(1000, 600, java.awt.Image.SCALE_SMOOTH);
        background = new ImageIcon(finalImg2).getImage();

        temp = new ImageIcon("arrow.png");
        Image edit3 = temp.getImage();
        Image finalImg3 = edit3.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        arrow = new ImageIcon(finalImg3).getImage();

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


        permX = 875;
        permY = 75;
        arrowX = 883;
        arrowY = 67;
        int arrowCenterX = permX + arrow.getWidth(null) / 2;
        int arrowCenterY = permY + arrow.getHeight(null) / 2;
        
        rotateArrow(90);

        AffineTransform arrowTransform = g2.getTransform();

        g2.rotate(Math.toRadians(rotationAngle), arrowCenterX, arrowCenterY);

        g2.drawImage(arrow, arrowX, arrowY, null);

        g2.setTransform(arrowTransform);



        // Draw Titan
        titanX = -300; // x-coordinate for Titan
        titanY = 400; // y-coordinate for Titan
        
        g2.drawImage(titan, titanX, titanY, null);

        
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


        Font font = new Font("Arial", Font.BOLD, 16);
        g2.setFont(font);
        g2.setColor(Color.RED);
        int textOffset = 75;

        g2.drawString("N", arrowCenterX, arrowCenterY - textOffset );
        g2.drawString("S", arrowCenterX, arrowCenterY + textOffset);
        g2.drawString("E", arrowCenterX + textOffset, arrowCenterY);
        g2.drawString("W", arrowCenterX - textOffset, arrowCenterY);
        
    }

    public void rotateSpaceship(double degrees) {
        rotationAngle = degrees;
        repaint();
    }


    
    public void rotateArrow(double degrees) {
        arrowRotate = degrees;
        repaint();
    }


}
