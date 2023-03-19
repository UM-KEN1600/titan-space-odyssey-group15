package SolarSystem;

//represents comets, asteroids, moons etc

public class CelestialBody {

    String name;
    String color;
    double mass;
    double radius;
    
    CelestialBody(String name, String color, double mass, double radius){

        this.name = name;
        this.color= color;
        this.mass = mass;
        this.radius = radius;
    }

    public String getName() {
        return name;
    }
    public String getColor() {
        return color;
    }
    public double getMass() {
        return mass;
    }public double getRadius() {
        return radius;
    }
}
