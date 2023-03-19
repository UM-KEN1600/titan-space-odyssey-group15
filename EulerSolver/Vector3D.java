package EulerSolver;
/**
 * 3 dimensional vectors
 */
public class Vector3D {
    double x;
    double y;
    double z;

    public Vector3D()
    {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Vector3D(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static double[] vectorSubtraction(double[] vectorA, double[] vectorB)
    {
        if(vectorA.length != vectorB.length)
        {
            return null;
        }

        double[] A = new double[3];

        for(int i = 0; i < vectorA.length; i++)
        {
            A[i] = vectorA[i] - vectorB[i];
        }

        return A;
    }

    public static double euclideanForm(double[] vectorA, double[] vectorB)
    {
        double[] A = new double[3];

        for(int i = 0; i < vectorA.length; i++)
        {
            A[i] = vectorA[i] - vectorB[i];
        }

        double euclideanValue = 0;

        for(int i = 0; i < vectorA.length; i++)
        {
            euclideanValue += Math.pow(A[i], 2);
        }

        euclideanValue = Math.sqrt(euclideanValue);

        return euclideanValue;
    }

    public static double[] vectorDivision(double[] vectorA, double value)
    {
        double[] A = new double[3];

        for(int i = 0; i < vectorA.length; i++)
        {
            A[i] = A[i] / value;
        }

        return A;
    }
}
