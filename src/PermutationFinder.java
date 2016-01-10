import java.io.CharArrayReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by baxie on 1/8/16.
 */
public class PermutationFinder {

    public static void main(String[] argv)
    {
        //Character[] array = {'a','b','c','d','e'};
        //Integer[] array = {1,2,3};
        Object[] array = new Object[60];
        for(int i = 0; i < array.length; i++)
        {
            array[i] = new Integer(i);
        }
        PermutationFinder finder = new PermutationFinder(array);
        while(finder.hasNextPermutation())
        {
            //System.out.println(Arrays.toString());
            finder.emptyBuffer();
        }
    }

    private Object[] array;
    private int mainLoopPosition;
    private ArrayList<Object[]> bufferedPermutations;

    public PermutationFinder(Object[] array)
    {
        this.array = array;
        bufferedPermutations = new ArrayList<>();
        mainLoopPosition = 0;
        buffer();
    }

    private void getAllPermutations(Object[] array, int beginIndex, int lastIndex)
    {
        if(lastIndex == beginIndex)
        {
            bufferedPermutations.add(copyArray(array));
            //System.out.println("Stored " + Arrays.toString(this.array));
        }
        else
        {
            for(int i = beginIndex; i <= lastIndex; i++)
            {
                //System.out.printf("Swapped: %s to ", Arrays.toString(this.array));
                swap(array, beginIndex, i);
                //System.out.println(Arrays.toString(this.array));
                getAllPermutations(array, beginIndex + 1, lastIndex);
                //System.out.printf("Swapped back after recursive call finished: %s to ", Arrays.toString(this.array));
                swap(array, i, beginIndex);
                //System.out.println(Arrays.toString(this.array));
            }
        }
    }

    private boolean buffer()
    {
        if(canBuffer())
        {
            System.out.printf("Buffering %dth time out of %d times\n", mainLoopPosition +1, this.array.length);
            long beginTime = System.nanoTime();
            swap(array, 0 ,mainLoopPosition);
            getAllPermutations(this.array, 1, this.array.length-1);
            long endTime = System.nanoTime();
            //System.out.println("Buffering finished. Size of buffer: " + bufferedPermutations.size());
            mainLoopPosition++;
            System.out.printf("Buffering took %.3f seconds. Amount of elements in buffer: %d\n"
                    ,((double)(endTime - beginTime)) / 10e9 ,bufferedPermutations.size());
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean canBuffer()
    {
        if(bufferedPermutations.size() == 0 && mainLoopPosition < this.array.length)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private static void swap(Object[] array, int pos1, int pos2)
    {
        Object temp = array[pos2];
        array[pos2] = array[pos1];
        array[pos1] = temp;
    }

    private Object[] copyArray(Object[] array)
    {
        Object[] copy = new Object[array.length];
        for(int i = 0; i < array.length; i++)
        {
            copy[i] = array[i];
        }
        return copy;
    }

    public boolean hasNextPermutation()
    {
        if(bufferedPermutations.size() == 0 && !canBuffer())
        {
            return false;
        }
        return true;
    }

    public Object[] getNextPermutation()
    {
        if(bufferedPermutations.size() == 0)
        {
            if( canBuffer() )
            {
                buffer();
            }
            else
            {
                return null;
            }
        }
        try
        {
            Object[] permutation = bufferedPermutations.get(0);
            bufferedPermutations.remove(0);
            return permutation;
        }
        catch(IndexOutOfBoundsException e)
        {
            return null;
        }
    }

    public void emptyBuffer()
    {
        bufferedPermutations = new ArrayList<>();
        buffer();
    }

}


