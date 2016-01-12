import java.util.ArrayList;
import java.util.Collections;
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

    public static Random rng = new Random(System.currentTimeMillis());

    public static void main(String[] argv)
    {
        Product[] baseLine = Knapsack.createDefaultProductArray();

        //creation of set
        ArrayList<ProductSet> population = new ArrayList<>();
        for(int i = 0; i < POPULATION_SIZE; i++)
        {
            population.add(shuffleArray(baseLine));
        }
    }

    public static Product[] copyArray(Product[] array)
    {
        Product[] copyArray = new Product[array.length];
        for(int i=0; i<array.length; i++)
            copyArray[i]=array[i];
        return copyArray;
    }

    public static Product[] remove(Product[] array, int pos)
    {
        Product[] copyArray= new Product[array.length-1];
        System.arraycopy(array, 0, copyArray, 0, pos);
        System.arraycopy(array, pos+1, copyArray, pos, array.length-pos-1);
        return copyArray;
    }

    public static Product[] shuffleArray(Product[] arrayToShuffle)
    {
        Product[] shuffledArray = new Product[arrayToShuffle.length];
        int index = 0;
        Random rng = new Random(System.nanoTime());
        while(arrayToShuffle.length > 0)
        {
            int randomNumber = rng.nextInt(arrayToShuffle.length);
            shuffledArray[index] = arrayToShuffle[randomNumber];
            arrayToShuffle = remove(arrayToShuffle, randomNumber);
            index++;
        }
        return shuffledArray;
    }

    public static void switchElements(Product[] array ,int pos1, int pos2)
    {
        Product aux=array[pos2];
        array[pos2]=array[pos1];
        array[pos1]=aux;
    }

    public static void mutatePosition(Product[] array, int mutations)
    {
        for(int i = 0; i < mutations; i++)
        {
            switchElements(array, rng.nextInt(array.length), rng.nextInt(array.length));
        }
    }

    public static void mutateRotation(Product[] array, int mutations)
    {
        for(int i = 0; i < mutations; i++)
        {
            int index = rng.nextInt(array.length);
            Product[] alleles = array[index].getRotations();
            array[index] = alleles[rng.nextInt(alleles.length)];
        }
    }

    public static int getFitness(Product[] array)
    {
        Truck truck = new Truck(TRUCK_LENGTH, TRUCK_WIDTH, TRUCK_HEIGHT);
        int count = 0;
        while(truck.canFit(array[count]))
        {
            try
            {
                truck.add(array[count]);
            } catch (HollowSpace.NoRoomException e)
            {
                e.printStackTrace();
            }
        }
        return truck.getValue();
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
