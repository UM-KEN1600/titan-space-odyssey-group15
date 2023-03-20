package SolarSystem;

import java.awt.*;
import java.awt.Graphics2D;
import EulerSolver.*;


import javax.swing.JComponent;
import javax.swing.JPanel;

public class Draw extends JPanel {

    CelestialBody [] celestialBodies;
    Spaceship spaceship;
    int radius = 30;    //earth default
    
    

    public Draw() {
    }

    public void paint (Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        setBackground(Color.black);

        for (int i = 0; i < 9 ; i++) {
            int x = (int)Math.round(CelestialBody.scaleDownPosition(CelestialBody.getX1(i),i));
            int y = (int)Math.round(CelestialBody.scaleDownPosition(CelestialBody.getX2(i),i) );
                  
       //     if (x != (int)Math.round(body.scaleDownPosition(body.x1)) && y != (int)Math.round(body.scaleDownPosition(body.x2))) {
           //     System.out.println("Casting from double to integer in drawCelestialBody is not correct");
          //  }
            g2.setColor(State.colors[i]);
            //moons

            switch(i){
            case 0: 
            radius = 70;
            break;
            case 1:
            radius = 25;
            break;
            case 3: 
            radius = 10;
            break;
            case 4:
            radius = 20;
            break;
            case 5: 
            radius = 50;
            break;
            case 6:
            radius = 50;
            break;
            case 7: 
            radius = 10;
            break;}
         
            g2.fillOval(x+450, y+250, radius, radius);

        }


    }


}

//     @Override
//     public void paintComponent (Graphics g) {
//         super.paintComponent(g);
//         Graphics2D g2 = (Graphics2D) g;
//         CelestialBody body = new CelestialBody();

//         // looping through the celestial body array and drawing a planet / moon / sun
//       //  for (CelestialBody body : celestialBodies) {
//             int x = (int)Math.round(body.scaleDownPosition(body.getX1()));
//             int y = (int)Math.round(body.scaleDownPosition(body.getX2()));
      
//             if (x != (int)Math.round(body.scaleDownPosition(body.x1)) && y != (int)Math.round(body.scaleDownPosition(body.x2))) {
//               System.out.println("Casting from double to integer in drawCelestialBody is not correct");
//              }
      
                  
//               g2.setColor(body.getColor());
//               g2.fillOval(x, y, body.diameter, body.diameter);
//      //   }

//         //drawing spaceship
//         spaceship.drawSpaceship(g2);
//     }

    
// }
