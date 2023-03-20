package SolarSystem;

import java.awt.*;
import java.awt.Graphics2D;
import EulerSolver.*;
import javax.swing.JComponent;
import javax.swing.JPanel;

//represents the drawing of the celestialbodies

public class Draw extends JPanel {

    CelestialBody [] celestialBodies;
    Spaceship spaceship;
    int radius = 40;    //size of earth (default)

    public Draw() {}

    public void paint (Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        setBackground(Color.black);

        for (int i = 0; i < 9 ; i++) {
            int x = (int)Math.round(CelestialBody.scaleDownPosition(CelestialBody.getX1(i),i));
            int y = (int)Math.round(CelestialBody.scaleDownPosition(CelestialBody.getX2(i),i));
                  
        if (x != (int)Math.round(CelestialBody.scaleDownPosition(CelestialBody.getX1(i),i)) && y != (int)Math.round(CelestialBody.scaleDownPosition(CelestialBody.getX2(i),i))) {
             System.out.println("Casting from double to integer in Draw class is not correct");
        }

        g2.setColor(State.colors[i]);
        

        switch(i){
            //sun
            case 0: 
            radius = 100;
            break;
            //venus
            case 1:
            radius = 20;
            break;
            //moon
            case 3: 
            radius = 10;
            break;
            //mars
            case 4:
            radius = 30;
            break;
            //jupiter
            case 5: 
            radius = 60;
            break;
            //saturn
            case 6:
            radius = 60;
            break;
            //titan
            case 7: 
            radius = 10;
            break;}
         
            g2.fillOval(x+400, y+250, radius, radius);

        }


    }


}


