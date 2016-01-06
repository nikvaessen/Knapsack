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
    int nextX,nextY, nextZ;

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
            System.out.println("length of object " + object.getLength() + "width of object " +
                    object.getWidth() + "height of object " + object.getHeight()  );
            System.out.println("x before:" + nextX);
            System.out.println("y before:" + nextY);
            System.out.println("z before:" + nextZ);
            updateCoordinates(object);
            System.out.println("x after:" + nextX);
            System.out.println("y after:" + nextY);
            System.out.println("z after:" + nextZ);
            fill(object, value, nextX + 1,nextZ + 1, nextY + 1);

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
    
    public boolean canFit(Space object, int x, int z, int y)
    {
        int objectLength = object.getLength();
        int objectWidth  = object.getWidth();
        int objectHeight = object.getHeight();
        if(x + objectLength >= space.length || z + objectWidth >= space[0].length ||
                y + objectHeight >= space[0][0].length)
        {
            return false;
        }
        return true;
    }
    
    //// TODO: 1/6/16 finish method 
    public boolean canFit(Space object)
    {
        //need to sheck if the fill method will be able to place the new product
        return false;
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

    public void updateCoordinates(Space object) throws NoRoomException
    {
        try
        {
            int objectLength = object.getLength();
            int objectWidth  = object.getWidth();
            int objectHeight = object.getHeight();

            if(nextZ + objectWidth >= super.getWidth())
            {
                nextY=nextY+objectHeight;
                nextZ=0;
            }
            else if(nextY+objectHeight>super.getHeight())
            {
                nextX=nextX+objectLength;
                nextY=0;
                nextZ=0;
            }
            else
            {
                nextZ=nextZ+objectWidth;
            }

            if(nextX  >= space.length || nextZ >= space[0].length || nextY>= space[0][0].length)
            {
                throw new NoRoomException();
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            throw new NoRoomException();
        }
    }
}
