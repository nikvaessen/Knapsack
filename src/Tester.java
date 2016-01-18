import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Created by baxie on 10-1-16.
 */
public class Tester
{
    public static void main(String[] argv)
    {
//        ArrayList<Integer> array = new ArrayList<Integer>(Arrays.asList(new Integer[] {1,2,3,4,5}));
//        System.out.println(array);
//        int removed = array.remove(3);
//        System.out.println(array);
//        array.add(3, removed);
//        System.out.println(array);

          Truck truck = new Truck(165, 25, 40);
//        Random rng = new Random();
//        ProductSet test = new ProductSet(truck, rng );
//        Product A = new Product(10, 10, 20, 5/*MINIMUM_PRIZE + rng.nextInt(MAXIMUM_PRIZE - MINIMUM_PRIZE)*/, "A");
//        Product B = new Product(10, 15, 20, 6/*MINIMUM_PRIZE + rng.nextInt(MAXIMUM_PRIZE - MINIMUM_PRIZE)*/, "B");
//        Product C = new Product(15, 15, 15, 7/*MINIMUM_PRIZE + rng.nextInt(MAXIMUM_PRIZE - MINIMUM_PRIZE)*/, "C");
//        test.add(A);
//        test.add(B);
//        test.add(C);
//        System.out.println("The fitness of the truck:" + test.getFitness());
//        System.out.println("The list:" + test.getList().size());
//        test.getTruck().printTruckCoronally();
        PentominoeProduct p = new PentominoeProduct("t",3);
        try {
            truck.addPentominoe(p);
        } catch (HollowSpace.NoRoomException e) {
            e.printStackTrace();
        }
        PentominoeProduct t = new PentominoeProduct("p",3);
        try {
            truck.addPentominoe(t);
        } catch (HollowSpace.NoRoomException e) {
            e.printStackTrace();
        }


        truck.printTruckCoronally();


    }

}
