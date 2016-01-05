/**
 * Created by baxie on 1/5/16.
 */
public class Space {
    //dimension of the space in centimeters
    private int length;
    private int width;
    private int height;
    private int volume; //volume in cubic centimeters
    //i choose int because
    //                   165,000,000 is the volume of the truck in the booklet
    //int              2,147,483,647
    //long 9,223,372,036,854,775,807

    /**
     * Construct a space with a length, width and height of 1 centimeter
     */
    public Space()
    {
        length = 1;
        width = 1;
        height = 1;
        volume = 1;
    }

    /**
     * Construct a spce with a given length, width and height in centimeters
     * @param length the length in centimeters
     * @param width the width in centimeters
     * @param height the height in centimeters
     * @throws IllegalArgumentException when the specified length, width or height are smaller than or equal to 0.
     */
    public Space(int length, int width, int height) throws IllegalArgumentException {
        if(length <= 0 || width <= 0 || height <= 0)
        {
            throw new IllegalArgumentException("Cannot have null or negative dimensions in space");
        }
        this.length = length;
        this.width = width;
        this.height = height;
        this.volume = height * width * length;
    }

    /**
     * returns the 3d volume of the product in cubic centimeters
     * @return the cubic centimeter volume
     */
    public int getVolume() { return volume;}

    /**
     * returns the length of the product
     * @return the length of the product in centimeters
     */
    public int getLength() {
        return length;
    }

    /**
     * returns the width of the product
     * @return the width of the product in centimeters
     */
    public int getWidth() {
        return width;
    }

    /**
     * returns the height of the product
     * @return the height of the product in centimeters
     */
    public int getHeight() {
        return height;
    }

}
