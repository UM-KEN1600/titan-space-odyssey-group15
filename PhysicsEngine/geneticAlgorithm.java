package PhysicsEngine;

import java.util.Arrays;
import java.util.Random;

public class geneticAlgorithm {
    
    private int populationSize;
    private double mutationRate;
    private int numGenerations;
    public trajectory population;

    public static void main(String[] args) {
        geneticAlgorithm test = new geneticAlgorithm(20, 0.05, 100);
        System.out.println(test.evolution());
    }

    public geneticAlgorithm(int populationSize, double mutationRate, int numGenerations){
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        this.numGenerations = numGenerations;
            
    }

    public double runTest(double[] velocity){
        
        double[][] positions = SimulationHelp.planetarySetUp(velocity[0], velocity[1], velocity[2]);
        double[] probePosition = positions[0];
        double[] titanPosition = positions[1];
        return getFitness(probePosition, titanPosition);
    }

    public double getFitness(double[] probePosition, double[] titanPosition){
        return VectorOperations.euclideanForm(probePosition, titanPosition);
    }

    public trajectory[] createPopulation(double[] parentVelocity){
        trajectory[] population = new trajectory[populationSize];
        for (int i = 0; i < populationSize; i++){
            population[i] = new trajectory(parentVelocity, mutationRate);
        }
        return population;
    }
    
    public trajectory findBest(trajectory[] population){
        trajectory best = population[0];
        for (trajectory element : population){
            if (element.fitness < best.fitness){
                best = element;
            }
        }

        return best;
    }

    public double[] evolution(){
        double[] startingVelocity = {47, 34, 14};
        trajectory[] population = createPopulation(startingVelocity);
        while(findBest(population).fitness >= 2600){
            population = createPopulation(findBest(population).velocity);
            System.out.println(Arrays.toString(findBest(population).velocity));
            System.out.println(findBest(population).fitness);
        }
        
        return findBest(population).velocity;
    }


    class trajectory{

        public double[] velocity;
        double mutationRate;
        public double fitness;
        
        public trajectory(double[] parentVelocity, double mutationRate){
            this.mutationRate = mutationRate;
            this.velocity = mutator(parentVelocity);
            this.fitness = runTest(this.velocity);
            System.out.println(this.fitness);
        }

        public double[] mutator(double[] parentVelocity){
            double[] mutatedVelocity = new double[3];
            Random random = new Random();
            int axis = random.nextInt(3);
            int sign = random.nextInt(2);
            
            boolean mutated = false;
            
            double mutationValue = mutationRate;

            for(int i = 0; i < mutatedVelocity.length; i++){
                mutatedVelocity[i] = parentVelocity[i];
            }
            if (sign == 1){
                mutationValue *= -1;
            }
            
            while(!mutated){
                mutatedVelocity[axis] = parentVelocity[axis] + mutationValue;
                if(VectorOperations.magnitude(mutatedVelocity[0],mutatedVelocity[1],mutatedVelocity[2]) <= 60){
                    mutated = true;
                } else {
                    mutatedVelocity[axis] = parentVelocity[axis];
                    int change = random.nextInt(2);
                    if (change == 0){
                        if (axis == 2){
                            axis = 0;
                        } else{
                            axis++;
                        }
                    } 
                        else if (change == 1){
                        mutationValue *= -1;
                        }
                    }
            }
    
            return mutatedVelocity; 
        }
    }


}

