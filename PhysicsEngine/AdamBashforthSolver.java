package PhysicsEngine;
import SolarSystem.CelestialBody;


// Is not implementing the interface cause the method solve here needs also to be passed the double[][][] preOldState parameter, ill find a way to change it
public class AdamBashforthSolver implements iSolver {

    private double[][][] preOldState;
    private boolean bootStrapped = false;

    public void setPreOldState(double[][][] preOldState){
        this.preOldState = preOldState;
    }

    public double[][][] solve(double timestep, double[][][] oldState) {
        //multistep methods reaquires bootstrapping for compute the first value
        if(!bootStrapped){
            preOldState = oldState;
            bootStrapped = true;
            return bootStrapping(timestep, oldState);
        }
            
        int position = 0;
        int velocity1 = 1;
        int velocity2 = 0;
        int acceleration = 1;

        double[][][] firstDerivativeOldState = new double[12][2][3];
        double[][][] firstDerivativePreOldState = new double[12][2][3];
        double[][][] newState = new double[12][2][3];
    
        //Calculating f(ti, wi)
        double[][] tempPositions = new double[12][3];
        for (int body = 0; body < oldState.length; body++) {
            tempPositions[body] = oldState[body][position];
        }

        double[][] updatedForces = Functions.forceCalculator(tempPositions);
    
        for (int body = 0; body < oldState.length; body++) {
            // f(ti,wi)
            firstDerivativeOldState[body][velocity2] = oldState[body][velocity1];
            firstDerivativeOldState[body][acceleration] = VectorOperations.vectorScalarDivision(updatedForces[body], CelestialBody.bodyList[body].getMass());
        }
    
        // Calculating f(ti-1,wi-1)
        for (int body = 0; body < preOldState.length; body++) {
            tempPositions[body] = preOldState[body][position];
        }

        updatedForces = Functions.forceCalculator(tempPositions);
    
        for (int body = 0; body < preOldState.length; body++) {
            // f(ti-1,wi-1)
            firstDerivativePreOldState[body][velocity2] = preOldState[body][velocity1];
            firstDerivativePreOldState[body][acceleration] = VectorOperations.vectorScalarDivision(updatedForces[body], CelestialBody.bodyList[body].getMass());
        }
    
        for (int body = 0; body < oldState.length; body++) {
            // 3f(ti,wi) - f(ti-1,wi-1)
            newState[body] = MatrixOperations.matrixSubtraction(MatrixOperations.matrixScalarMultiplication(firstDerivativeOldState[body], 3.0),firstDerivativePreOldState[body]);
            // h/2 * [3f(ti,wi) - f(ti-1,wi-1)]
            newState[body] = MatrixOperations.matrixScalarMultiplication(newState[body], timestep / 2.0);
    
            // wi+1 = wi + h/2 * [3f(ti,wi) - f(ti-1,wi-1)]
            newState[body] = MatrixOperations.matrixAddition(oldState[body], newState[body]);
        }

        //updating preOldState
        preOldState = oldState;
        return newState;
    }



    // method for bootstrapping using RK4
    public double[][][] bootStrapping(double timestep, double[][][] oldState){
        iSolver solve = new RungeKutta4Solver();
        return solve(timestep,oldState);
    }

    
    
    
    
    
    
    
}
