package SolarSystem;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import PhysicsEngine.*;

public class SolverChooser {
    
    public static  String[] solvers = {"Euler" , "Runge-Kutta4" , "Runge-Kutta2", "Adam-Bashforth" , "Heuns" };
    static iSolver solver;
    static Simulation sim;
    static JFrame solverChooser;
        public static void main(String[] args)  {

        JLabel label = new JLabel("Select solver");
        label.setBackground(Color.BLACK);
        label.setFont(new Font("Consolas",Font.BOLD,14));

        JComboBox chooser = new JComboBox<>(solvers);
        chooser.setSelectedItem(null);
        chooser.setFont(new Font("Consolas",Font.BOLD,12));

        
        JButton runButton = new JButton("Run");
        runButton.setFont(new Font("Consolas",Font.BOLD,12));


        solverChooser = new JFrame();
        solverChooser.setSize(200,200);
        solverChooser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        solverChooser.setLocationRelativeTo(null);
        //solverChooser.setUndecorated(true);
        solverChooser.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
        solverChooser.getContentPane().setBackground(new Color(255,255,255));
        solverChooser.add(label);
        solverChooser.add(chooser);
        solverChooser.add(runButton);
        solverChooser.setVisible(true);
        
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            if(e.getSource()== runButton){
                String value = chooser.getSelectedItem().toString();
                if(value == "Euler" )
                solver = new EulerSolver();

                if(value == "Runge-Kutta4")
                solver = new RungeKutta4Solver();

                if(value == "Runge-Kutta2")
                solver = new RungeKuttaSolver(2);

                if(value == "Adam-Bashforth")
                solver = new AdamBashforthSolver();

                if(value == "Heuns")
                solver = new HeunsSolver();

                solverChooser.dispose();
                Main.run(solver);
            }
            }
        });
    }
}
