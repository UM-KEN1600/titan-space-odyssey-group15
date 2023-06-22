package SolarSystem;

import java.awt.*;
import java.awt.geom.AffineTransform;


import javax.swing.*;

import PhysicsEngine.States.State;
import PhysicsEngine.Wind.Wind;

public class LandingDraw extends JPanel {
    // Titan 
    int titanX = 0;
    int titanY = 0;
    
    //Spaceship
    int spaceshipCenterX;
    int spaceshipCenterY; 

    //Arrow
    int arrowX = 0;
    int arrowY = 0;
    int permX = 0;
    int permY = 0;

    int spaceshipX; 
    int spaceshipY; 
    static int i = 0;

    //Angle rotation for every image used
    double arrowRotate = 0.0;
    double titanRotationAngle = 0.0;
    double rotationAngle = 0.0;

    //Images
    Image titan;
    Image spaceShip;
    Image background;
    Image arrow;

    boolean paintFlag = false;


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

        //perm are the coordinates that are used to position the letters
        permX = 875;
        permY = 75;
        //these coordinates are used to draw the arrow
        arrowX = 883;
        arrowY = 67;

        //Calculates the center of the arrow
        int arrowCenterX = permX + arrow.getWidth(null) / 2;
        int arrowCenterY = permY + arrow.getHeight(null) / 2;
        


        //Arrow is drawn depending on the way the wind is blowing
        if(Wind.getDirection() == true){
            rotateArrow(90);
        } else {
            rotateArrow(-90);
        }
        

        AffineTransform arrowTransform = g2.getTransform();

        g2.rotate(Math.toRadians(rotationAngle), arrowCenterX, arrowCenterY);

        g2.drawImage(arrow, arrowX, arrowY, null);

        g2.setTransform(arrowTransform);



        // Draw Titan
        titanX = -300; // x-coordinate for Titan
        titanY = 400; // y-coordinate for Titan
        
        rotateTitan(45); 
        
        //Calculates the center of Titan
        int titanCenterX = titanX + titan.getWidth(null) / 2;
        int titanCenterY = titanY + titan.getHeight(null) / 2;

        AffineTransform titanTransform = g2.getTransform();

        g2.rotate(Math.toRadians(titanRotationAngle), titanCenterX, titanCenterY);

        g2.drawImage(titan, titanX, titanY, null);

        g2.setTransform(titanTransform);

        

        

        // sets initial coordiantes for spaceship
        if(i < 100){
        spaceshipX =(int)(CelestialBody.scaleDownLanding(State.landingPositionsAngle[i][0])) + 485;
        spaceshipY =-(int)(CelestialBody.scaleDownLanding(State.landingPositionsAngle[i][1])) + 400;
             
                g2.drawImage(spaceShip, spaceshipX, spaceshipY, null);
        }
        else 
        g2.drawImage(spaceShip , spaceshipX, spaceshipY, null);
        
        rotateSpaceship(-135);
        
        
        // calculates the center point coordinates on the spaceship
        int spaceshipCenterX = spaceshipX + spaceShip.getWidth(null) / 2;
        int spaceshipCenterY = spaceshipY + spaceShip.getHeight(null) / 2;

        AffineTransform oldTransform = g2.getTransform();

        g2.rotate(Math.toRadians(rotationAngle), spaceshipCenterX, spaceshipCenterY);


        g2.setTransform(oldTransform);


        Font font = new Font("Arial", Font.BOLD, 16);
        g2.setFont(font);
        g2.setColor(Color.RED);
        int textOffset = 75;
        // Drawing the North, South, East, West labels around the arrow
        g2.drawString("N", arrowCenterX, arrowCenterY - textOffset );
        g2.drawString("S", arrowCenterX, arrowCenterY + textOffset);
        g2.drawString("E", arrowCenterX + textOffset, arrowCenterY);
        g2.drawString("W", arrowCenterX - textOffset, arrowCenterY);

        i++;
        // if(!paintFlag)
        // i++;
        // else 
        // paintFlag = false;

    }
    /**
     * rotates the spaceship
     * 
     * @param double
     * @return
     */
    public void rotateSpaceship(double degrees) {
        rotationAngle = degrees;
        repaint();
    }


    /**
     * rotates the arrow
     * 
     * @param double
     * @return
     */
    public void rotateArrow(double degrees) {
        arrowRotate = degrees;
        repaint();
    }

    /**
     * rotates Titan
     * 
     * @param double
     * @return
     */
    public void rotateTitan(double degrees) {
        titanRotationAngle = degrees;
    repaint();
}



}
