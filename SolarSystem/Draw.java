package SolarSystem;
import java.awt.*;
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

    //Constructor for adding images of the given planets to its body
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
         

    }
    


    /**
     * Displays one frame for the given coordinates
     * @param Graphics 
     * @return 
     */
    public void paintComponent (Graphics g) {


        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        //for higher resolution
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        setBackground(Color.black);

        //each celestial objects gets drawn into the Image
        for (int i = 0; i < 9 ; i++) {
            g2.setColor(State.colors[i]);
            
            //stores the x and y coordinates of the given celestial body, which is given by the index
             x = (int)Math.round(CelestialBody.scaleDownPosition(State.allPositions[i][index][0],i));
             y =  -(int)Math.round(CelestialBody.scaleDownPosition(State.allPositions[i][index][1],i));
            
            switch(i){
                //sun
                case 0: 
                radius = 30;
                finalIm = sun;
                break;
                //venus
                case 1:
                radius = 15;
                finalIm = venus;
                break;
                //earth
                case 2:
                radius = 25;
                finalIm =earth;
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
                radius = 60;
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
                case 8:
                radius = 5;
                break; }

                //responsiple for leaving a line behind the space probe to track the trajectory of the probe
                if(i==8){
                    for (int j = 0; j < index; j++) {
                        
                        //casting to an integer to draw a dot in the image later
                        int x2 = (int)Math.round(CelestialBody.scaleDownPosition(State.allPositions[i][j][0],i));
                        int y2 =  -(int)Math.round(CelestialBody.scaleDownPosition(State.allPositions[i][j][1],i));
                        int index1 = x2+450;
                        int index2 = y2+250;
                        
                        g2.drawLine(index1, index2, index1, index2);
                        
                    
                }

                } 
            //necesary shiftings to display the celestial body properly
            if(i==6){
                g2.drawImage(finalIm, x+400, y+200, null);
            }
            else{
                g2.drawImage(finalIm, x+450, y+250, null);
            } 
        }
        //for each call of the method the index gets incremented to get new coordinates for the next call
        index++;
    }
    }
    

  



