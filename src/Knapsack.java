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
    
    public static void main(String[] argv) throws HollowSpace.NoRoomException{
        //create rng
        Random rng = new Random(System.currentTimeMillis());

        //create a set of products
        //product A, B and C as described in the project booklet
        Product A = new Product(10, 10, 20, 5/*MINIMUM_PRIZE + rng.nextInt(MAXIMUM_PRIZE - MINIMUM_PRIZE)*/, "A");
        Product B = new Product(10, 15, 20, 6/*MINIMUM_PRIZE + rng.nextInt(MAXIMUM_PRIZE - MINIMUM_PRIZE)*/, "B");
        Product C = new Product(15, 15, 15, 7/*MINIMUM_PRIZE + rng.nextInt(MAXIMUM_PRIZE - MINIMUM_PRIZE)*/, "C");
        Product[] originals = {A, B, C};

        //creation of set
        Product[] set = createRandomProductArray(originals, rng);
        System.out.println("Sorting finished");

        //create a truck as described in the project booklet and fill it with the made set

        long memFreeBefore = Runtime.getRuntime().freeMemory();
        Truck truck = new Truck(165, 25, 40);
        long memFreeAfter =  Runtime.getRuntime().freeMemory();
        System.out.printf("Truck needs: %d bytes\n", Math.abs(memFreeBefore - memFreeAfter));
        greedyFill(truck, set);
//        int loops = 50;
//        for(int i = 0; i < loops; i++)
//        {
//            truck.add(originals[0]);
//        }
//        truck.printTruckCoronally(30);
        truck.printTruckCoronally(165, 5);
        System.out.println(truck);
    }

    public static void greedyFill(Truck truck, Product[] set)
    {
        System.out.println("####################GREEDY_FILL################################");
        long beginTime = System.nanoTime();
        //sort the set from lowest to highest value/volume rating(value density)
        Arrays.sort(set);

        //begin filling the truck with the highest value density until the truck is full or there are no more products
        int index = set.length - 1;
        while(true)
        {
            System.out.printf("Putting Product with index %d in truck\n", index);
            try
            {
                truck.add(set[index].clone());
                index--;
            }
            catch( HollowSpace.NoRoomException e)
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
        long endTime = System.nanoTime();
        System.out.printf("Execution time: %.3f ms\n", ((double)endTime - beginTime) / 10e6);
        System.out.println("###############################################################");
    }

    public static Product[] createRandomProductArray(Product[] originals, Random rng)
    {
        Product[] initalSet = new Product[originals.length * MAXIMUM_FREQUENCY];
        int index = 0;
        int frequency = 10;
        for (Product product : originals) {
            frequency += 10;
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
