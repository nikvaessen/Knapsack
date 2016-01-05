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

    public boolean fill(Space object) throws NoRoomException
    {
        return true;
    }

    public boolean fill(Space object, int length, int width, int height) throws NoRoomException
    {
        return true;
    }

    public int[][][] getSpace()
    {
        return space;
    }

    private
}
