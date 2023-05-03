package PhysicsEngine;

import SolarSystem.CelestialBody;

public class RungeKutta4Solver implements iSolver 
{
  EulerSolver euler = new EulerSolver();

  @Override
  public double[][] solve(CelestialBody body, double timestep, double[][] oldState) 
  {
    //aids to understand what is being calculated
    int position = 0;
    int velocity1 = 1;
    int velocity2 = 0;
    int acceleration = 1;

    double[][] newState = new double[2][3];
    newState[position] = oldState[position];
    newState[velocity1] = oldState[velocity1];

    
    double[][] k1 = new double[2][3]; // stores velocities and accelerations
    double[][] k2 = new double[2][3];
    double[][] k3 = new double[2][3];
    double[][] k4 = new double[2][3];

    //k1 = f(ti, wi) or the derivative at that position (velocity and acceleration since we do not have a function)
    k1[velocity2] = oldState[velocity1];
    k1[acceleration] = VectorOperations.vectorScalarDivision(State.getForce(body.rowInState), body.getMass()); //CHANGE HERE

    //k1 * h
    k1 = MatrixOperations.matrixScalarMultiplication(k1, timestep);

    //k2 = f( (ti + 1/2*hi), (pi + 1/2*k1) )
    k2 = euler.solve(body, (0.5*timestep), MatrixOperations.matrixScalarMultiplication(k1, 0.5));
    //k2 * h
    k2 = MatrixOperations.matrixScalarMultiplication(k2, timestep);

    //k3 = f( (ti + 1/2*hi), (pi + 1/2*k2) )
    k3 = euler.solve(body, (0.5*timestep), MatrixOperations.matrixScalarMultiplication(k2, 0.5));
    //k3 * h
    k3 = MatrixOperations.matrixScalarMultiplication(k3, timestep);

    //k4 = f(t+ 1/2*h, pi + k3)
    k4 = euler.solve(body, timestep, k3);
    //k4 * h
    k4 = MatrixOperations.matrixScalarMultiplication(k4, timestep);

    // //k2 *2 and k3 *2
    k1 = MatrixOperations.matrixScalarMultiplication(k2, 1/6.0);
    k2 = MatrixOperations.matrixScalarMultiplication(k2, 2/6.0);
    k3 = MatrixOperations.matrixScalarMultiplication(k3, 2/6.0);
    k4 = MatrixOperations.matrixScalarMultiplication(k4, 1/6.0);

    //w (i+1) = w(i) + 1/6 * (k1 + k2 + k3 + k4) (k2, k3 times 2 happens above)

    double[][] a = MatrixOperations.matrixAddition(k1, k2);
    double[][] b = MatrixOperations.matrixAddition(k3, k4);
    double[][] c = MatrixOperations.matrixAddition(a, b);
    //  c = MatrixOperations.matrixScalarMultiplication(c, 1/6.0);

    newState = MatrixOperations.matrixAddition(oldState, c);

    return newState;
  }

  @Override
  public double[][][] solve(double timestep, double[][][] oldState) 
  {
    //aids to understand what is being calculated
    int position = 0;
    int velocity1 = 1;
    int velocity2 = 0;
    int acceleration = 1;

    double[][][] newState = new double[12][2][3];
    
    double[][][] k1 = new double[12][2][3]; // stores velocities and accelerations
    double[][][] k2 = new double[12][2][3];
    double[][][] k3 = new double[12][2][3];
    double[][][] k4 = new double[12][2][3];

    //used to store temporary positions for acceleration calculation
    double[][] tempPositions = new double[12][2];

    //k1 = f(ti, wi) or the derivative at that position (velocity and acceleration since we do not have a function)
    for(int body = 0; body < oldState.length; body++)
    {
        k1[body][velocity2] = oldState[body][velocity1];
        k1[body][acceleration] = VectorOperations.vectorScalarDivision(State.getForce(body), CelestialBody.bodyList[body].getMass());
    }

    //k1 = k1 * h     &&    storing the new positions at w(i) + 1/2*k1
    for(int body = 0; body < oldState.length; body++)
    {
        k1[body] = MatrixOperations.matrixScalarMultiplication(k1[body], timestep);
        tempPositions[body] = VectorOperations.vectorAddition(oldState[body][position], VectorOperations.vectorScalarMultiplication(k1[body][velocity2], timestep));
    }

    //updating the forces for position w(i) + 1/2*k1
    double[][] updatedForces = Functions.forceCalculator(tempPositions);
    for(int body = 0; body < oldState.length; body++)
    {
      //euler step
        k2[body][velocity2] = VectorOperations.vectorAddition(oldState[body][velocity1], VectorOperations.vectorScalarMultiplication(k1[body][velocity2], 1/2.0));
        k2[body][acceleration] = VectorOperations.vectorScalarDivision(updatedForces[body], CelestialBody.bodyList[body].getMass());

        //k2 * h
        k2[body] = MatrixOperations.matrixScalarMultiplication(k2[body], timestep);

        //storing the new positions of k2
        tempPositions[body] = VectorOperations.vectorAddition(oldState[body][position], k2[body][velocity2]);
    }

    //updating the forces for position w(i) + 1/2*k2
    updatedForces = Functions.forceCalculator(tempPositions);
    for(int body = 0; body < oldState.length; body++)
    {
      //euler step
        k3[body][velocity2] = VectorOperations.vectorAddition(oldState[body][velocity1], VectorOperations.vectorScalarMultiplication(k2[body][velocity2], 1/2.0));
        k3[body][acceleration] = VectorOperations.vectorScalarDivision(updatedForces[body], CelestialBody.bodyList[body].getMass());

        //k3 * h
        k3[body] = MatrixOperations.matrixScalarMultiplication(k3[body], timestep);

        //storing the new positions of k3
        tempPositions[body] = VectorOperations.vectorAddition(oldState[body][position], k3[body][velocity2]);
    }

    //updating the forces for position w(i) + k3
    updatedForces = Functions.forceCalculator(tempPositions);
    for(int body = 0; body < oldState.length; body++)
    {
      //euler step
        k4[body][velocity2] = VectorOperations.vectorAddition(oldState[body][velocity1], k3[body][velocity2]);
        k4[body][acceleration] = VectorOperations.vectorScalarDivision(updatedForces[body], CelestialBody.bodyList[body].getMass());

      //k3 * h
      k4[body] = MatrixOperations.matrixScalarMultiplication(k4[body], timestep);
    }

    for(int body = 0; body < oldState.length; body++)
    {
        // //k2 *2 and k3 *2
        k1[body] = MatrixOperations.matrixScalarMultiplication(k1[body], 1/6.0);
        k2[body] = MatrixOperations.matrixScalarMultiplication(k2[body], 2/6.0);
        k3[body] = MatrixOperations.matrixScalarMultiplication(k3[body], 2/6.0);
        k4[body] = MatrixOperations.matrixScalarMultiplication(k4[body], 1/6.0);
    }


    //w (i+1) = w(i) + (k1 + k2 + k3 + k4) (k2, k3 times 2, all * 1/6 happens above)
    for(int body = 0; body < oldState.length; body++)
    {
        newState[body] = MatrixOperations.matrixAddition(oldState[body],MatrixOperations.matrixAddition(MatrixOperations.matrixAddition(k1[body], k2[body]), MatrixOperations.matrixAddition(k3[body], k4[body])));
    }

    return newState;
  }
    
}
