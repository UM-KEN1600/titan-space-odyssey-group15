package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import PhysicsEngine.MatrixOperations;


public class MatrixOperationsTest {


    double[][] matrixA = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    double[][] matrixB = {{9, 8, 7}, {6, 5, 4}, {3, 2, 1}};
    double[][] matrixC = {{1, 2, 3}, {4, 5, 6}};
    double[][] expectedSum = {{10, 10, 10}, {10, 10, 10}, {10, 10, 10}};


    @Test public void testMatricesWrongSizeAddition() {
        assertNull(MatrixOperations.matrixAddition(matrixA, matrixC));
    }

    @Test public void testMatricesWrongSizeSwitchedAddition() {
        assertNull(MatrixOperations.matrixAddition(matrixC, matrixA));
    }

    @Test public void testMatricesCorrectSizeAddition(){
        assertArrayEquals(expectedSum, MatrixOperations.matrixAddition(matrixA, matrixB));
    }

    

}




