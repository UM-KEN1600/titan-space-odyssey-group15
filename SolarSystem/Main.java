package SolarSystem;

import java.util.ArrayList;
import EulerSolver.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static CelestialBody body = new CelestialBody();
    public static Draw drawPanel = new Draw();
    State state = new State();
    static Functions functions = new Functions();
    double [] newPositions;

    public static void main(String[] args)  {

        //frame
        JFrame mainFrame = new JFrame("Solar System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1000, 600);
        mainFrame.add(new CelestialBody(0, 0, 0, null, null));
        mainFrame.setLocationRelativeTo(null);
        mainFrame.add(drawPanel);

        // AnimationListener Listener
		class AnimationListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				for (int i = 0; i < 9 ; i++) {
                    double [] newPositions = functions.newPositionOfBody(50, CelestialBody.list[i]);
                CelestialBody.list[i].updatePosition(newPositions, i);
                }
				mainFrame.repaint();
			}
		}

 		
		AnimationListener animationListener = new AnimationListener();
		final int TIME = 200;
		Timer t = new Timer(TIME, animationListener); 	// TIME in milliseconds
		t.start(); 

 		
        mainFrame.setVisible(true);
        
    //     // update the position of each celestial body every 100 milliseconds
    //     Timer timer = new Timer(100, new ActionListener() {
    //         @Override
    //         public void actionPerformed(ActionEvent e) {
    //             for (int i = 0; i < 9; i++) {
    //                 double[] newPosition = functions.newPositionOfBody(0.1, CelestialBody.list[i]);
    //                 CelestialBody.list[i].updatePosition(newPosition, i);
    //             }
    //             drawPanel.repaint();
    //         }
    //     });
    //     timer.start();
    // }
    }

}
