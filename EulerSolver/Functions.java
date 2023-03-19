package EulerSolver;

import SolarSystem.CelestialBody;

// represents differential equation we want to solve

public class Functions {

    double G = 6.6743e-20;
    
    public double[] forceCalculator(CelestialBody bodyA, CelestialBody bodyB)
    {   
        double[] vectorA = State.positions[bodyA.rowInState];
        double[] vectorB = State.positions[bodyB.rowInState];

        double a = G * bodyA.mass * bodyB.mass;

        double[] b = Vector3D.vectorDivision(Vector3D.vectorSubtraction(vectorA, vectorB), Vector3D.euclideanForm(vectorA, vectorB)) ;

        double[] finalForce = Vector3D.vectorDivision(b, 1/a);

        return finalForce;
    }

    public double[] fullForce()
}
