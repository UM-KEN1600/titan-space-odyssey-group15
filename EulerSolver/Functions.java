package EulerSolver;

import SolarSystem.CelestialBody;

/**
 * This class contains all numerical methods that are used for computations.
 */
public class Functions {

    double G = 6.6743 * Math.pow(10, -20);

    CelestialBody listOfCelestialBodies[] = CelestialBody.list;

    int rowSpaceship = 8;
    
    /**
     * calculates the force of one celestial body on another celestial body
     * @param bodyA
     * @param bodyB
     * @return Force of bodies in three dimensions
     */
    public double[] forceCalculator(CelestialBody bodyA, CelestialBody bodyB){

        double[] vectorA = State.getPosition(bodyA.rowInState).clone();
        double[] vectorB = State.getPosition(bodyB.rowInState).clone();

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
    public double[] forceOnSpaceship(CelestialBody spaceship){
        
        double[] fullForce = new double[3];

        for(CelestialBody planet : listOfCelestialBodies)
        {
            fullForce = VectorOperations.vectorAddition(fullForce, forceCalculator(spaceship, planet));
        }

        return VectorOperations.vectorScalarMultiplication(fullForce, -1);
    }

    /**
     * calculates the forces of other bodies on a body A, ignoring the spaceship
     * @param bodyA
     * @return
     */
    public double[] forceOnPlanet(CelestialBody bodyA)
    {
        double[] fullForce = new double[3];

        for(int i = 0; i < 11; i++) //leaves out spaceship
        {
            if(i != bodyA.rowInState)
            {
                fullForce = VectorOperations.vectorAddition(fullForce, forceCalculator(listOfCelestialBodies[bodyA.rowInState], listOfCelestialBodies[i]));
            }
            
        }

        return VectorOperations.vectorScalarMultiplication(fullForce, -1);
    }

    /**
     * calculates the velocity of a planet with respect to time
     * @param time
     * @param spaceShip
     * @return
     */
    public double[] velocityOfBody(double time, CelestialBody body)
    {
        double[] velocity = new double[3];

        //velocity1 = velocity0 + (Force/mass) * time
        double mass = body.mass;
        velocity = VectorOperations.vectorScalarDivision(State.getForce(body.rowInState), body.mass);
        velocity = VectorOperations.vectorScalarMultiplication(velocity, time);
        velocity = VectorOperations.vectorAddition(velocity, State.getVelocity(body.rowInState));

        return velocity;
    }

    /** calculates the new position of a body
     * 
     * @param time
     * @param spaceShip
     * @return
     */
    public double[] newPositionOfBody(double time, CelestialBody body)
    {
        double[] position = new double[3];

        position = VectorOperations.vectorScalarMultiplication(State.getVelocity(body.rowInState), time);
        position = VectorOperations.vectorAddition(State.getPosition(body.rowInState), position);
        
        return position;
    }
}
