import java.text.Normalizer;
import java.util.Arrays;

/**
 * Created by baxie on 1/5/16.
 */
public class HollowSpace extends Space {

    public static void main(String[] argv)
    {
//        HollowSpace space = new HollowSpace(10,10,10);
//        Space object1 = new Space(3,3,3);
//        try{
//            space.fill(object1);
//        }
//        catch(NoRoomException e)
//        {
//            e.printStackTrace();
//        }
    }

    public int[][][] space;

    public HollowSpace(int length, int width, int height) throws IllegalArgumentException {
        super(length, width, height);
        space = new int[length][width][height];
    }

    public class NoRoomException extends Exception
    {
        public NoRoomException() {
        }

        public NoRoomException(String s) {
            super(s);
        }
    }

    /**
     * Fills the space at the next empty location. The next location is found by
     * @param object
     * @param value
     * @throws NoRoomException
     */
    public void fill(Space object, int value) throws NoRoomException
    {
        try
        {
            int height = 0;
            int width = 0;
            int length = 0;
            fill(object, value,  height, width, length);
        }
        catch (NoRoomException e)
        {
            throw e;
        }
    }

    public void fill(Space object, int value, int length, int width, int height) throws NoRoomException
    {
        try
        {
            int objectLength = object.getLength();
            int objectWidth  = object.getWidth();
            int objectHeight = object.getHeight();
            //for(int i = length; )
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            throw new NoRoomException();
        }
    }

    /**
     * retruns a copy of the 3 dimensional array of the HollowSpace
     * @return 3 dimensional array of integers
     */
    public int[][][] getSpace()
    {
        int[][][] copy=
        return space;
    }

    private
}
