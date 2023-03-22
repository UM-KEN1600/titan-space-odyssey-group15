package SolarSystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import EulerSolver.*;
import javax.swing.*;

//represents the drawing of the celestialbodies

public class Draw extends JPanel {

    CelestialBody [] celestialBodies;
    Spaceship ship;
    int radius = 40;    //size of earth (default)
    static int index = 0;
    int x = 0;
    int y = 0;
    Image earth;
    Image sun;
    Image venus;
    Image moon;
    Image finalIm;
    Image mars;
    Image jupiter;
    Image saturn;
    Image titan;
    Image spaceship;

    public Draw() {
        ImageIcon e = new ImageIcon("earth.png");
        Image edit = e.getImage();
        Image finalImg = edit.getScaledInstance(25,25,java.awt.Image.SCALE_SMOOTH);
         earth = new ImageIcon(finalImg).getImage(); 
        
        ImageIcon s = new ImageIcon("sun.png");
        Image edit1 = s.getImage();
        Image finalImg1 = edit1.getScaledInstance(30,30,java.awt.Image.SCALE_SMOOTH);
        sun = new ImageIcon(finalImg1).getImage(); 

        ImageIcon v = new ImageIcon("venus.png");
        Image edit2 = v.getImage();
        Image finalImg2 = edit2.getScaledInstance(20,20,java.awt.Image.SCALE_SMOOTH);
        venus = new ImageIcon(finalImg2).getImage(); 

        ImageIcon m = new ImageIcon("moon.png");
        Image edit3 = m.getImage();
        Image finalImg3 = edit3.getScaledInstance(10,10,java.awt.Image.SCALE_SMOOTH);
        moon = new ImageIcon(finalImg3).getImage(); 

        ImageIcon m1 = new ImageIcon("mars.png");
        Image edit4 = m1.getImage();
        Image finalImg4 = edit4.getScaledInstance(30,30,java.awt.Image.SCALE_SMOOTH);
        mars = new ImageIcon(finalImg4).getImage(); 

        ImageIcon j = new ImageIcon("jupiter.png");
        Image edit5 = j.getImage();
        Image finalImg5 = edit5.getScaledInstance(60,60,java.awt.Image.SCALE_SMOOTH);
         jupiter = new ImageIcon(finalImg5).getImage(); 

         ImageIcon s1 = new ImageIcon("saturn.png");
         Image edit6 = s1.getImage();
         Image finalImg6 = edit6.getScaledInstance(130,130,java.awt.Image.SCALE_SMOOTH);
          saturn = new ImageIcon(finalImg6).getImage(); 

        ImageIcon t = new ImageIcon("titan.png");
        Image edit7 = t.getImage();
        Image finalImg7 = edit7.getScaledInstance(10,10,java.awt.Image.SCALE_SMOOTH);
         titan = new ImageIcon(finalImg7).getImage(); 

         ImageIcon sp = new ImageIcon("spaceship.png");
        Image edit8 = sp.getImage();
        Image finalImg8 = edit8.getScaledInstance(10,10,java.awt.Image.SCALE_SMOOTH);
         spaceship = new ImageIcon(finalImg8).getImage(); 
         
    
    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        setBackground(Color.black);

       // while (index < 50) {
        for (int i = 0; i < 9 ; i++) {
            g2.setColor(State.colors[i]);
            

            x = (int)Math.round(CelestialBody.scaleDownPosition(State.allPositions[i][index][0],i));
            y =  -(int)Math.round(CelestialBody.scaleDownPosition(State.allPositions[i][index][1],i));
            
    
            if (x != (int)Math.round(CelestialBody.scaleDownPosition(State.allPositions[i][index][0],i)) || y !=  -(int)Math.round(CelestialBody.scaleDownPosition(State.allPositions[i][index][1],i))){
                System.out.println("Casting for " + i + " is not correct.");
            }
            
        switch(i){
            //sun
            case 0: 
            radius = 35;
            finalIm = sun;
            break;
            //venus
            case 1:
            radius = 15;
            finalIm = venus;
            break;
            //earth
            case 2:
            radius = 20;
            finalIm = earth;
            break;
            //moon
            case 3: 
            radius = 10;
            finalIm = moon;
            break;
            //mars
            case 4:
            radius = 30;
            finalIm = mars;
            break;
            //jupiter
            case 5: 
            radius = 70;
            finalIm = jupiter;
            break;
            //saturn
            case 6:
            radius = 60;
            finalIm = saturn;
            break;
            //titan
            case 7: 
            radius = 10;
            finalIm = titan;
            break;
            //spaceship
            case 8:
            radius = 10;
            finalIm = spaceship;
            break; }

        
            g2.drawImage(finalIm, x+450-radius, y+250-radius, null);
        
        }
        index++;
        }
    }
    

  


