/**
 * Created by baxie on 16-12-15.
 */
public class Product implements Comparable{

    //dimension of the product in millimeters
    private int length;
    private int width;
    private int height;

    //value of product
    private int value;
    private String name;
    private double valueDensity; //value density is the value of the product per cubic meter

    /**
     * constructor for a product with a certain 3d volume and a value
     * @param length the length of the product in millimeters
     * @param width the width of the product in millimeters
     * @param height the height of the product in millimeters
     * @param value the value of the product in euros
     */
    public Product(int length, int width, int height, int value, String name) throws IllegalArgumentException
    {
        if(length <= 0 || width <= 0 || height <= 0 || value <= 0)
        {
            throw new IllegalArgumentException();
        }
        //set dimension
        this.length = length;
        this.height = height;
        this.width  = width;
        //set value and name
        this.value = value;
        this.name = name;
        this.valueDensity = computeValueDensity(value, getVolume());

    }

    /**
     * calculates the value / cubic meter of this product
     * @param value the value in euros
     * @param volume the volume in cubic millimeters
     * @return the value per cubic meter of this product
     */
    private double computeValueDensity(int value, long volume)
    {
        long valueLong = (long) value * 1000000000;
        double valueDensity = (double)valueLong / (double)volume;
        return valueDensity;
    }

    /**
     * returns the value of the product
     * @return the value in euros
     */
    public int getValue() { return value;}

    /**
     * returns the 3d volume of the product in millimeters to the power of 3
     * @return the millimters ^ 3 volume  as a long
     */
    public long getVolume() { return (long)length*height*width;}

    /**
     * returns the length of the product
     * @return the length of the product in millimeters
     */
    public int getLength() {
        return length;
    }

    /**
     * returns the width of the product
     * @return the width of the product in millimeters
     */
    public int getWidth() {
        return width;
    }

    /**
     * returns the height of the product
     * @return the height of the product in millimeters
     */
    public int getHeight() {
        return height;
    }

    /**
     * returns the name of the product
     * @return the name oif the product
     */
    public String getName()
    {
        return name;
    }

    public double getValueDensity()
    {
        return valueDensity;
    }

    @Override
    /**
     * returns a product with the same value and dimensions
     */
    public Product clone()
    {
        return new Product(length, width, height, value, name);
    }

    @Override
    /**
     * returns true if specified object has equal dimensions and value as the object it is called on.
     * returns false if value or dimensions are not the same
     */
    public boolean equals(Object object)
    {
        if ((object == null) || !(object instanceof Product))
        {
            return false;
        }
        else if (object == this)
        {
            return true;
        }
        else
        {
            Product product = (Product) object;
            return (product.getHeight() == height) && (product.getWidth() == width)
                    && (product.getLength() == length) && product.getName().equals(name);
        }
    }

    @Override
    public int hashCode() {
        int result = getLength();
        result = 31 * result + getWidth();
        result = 31 * result + getHeight();
        result = 31 * result + getValue();
        result = 31 * result + getName().hashCode();
        return result;
    }

    @Override
    public String toString()
    {
        return String.format("product description: %nName: %s length: %d width: %d height: %d value: $%d " +
                "cubic millimeters: %d value density: %f\n",
                name, length, width, height, value, getVolume(), valueDensity);
    }

    @Override
    public int compareTo(Object object)
    {
        if ((object == null) )
        {
            throw new NullPointerException();
        }
        else if(!(object instanceof Product))
        {
            throw new ClassCastException();
        }
        else if (object == this)
        {
            return 0;
        }
        else
        {
            Product product = (Product) object;
            double productValueDensity = product.getValueDensity();
            if(Math.abs(valueDensity - productValueDensity) < 0.001)
            {
                return 0;
            }
            else if (valueDensity > productValueDensity)
            {
                return 1;
            }
            else
            {
                return -1;
            }
        }
    }
}
