import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by baxie on 15-12-15.
 */
public class Knapsack {

    public static final int MAXIMUM_PRIZE = 20;
    public static final int MINIMUM_PRIZE = 5;
    public static final int MINIMUM_FREQUENCY = 20;
    public static final int MAXIMUM_FREQUENCY = 40;
    
    public static void main(String[] argv) {
        //create rng
        Random rng = new Random(System.currentTimeMillis());

        //create a set of products
        //product A, B and C as described in the project booklet
        Product A = new Product(1000, 1000, 2000, 5/*MINIMUM_PRIZE + rng.nextInt(MAXIMUM_PRIZE - MINIMUM_PRIZE)*/, "A");
        Product B = new Product(1000, 1500, 2000, 6/*MINIMUM_PRIZE + rng.nextInt(MAXIMUM_PRIZE - MINIMUM_PRIZE)*/, "B");
        Product C = new Product(1500, 1500, 1500, 7/*MINIMUM_PRIZE + rng.nextInt(MAXIMUM_PRIZE - MINIMUM_PRIZE)*/, "C");
        Product[] originals = {A, B, C};

        //creation of set
        Product[] set = createRandomProductArray(originals, rng);

        //create a truck as described in the project booklet and fill it with the made set
        Truck truck = new Truck(16500, 2500, 4000);
        greedyFill(truck, set);
        System.out.println(truck);

    }

    public static void greedyFill(Truck truck, Product[] set)
    {
        System.out.println("####################################################");
        //sort the set from lowest to heighest value/volume rating(value density)
        Arrays.sort(set);

        //begin filling the truck with the highets vlaue density until the truck is full or there are no more products
        int index = set.length - 1;
        while(true)
        {
            try
            {
                truck.add(set[index].clone());
                index--;
            }
            catch( Truck.TruckFillFailException e)
            {
                System.out.print("Truck is full\n");
                break;
            }
            catch ( ArrayIndexOutOfBoundsException e)
            {
                System.out.println("every product in the set has been added to the truck");
                break;
            }
        }
    }

    public static Product[] createRandomProductArray(Product[] originals, Random rng)
    {
        Product[] initalSet = new Product[originals.length * MAXIMUM_FREQUENCY];
        int index = 0;
        for (Product product : originals) {
            int frequency = MINIMUM_FREQUENCY + rng.nextInt(MAXIMUM_FREQUENCY - MINIMUM_FREQUENCY);
            System.out.printf("Filling the set with %d pieces of product %s\n", frequency, product.getName());
            for (int i = 0; i < frequency; i++) {
                initalSet[index] = product.clone();
                index++;
            }
        }
        //clean up null variables
        Product[] set = new Product[index];
        System.arraycopy(initalSet, 0, set, 0, index);

        //print for debugging
        for(int i = 0; i < set.length; i++)
        {
            //System.out.println(set[i]);
        }

        return set;
    }

}
