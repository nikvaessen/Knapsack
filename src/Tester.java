import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by baxie on 10-1-16.
 */
public class Tester
{
    public static void main(String[] argv)
    {
        ArrayList<Integer> array = new ArrayList<Integer>(Arrays.asList(new Integer[] {1,2,3,4,5}));
        System.out.println(array);
        Collections.shuffle(array);
        System.out.println(array);
    }

}
