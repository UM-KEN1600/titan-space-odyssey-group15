package SolarSystem;

import java.awt.*;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import EulerSolver.*;
import javax.swing.*;

//represents the drawing of the celestialbodies

public class Draw extends JPanel {

    CelestialBody [] celestialBodies;
    Spaceship spaceship;
    int radius = 40;    //size of earth (default)
    static int index = 0;

    public Draw() {
    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        setBackground(Color.black);

       // while (index < 50) {
        for (int i = 0; i < 8 ; i++) {
            
            int x = (int)Math.round(CelestialBody.scaleDownPosition(State.allPositions[i][index][0],i));
            int y =  -(int)Math.round(CelestialBody.scaleDownPosition(State.allPositions[i][index][1],i));
            
                  
        // if (x != (int)Math.round(CelestialBody.scaleDownPosition(CelestialBody.getX1(i),i)) && y != (int)Math.round(CelestialBody.scaleDownPosition(CelestialBody.getX2(i),i))) {
        //      System.out.println("Casting from double to integer in Draw class is not correct");
        // }

        g2.setColor(State.colors[i]);
        

        switch(i){
            //sun
            case 0: 
            radius = 20;
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
         
            
            g2.fillOval(x+450, y+250, radius, radius);
        
        }
        index++;
      //  }
    }
    }
    

    // public void startTimer() {
    //     Timer t = new Timer(TIME, new ActionListener(){
    //         public void actionPerformed(ActionEvent e){
    //             repaint();
	// 		}
    //     }); 

	// 	// TIME in milliseconds
	// 	t.start();
    // }



