import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * Created by Bia on 1/11/2016.
 */
public class GeneticAlgorithm {

    public static final int TRUCK_LENGTH = 165;
    public static final int TRUCK_WIDTH = 25;
    public static final int TRUCK_HEIGHT = 40;

    public static final int POPULATION_SIZE = 100;
    public static final int PRODUCT_FREQUENCY = 20;

    public static void main(String[] argv)
    {
        Product A = new Product(10, 10, 20, 5/*MINIMUM_PRIZE + rng.nextInt(MAXIMUM_PRIZE - MINIMUM_PRIZE)*/, "A");
        Product B = new Product(10, 15, 20, 6/*MINIMUM_PRIZE + rng.nextInt(MAXIMUM_PRIZE - MINIMUM_PRIZE)*/, "B");
        Product C = new Product(15, 15, 15, 7/*MINIMUM_PRIZE + rng.nextInt(MAXIMUM_PRIZE - MINIMUM_PRIZE)*/, "C");
        Product[] originals = {A, B, C};

        Product[] baseLine = Knapsack.createRandomProductArray(originals, new Random(System.nanoTime()));

        //creation of set
        ArrayList<Product[]> population = new ArrayList<>();



    }


    public Object[] getShuffledArray(Object[] arrayToShuffle)
    {
        Object[] array = PermutationFinder.copyArray(arrayToShuffle);
        Object[] copy  = new Objects[arrayToShuffle.length];
        while(array.length > 0)
        {

        }

        return copy;
    }


}
