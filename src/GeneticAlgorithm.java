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


    public static void sortBasedOnFitness(ArrayList<Product[]> arrayList)
    {
        //Collections.shuffle();
    }

    public static ArrayList<ProductSet> getElitestSubPopulation()
    {
        return null;
    }

    public static ArrayList<ProductSet> getCrossedOverSubPopulation()
    {
        return null;
    }

    public static void replaceSubPopulation(int from, int until, ArrayList<ProductSet> replacement)
    {

    }




}
