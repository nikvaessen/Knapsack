/**
 * Created by baxie on 1/12/16.
 */
public class removedCode {
//
//
//    public static Product[] copyArray(Product[] array)
//    {
//        Product[] copyArray = new Product[array.length];
//        for(int i=0; i<array.length; i++)
//            copyArray[i]=array[i];
//        return copyArray;
//    }
//
//    public static Product[] remove(Product[] array, int pos)
//    {
//        Product[] copyArray= new Product[array.length-1];
//        System.arraycopy(array, 0, copyArray, 0, pos);
//        System.arraycopy(array, pos+1, copyArray, pos, array.length-pos-1);
//        return copyArray;
//    }
//
//    public static Product[] shuffleArray(Product[] arrayToShuffle)
//    {
//        Product[] shuffledArray = new Product[arrayToShuffle.length];
//        int index = 0;
//        Random rng = new Random(System.nanoTime());
//        while(arrayToShuffle.length > 0)
//        {
//            int randomNumber = rng.nextInt(arrayToShuffle.length);
//            shuffledArray[index] = arrayToShuffle[randomNumber];
//            arrayToShuffle = remove(arrayToShuffle, randomNumber);
//            index++;
//        }
//        return shuffledArray;
//    }
//
//    public static void switchElements(Product[] array ,int pos1, int pos2)
//    {
//        Product aux=array[pos2];
//        array[pos2]=array[pos1];
//        array[pos1]=aux;
//    }
//
//    public static void mutatePosition(Product[] array, int mutations)
//    {
//        for(int i = 0; i < mutations; i++)
//        {
//            switchElements(array, rng.nextInt(array.length), rng.nextInt(array.length));
//        }
//    }
//
//    public static void mutateRotation(Product[] array, int mutations)
//    {
//        for(int i = 0; i < mutations; i++)
//        {
//            int index = rng.nextInt(array.length);
//            Product[] alleles = array[index].getRotations();
//            array[index] = alleles[rng.nextInt(alleles.length)];
//        }
//    }
//
//    public static int getFitness(Product[] array)
//    {
//        Truck truck = new Truck(TRUCK_LENGTH, TRUCK_WIDTH, TRUCK_HEIGHT);
//        int count = 0;
//        while(truck.canFit(array[count]))
//        {
//            try
//            {
//                truck.add(array[count]);
//            } catch (HollowSpace.NoRoomException e)
//            {
//                e.printStackTrace();
//            }
//        }
//        return truck.getValue();
//    }

//    public static void backTrackFill(Truck truck, Product[] set)
//    {
//
//        System.out.println("####################BACKTRACK_FILL################################");
//        long beginTime = System.nanoTime();
//
//        //int value = backTrack(truck, getArrayCopy(set), 0);
//        //System.out.println("value of truck: " + value);
//        int count = 0;
//        while(truck.canFit(set[count]))
//        {
//            try{
//                truck.add(set[count]);
//                count++;
//            }
//            catch(HollowSpace.NoRoomException e)
//            {
//                e.printStackTrace();
//            }
//        }
//        long endTime = System.nanoTime();
//        System.out.println(truck);
//        truck.printTruckCoronally();
//        System.out.printf("Execution time: %d ms\n", ((double)endTime - beginTime) / 10e9);
//        System.out.println("##################################################################");
//
//    }

}
