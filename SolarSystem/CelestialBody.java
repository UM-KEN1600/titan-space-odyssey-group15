package SolarSystem;

//represents comets, asteroids, moons etc

public class CelestialBody {

    String name;
    String color;
    double mass;
    double radius;
    double[] initialPos = new double[3];
    double[] initialVel = new double[3];

    CelestialBody(String name, String color, double[] initialPos, double initialVel[], double mass, double radius){
        this.name = name;
        this.color = color;
        this.mass = mass;

        for (int i = 0; i < 3; i++) {
            this.initialPos[i] = initialPos[i];
            this.initialVel[i] = initialVel[i];      
        }       
    }

    
}
