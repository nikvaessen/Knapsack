import java.io.CharArrayReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by baxie on 1/8/16.
 */
public class PermutationFinder {

    public static void main(String[] argv)
    {
        Character[] acronym = {'a', 'b', 'c'};
        getAllPermutations(acronym, 0, 2);
    }

    private char[] array;
    private int loopPosition;
    private ArrayList<Character[]> bufferedPermutations;

    public PermutationFinder(char[] array)
    {
        this.array = array;
    }

    public static void getAllPermutations(Character[] array, int beginIndex, int lastIndex)
    {
        if(lastIndex == beginIndex)
        {
            System.out.println(Arrays.toString(array));
        }
        else
        {
            for(int i = beginIndex; i <= lastIndex; i++)
            {
                swap(array, beginIndex, i);
                getAllPermutations(array, beginIndex + 1, lastIndex);
                System.out.println("Loop done");
                swap(array, i, beginIndex);
            }
        }
    }

    public static void swap(Character[] array, int pos1, int pos2)
    {
        char temp = array[pos2];
        array[pos2] = array[pos1];
        array[pos1] = temp;
    }

//    public Object[] next()
//    {
//        return getNextPermutation();
//    }

    private Character[] getNextBufferedPermutation()
    {
        Character[] toReturn = bufferedPermutations.get(0);
        bufferedPermutations.remove(0);
        return toReturn;
    }
//
//    private Object[] getNextPermutation(Character[] array, int beginIndex, int lastIndex)
//    {
//            if(lastIndex == beginIndex)
//            {
//                System.out.println(Arrays.toString(array));
//                bufferedPermutations.add(copyArray(array));
//            }
//            else
//            {
//                for(int i = beginIndex; i <= lastIndex; i++)
//                {
//                    swap(array, beginIndex, i);
//                    getAllPermutations(array, beginIndex + 1, lastIndex);
//                    swap(array, i, beginIndex);
//                }
//            }
//    }

    private Character[] copyArray(Character[] array)
    {
        Character[] copy = new Character[array.length];
        for(int i = 0; i < array.length; i++)
        {
            copy[i] = array[i];
        }
        return copy;
    }

}


