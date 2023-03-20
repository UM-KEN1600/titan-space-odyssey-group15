package EulerSolver;

import SolarSystem.CelestialBody;

/**
 * This class contains all numerical methods that are used for computations.
 */
public class Functions {

    double G = 6.6743 * Math.pow(10, -20);
    
    /**
     * calculates the force of one celestial body on another celestial body
     * @param bodyA
     * @param bodyB
     * @return Force of bodies in three dimensions
     */
    public double[] forceCalculator(CelestialBody bodyA, CelestialBody bodyB){

        double[] vectorA = State.positions[bodyA.rowInState];
        double[] vectorB = State.positions[bodyB.rowInState];

        double a = G * bodyA.mass * bodyB.mass;

        double[] b = VectorOperations.vectorScalarDivision(VectorOperations.vectorSubtraction(vectorA, vectorB), Math.pow(VectorOperations.euclideanForm(vectorA, vectorB), 3));

        double[] force = VectorOperations.vectorScalarMultiplication(b, a);

        return force;
    }

    /**
     * calculates the sum of the forces the celestial bodies have on the space ship
     * @param celestialBodies
     * @param spaceship
     * @return sum of all forces on the spaceship
     */
    public double[] forceOnSpaceship(CelestialBody[] celestialBodies, CelestialBody spaceship){
        
        double[] fullForce = new double[3];

        for(CelestialBody planet : celestialBodies)
        {
            fullForce = VectorOperations.vectorAddition(fullForce, forceCalculator(planet, spaceship));
        }

        return VectorOperations.vectorScalarMultiplication(fullForce, -1);
    }
}
