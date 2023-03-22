package SolarSystem;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import EulerSolver.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static CelestialBody body = new CelestialBody();
    public static Draw drawPanel = new Draw();
  //  State state = new State();
  //  static Functions functions = new Functions();
  //  double [] newPositions;

    public static void main(String[] args)  {

        Simulation.planetarySetUp();

        //frame
        JFrame mainFrame = new JFrame("Solar System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1000, 600);
        mainFrame.add(new CelestialBody(0, 0, 0, null, null));
        mainFrame.setLocationRelativeTo(null);
        mainFrame.add(drawPanel);
        mainFrame.setVisible(true);

        // Timer t = new Timer(200, new ActionListener(){
        //     public void actionPerformed(ActionEvent e){
        //         drawPanel.repaint();
        //     }
        // }); 
        
        // t.start();
    
        // // Add a "Stop" button to the UI
        // JButton stopButton = new JButton("Stop");
        // stopButton.addActionListener(new ActionListener() {
        // public void actionPerformed(ActionEvent e) {
        //     t.stop(); // Stop the timer
        // }
        // });
        //     mainFrame.add(stopButton, BorderLayout.SOUTH);

    
        
        Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            int a = 0;
            @Override
            public void run() {
                drawPanel.repaint();
                if(a == 48)
                {
                    t.cancel();
                }
                a++;
            }
            
        };

        t.schedule(tt, 0, 200);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            t.schedule(tt, 0, 200);;
            }
        });
            mainFrame.add(startButton, BorderLayout.NORTH);

}

}
