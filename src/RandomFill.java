/**
 * Created by Stefan K on 18.01.2016.
 *
 *
 */

import java.util.Random ;

public class RandomFill {

    public static void main(String[] args)
    {
        //create rng
        Random rng = new Random(System.currentTimeMillis());

        //create a set of products
        //product A, B and C as described in the project booklet
        Product A = new Product(10, 10, 20, 3/*MINIMUM_PRIZE + rng.nextInt(MAXIMUM_PRIZE - MINIMUM_PRIZE)*/, "A");
        Product B = new Product(10, 15, 20, 4/*MINIMUM_PRIZE + rng.nextInt(MAXIMUM_PRIZE - MINIMUM_PRIZE)*/, "B");
        Product C = new Product(15, 15, 15, 5/*MINIMUM_PRIZE + rng.nextInt(MAXIMUM_PRIZE - MINIMUM_PRIZE)*/, "C");
        Product[] originals = {A, B, C};

        Truck truck = new Truck(165, 25, 40);

        randomFill(truck , originals) ;


    }

    public static void randomFill(Truck truck, Product[] set)
    {
        System.out.println("####################RANDOM_FILL################################");
        long beginTime = System.nanoTime();
        Random r = new Random();
        int chosenProduct = 0;
        int valueSum = 0;
        long endTime;
        while(true)
        {
            chosenProduct = r.nextInt(3);
            try
            {
                truck.add(set[chosenProduct].clone());
                valueSum += set[chosenProduct].getValue();
            }
            catch( HollowSpace.NoRoomException e)
            {
                endTime = System.nanoTime();
                System.out.println("###############################################################");
                truck.printTruckCoronally();
                System.out.println("Truck is full");
                System.out.println(truck.getContent());
                System.out.println("Total value of products stored: " + valueSum);
                System.out.println("Time used: " + ((double)endTime - beginTime) / 10e9 + "ms");

                break;
            }
            catch ( ArrayIndexOutOfBoundsException e)
            {
                System.out.println("Every product in the set has been added to the truck");
                break;
            }

        }
    }

}
