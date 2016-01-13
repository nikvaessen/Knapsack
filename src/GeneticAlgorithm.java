
import java.util.*;

/**
 * Created by Bia on 1/11/2016.
 */
public class GeneticAlgorithm {

    public static final int POPULATION_SIZE = 100;
    public static final int GENERATIONS = 1000;
    public static final int MUTATION_CHANCE = 15;

    public static Random rng = new Random(System.currentTimeMillis());

    public static void main(String[] argv)
    {
        ProductSet baseLine = Knapsack.getDefaultProductSet();

        //creation of set
        ArrayList<ProductSet> population = new ArrayList<>();
        for(int i = 0; i < POPULATION_SIZE; i++)
        {
            ProductSet ps = baseLine.clone();
            ps.shuffle();
            population.add(ps);
        }

        evolvePopulationMatrix(GENERATIONS, population);
    }

    public static void evolvePopulationMatrix(int generations, List<ProductSet> populationMatrix)
    {
        long beginTime = System.currentTimeMillis();
        for(int i = 0; i < generations; i++)
        {
            long beginTimeGeneration = System.currentTimeMillis();
            //first sort them on highest value
            sortBasedOnFitness(populationMatrix);

            //elitist selection
            ArrayList<ProductSet> parents = getElitistSubPopulation(40, populationMatrix);
            ArrayList<ProductSet> children = getCrossedOverSubPopulation(parents);
            ArrayList<ProductSet> newInd = getNewIndividuals(populationMatrix.size() - parents.size() - children.size(),
                    populationMatrix.get(0));

            for(int j=0; j<children.size(); j++)
            {
                if(rng.nextInt(100)<MUTATION_CHANCE)
                {
                    children.get(j).mutatePosition(10);
                    children.get(j).mutateRotation(10);
                }
            }
            ArrayList<ProductSet> newGeneration = new ArrayList<>(populationMatrix.size());
            newGeneration.addAll(parents);
            newGeneration.addAll(children);
            newGeneration.addAll(newInd);
            populationMatrix = newGeneration;
            long endTimeGeneration = System.currentTimeMillis();
            System.out.printf("%dth generation took %d ms to compute.\n", i+1, endTimeGeneration - beginTimeGeneration);
        }
        sortBasedOnFitness(populationMatrix);
        long endTime   = System.currentTimeMillis();
        System.out.printf("####################_RESULTS_####################\n");
        System.out.printf("Execution time: %d ms\n", endTime - beginTime );
        System.out.printf("Truck with highest value: %d\n", populationMatrix.get(0).getFitness());
        System.out.printf("Content of truck: \n");
        populationMatrix.get(0).getFilledTruck().printTruckCoronally();
    }

    public static void sortBasedOnFitness(List<ProductSet> arrayList)
    {
        Collections.sort(arrayList, Collections.reverseOrder());
    }

    public static ArrayList<ProductSet> getElitistSubPopulation(int percent, List<ProductSet> population)
    {
        int n=percent/100*population.size();
        ArrayList<ProductSet> elitistSubPopulation = new ArrayList<>();
        sortBasedOnFitness(population);
        for(int i=0; i<n; i++)
            elitistSubPopulation.add(population.get(i));
        return elitistSubPopulation;
    }

    public static ArrayList<ProductSet> getCrossedOverSubPopulation(List<ProductSet> parents)
    {
        ArrayList<ProductSet> crossedOverPopulation = new ArrayList<>(parents.size()/2);
        int n = parents.size();
        int cnt = 0;
        for(int i=0; i<n; i+=2)
        {
            ProductSet child = ProductSet.createChild(parents.get(i), parents.get(i + 1));
            crossedOverPopulation.add(cnt, child);
            cnt++;
        }
        return crossedOverPopulation;
    }

    public static void replaceSubPopulation(int from, int until,
                                            List<ProductSet> toBeReplaced, List<ProductSet> replacements)
    {
        int k=0;
        for(int i=from; i<until; i++)
        {
            toBeReplaced.add(i, replacements.get(k));
            k++;
        }
    }

    public static ArrayList<ProductSet> getNewIndividuals(int amount, ProductSet baseLine)
    {
        ArrayList<ProductSet> set = new ArrayList<>();
        for(int i = 0; i < amount; i++)
        {
            ProductSet ps = baseLine.clone();
            ps.shuffle();
            List psContent = ps.getList();
            for(int j = 0; j < psContent.size(); j++)
            {
                ps.mutateRotation(ps.size());
            }
            set.add(ps);
        }
        return set;
    }

}
