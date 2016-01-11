import javax.naming.event.ObjectChangeListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Bia on 1/11/2016.
 */
public class ArrayListFunctions {

    public static final Product[] copyArray(Product[] array)
    {
        Product[] copyArray = new Product[array.length];
        for(int i=0; i<array.length; i++)
            copyArray[i]=array[i];
        return copyArray;
    }

    public static final Product[] remove(Product[] array, int pos)
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

}
