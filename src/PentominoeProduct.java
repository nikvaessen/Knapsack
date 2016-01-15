import java.util.ArrayList;

/**
 * Created by Bia on 15-Jan-16.
 */
public class PentominoeProduct extends Product{

    private String name;
    private int[][] array;
    private int[][] r2, r3, r4;
    private boolean x, y, z;
    public static int[][] p = { {1, 1},
                                {1, 1},
                                {1, 0}
                              };
    public static int[][] l = { {1, 0},
                                {1, 0},
                                {1, 0},
                                {1, 1}
                              };

    public static int[][] t = { {1, 1, 1},
                                {0, 1, 0},
                                {0, 1, 0}
                              };

    public PentominoeProduct(String name, int value)
    {
        super(1,1,1, value, name );
        x = false;
        y = true;
        z = true;
        this.name = name;
        if (name.compareTo("p") == 0)
            array=p;
        else if(name.compareTo("l") == 0)
            array = l;
        else if(name.compareTo("t") == 0)
            array = t;
        else throw new IllegalArgumentException();
        r2 = rotateMatrix(array, true);
        r3 = rotateMatrix(r2, true);
        r4 = rotateMatrix(r3, true);
    }

    public int[][] getArray()
    {
        return array;
    }

    public ArrayList<int[][]> getAllRotations()
    {
        ArrayList<int[][]> rotations = new ArrayList<>();
        rotations.add(array);
        rotations.add(r2);
        rotations.add(r3);
        rotations.add(r4);
        return rotations;
    }

    public boolean isX() {
        return x;
    }


    public boolean isY() {
        return y;
    }


    public boolean isZ() {
        return z;
    }


    public void setXFalse()
    {
        x = false;
        y = true;
        z = true;
    }

    public void setYFalse()
    {
        y = false;
        x = true;
        z = true;
    }

    public void setZFalse()
    {
        z = false;
        y = true;
        x = true;
    }

    /**
     * It rotates a given matrix clockwise or anticlockwise
     * @param matrixToRotate the matrix that will be rotated
     * @param clockwise true for clockwise, false for anticlockwise
     * @return the rotated matrix
     */
    public static int[][] rotateMatrix(int[][] matrixToRotate, boolean clockwise){
		/*
		step 1: To rotate a matrix, first transpose the matrix so that the first column becomes the
				first row, the second column becomes the second row, ect.
		*/
        //create a empty matrix with transposed row and column length
        int[][] rotatedMatrix = new int[matrixToRotate[0].length][matrixToRotate.length];
        //and set the new values
        for(int row = 0; row < rotatedMatrix.length; row++)
        {
            for(int column = 0; column < rotatedMatrix[row].length; column++)
            {
                rotatedMatrix[row][column] = matrixToRotate[column][row];
            }
        }

		/*
		step 2: For a clockwise rotation(+90), 	every row has to be reversed.
				for an anti-clockwise rotation, every column has to be reversed.
		 */

        if(clockwise)
        {
            //Loop through every row and reverse them
            for (int row = 0; row < rotatedMatrix.length; row++) {
                //first store all the values in a row
                int rowLength = rotatedMatrix[row].length;
                int[] rowValues = new int[rowLength];
                for(int column = 0; column < rowValues.length; column++)
                {
                    rowValues[column] = rotatedMatrix[row][column];
                }
                //then reversely add them back to the same row
                for(int column = 0; column < rowValues.length; column++)
                {
                    rotatedMatrix[row][column] = rowValues[rowLength - 1 - column];
                }
            }
        }
        else if(!clockwise)
        {
            //Loop through every column and reverse them
            for (int column = 0; column < rotatedMatrix[0].length; column++) {
                //first store all the values in a column
                int columnLength = rotatedMatrix.length;
                int[] columnValues = new int[columnLength];
                for(int row = 0; row < columnLength; row++)
                {
                    columnValues[row] = rotatedMatrix[row][column];
                }
                //then reversely add them back to the same column
                for(int row = 0; row < columnLength; row++)
                {
                    rotatedMatrix[row][column] = columnValues[columnLength - 1 - row];
                }
            }
        }
        return rotatedMatrix;
    }
}
