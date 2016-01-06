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
        int[][][] array = new int[3][3][3];
        for(int i = 0; i < array.length; i++)
        {
            for(int j = 0; j < array[0].length; j++)
            {
                for(int k = 0; k < array[0][0].length; k++)
                {
                    System.out.println(array[i][j][k]);
                }
            }
        }
        System.out.printf("Length: %d width: %d height: %d\n", array.length, array[0].length, array[9][0].length);
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
            int y = 0;
            int z = 0;
            int x = 0;
            fill(object, value,  x, z, y);
        }
        catch (NoRoomException e)
        {
            throw e;
        }
    }

    public void fill(Space object, int value, int x, int z, int y) throws NoRoomException
    {
        try
        {
            int objectLength = object.getLength();
            int objectWidth  = object.getWidth();
            int objectHeight = object.getHeight();
            if(x + objectLength >= space.length || z + objectWidth >= space[0].length ||
                    y + objectHeight >= space[0][0].length)
            {
                throw new NoRoomException();
            }
            for(int i = x; i < objectLength; i++)
            {
                for(int j = z; j < objectWidth; j++)
                {
                    for(int k = y; k < objectHeight; k++)
                    {
                        space[i][j][k] = value;
                    }
                }
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            throw new NoRoomException();
        }
    }

    public int getValueAtPosition(int x, int z, int y)
    {
        return space[x][z][y];
    }

    public void emptySpace()
    {
        space = new int[super.getLength()][super.getWidth()][super.getHeight()];
    }

    /**
     * retruns a copy of the 3 dimensional array of the HollowSpace
     * @return 3 dimensional array of integers
     */
    public int[][][] getSpace()
    {
        int[][][] copy = new int[super.getLength()][super.getWidth()][super.getHeight()];
        for(int i = 0; i < copy.length; i++)
        {
            for(int j = 0; j < copy[0].length; j++)
            {
                for(int k = 0; k < copy[0][0].length; k++)
                {
                    copy[i][j][k] = space[i][j][k];
                }
            }
        }
        return copy;
    }
}
