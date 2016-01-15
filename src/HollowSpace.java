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
        for(int x = 0; x < super.getLength(); x++)
        {
            for(int z = 0; z < super.getWidth(); z++)
            {
                for(int y = 0; y < super.getHeight(); y++)
                {
                    space[x][z][y] = -1;
                }
            }
        }
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
            int[] coords = getViableCoordinates(object);
           // System.out.println("length of object " + object.getLength() + " width of object " +
            //        object.getWidth() + " height of object " + object.getHeight()  + " placing object at:"
           //  + Arrays.toString(coords));
            fill(object, value, coords[0], coords[1], coords[2]);
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
            if(x + objectLength > space.length || z + objectWidth > space[0].length ||
                    y + objectHeight > space[0][0].length)
            {
                throw new NoRoomException("Trying to place a object out of bounds of the truck");
            }
            for(int i = 0; i < objectLength; i++)
            {
                for(int j = 0; j < objectWidth; j++)
                {
                    for(int k = 0; k < objectHeight; k++)
                    {
                        space[i+x][j+z][k+y] = value;
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
        //check for out of bounds
        if(x + objectLength > space.length || z + objectWidth > space[0].length ||
                y + objectHeight > space[0][0].length)
        {
            return false;
        }
        //check for products already in place
        for(int i = 0 ; i < objectLength; i++)
        {
            for(int j = 0; j < objectWidth; j++)
            {
                for(int k = 0; k < objectHeight; k++)
                {
                    int value = space[i+x][j+z][k+y];
                    if(value != -1 )
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean canFit(Space object)
    {
        try{
            int[] coords = getViableCoordinates(object);
            return canFit(object, coords[0], coords[1], coords[2]);
        }
        catch(NoRoomException e)
        {
            return false;
        }
    }

    public int getValueAtPosition(int x, int z, int y)
    {
        return space[x][z][y];
    }

    public void emptySpace()
    {
        for(int x = 0; x < super.getLength(); x++)
        {
            for(int y = 0; y < super.getHeight(); y++)
            {
                for(int z = 0; z < super.getWidth(); z++)
                {
                    space[x][z][y] = -1;
                }
            }
        }
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

    public int[] getViableCoordinates(Space object) throws NoRoomException
    {
        try
        {
            for(int x = 0; x < super.getLength(); x++)
            {
                for(int y = 0; y < super.getHeight(); y++)
                {
                    for(int z = 0; z < super.getWidth(); z++)
                    {
                        int value = space[x][z][y];
                        if(value == -1 && canFit(object,x,z,y))
                        {
                            //System.out.printf("returned %s as viable coordinates\n", Arrays.toString(new int[] {x,z,y}));
                            return new int[] {x,z,y};
                        }
                    }
                }
            }
            //it was not able to find a viable spot so there is no room anymore
            throw new NoRoomException("Not able to find a viable coordinate in the truck");
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            throw new NoRoomException("Array out of bound exception trying to search for viable coordinates");
        }
    }

    public void fill2DArray(PentominoeProduct product, int xBegin, int zBegin, int yBegin, int value) throws NoRoomException
    {
        int[][] toFill = product.getArray();
        int length = 5*toFill.length;
        int width = 5*toFill[0].length;
        // get viable coordinates
        if(!product.isX() && product.isY() && product.isZ())
        {
            System.out.println("width=" + width + " length=" + length);
            for(int i = 0; i < 4; i++)
            {
                for(int j = 0; j < length; j++)
                {
                    for(int k = 0; k < width; k++)
                    {
                        System.out.println("j=" + j + " k=" + k + " j/5=" + j/5 + " k/5=" + k/5);
                        if(toFill[j/5][k/5] == 1)
                        space[i+xBegin][j+zBegin][k+yBegin] = value;
                    }
                }
            }
        }

        else if(product.isX() && !product.isY() && product.isZ())
        {
            for(int i = 0; i < 4; i++)
            {
                for(int j = 0; j < length; j++)
                {
                    for(int k = 0; k < width; k++)
                    {
                        if(toFill[j/5][k/5] == 1)
                        space[j+xBegin][k+zBegin][i+yBegin] = value;
                    }
                }
            }
        }

       else if(product.isX() && product.isY() && !product.isZ())
        {
            for(int i = 0; i < 4; i++)
            {
                for(int j = 0; j < length; j++)
                {
                    for(int k = 0; k < width; k++)
                    {
                        if(toFill[j/5][k/5] == 1)
                        space[j+xBegin][i+zBegin][k+yBegin] = value;
                    }
                }
            }
        }

    }
}
