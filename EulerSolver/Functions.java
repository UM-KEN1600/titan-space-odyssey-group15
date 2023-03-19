package EulerSolver;

import SolarSystem.CelestialBody;

/**
 * This class contains all numerical methods that are used for computations.
 */
public class Functions {

    double G = Math.pow(6.6743, -20);
    
    /**
     * 
     * @param bodyA
     * @param bodyB
     * @return
     */
    public double[] forceCalculator(CelestialBody bodyA, CelestialBody bodyB){

        double[] vectorA = State.positions[bodyA.rowInState];
        double[] vectorB = State.positions[bodyB.rowInState];

        double a = G * bodyA.mass * bodyB.mass;

        double[] b = VectorOperations.vectorScalarDivision(VectorOperations.vectorSubtraction(vectorA, vectorB), VectorOperations.euclideanForm(vectorA, vectorB));

        double[] finalForce = VectorOperations.vectorScalarMultiplication(b, a);

        return finalForce;
    }

    public double[] forceOnSpaceship(CelestialBody[] celestialBodies, double[][] positions, CelestialBody spaceship){
        
        double[] fullForce = new double[3];

        for(CelestialBody planet : celestialBodies)
        {
            fullForce = VectorOperations.vectorAddition(fullForce, forceCalculator(planet, spaceship));
        }

        return VectorOperations.vectorScalarMultiplication(fullForce, -1);
    }
}
