package SolarSystem;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.*;
import java.awt.*;
import javax.swing.JPanel;

public class DrawBody extends JPanel {

    CelestialBody celestialBody = new CelestialBody();

    public void paint(Graphics2D g) {
        super.paint(g);

        // looping through the celestial body array and drawing a planet / moon / sun
    
            int x = (int)Math.round(body.scaleDownPosition(body.getX1()));
            int y = (int)Math.round(body.scaleDownPosition(body.getX2()));
      
            if (x != (int)Math.round(body.scaleDownPosition(body.x1)) && y != (int)Math.round(body.scaleDownPosition(body.x2))) {
              System.out.println("Casting from double to integer in drawCelestialBody is not correct");
             }
      
                  
              g.setColor(body.getColor());
              g.fillOval(x, y, body.diameter, body.diameter);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        setBackground(Color.black);
        // draw the sun
        g2d.setColor(Color.YELLOW);
        g2d.fillOval();
        }

    }
}
