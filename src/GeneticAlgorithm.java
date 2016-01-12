import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;

/**
 * Created by Bia on 1/11/2016.
 */
public class GeneticAlgorithm {

    public static final int POPULATION_SIZE = 100;
    public static final int PRODUCT_FREQUENCY = 20;

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
    }


    public static void sortBasedOnFitness(ArrayList<ProductSet> arrayList)
    {
        //Collections.shuffle();
    }

    public static ArrayList<ProductSet> getElitistSubPopulation(int percent, ArrayList<ProductSet> population)
    {
        int n=percent/100*population.size();
        ArrayList<ProductSet> elitistSubPopulation = new ArrayList<>();
        sortBasedOnFitness(population);
        for(int i=0; i<n; i++)
            elitistSubPopulation.add(population.get(i));
        return elitistSubPopulation;


    }

    public static ArrayList<ProductSet> getCrossedOverSubPopulation(ArrayList<ProductSet> parents)
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

    public static void replaceSubPopulation(int from, int until, ArrayList<Product[]> replacement)
    {

    }




}
