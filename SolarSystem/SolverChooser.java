package SolarSystem;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import PhysicsEngine.*;

public class SolverChooser {
    
    public static  String[] solvers = {"Euler" , "Runge-Kutta4" , "Runge-Kutta2", "Adam-Bashforth" , "Heuns" };
    static iSolver solver;
    static Simulation sim;
    public static void main(String[] args)  {

        JLabel label = new JLabel("Select solver");
        label.setBounds(40,10,120,10);
        label.setAlignmentX(FlowLayout.CENTER);

        JComboBox chooser = new JComboBox<>(solvers);
        chooser.setBounds(40,30 ,120, 30);
        chooser.setSelectedItem(null);
        
        
        JButton runButton = new JButton("Run");
        runButton.setBounds(50, 90,100, 30);
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

                Main.run(solver);
            }
            }});

            JFrame solverChooser = new JFrame();
            solverChooser.setSize(200,200);
            solverChooser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            solverChooser.setLocationRelativeTo(null);
            solverChooser.setLayout(null);
            solverChooser.add(label);
            solverChooser.add(chooser);
            solverChooser.add(runButton);
            solverChooser.setVisible(true);

    
    }
}
