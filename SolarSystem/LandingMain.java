package SolarSystem;

import javax.swing.JFrame;

public class LandingMain {

      public static void main(String[] args) {
        
        LandingDraw dr = new LandingDraw();
           JFrame landFrame = new JFrame("Landing");
                            landFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            landFrame.setSize(1000, 600);
                            landFrame.setLocationRelativeTo(null);
                            landFrame.add(dr);
                            landFrame.setVisible(true);
    }
}
