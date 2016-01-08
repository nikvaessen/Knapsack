/**
 * Created by baxie on 16-12-15.
 */
public class Product extends Space implements Comparable{


    //value of product
    private int value;
    private String name;
    private double valueDensity; //value density is the value of the product per cubic meter


    /**
     * constructor for a product with a certain 3d volume and a value
     * @param length the length of the product in decimeters
     * @param width the width of the product in decimeters
     * @param height the height of the product in decimeters
     * @param value the value of the product in euros
     */
    public Product(int length, int width, int height, int value, String name) throws IllegalArgumentException
    {
        super(length, width, height);
        //set value and name
        this.value = value;
        this.name = name;
        this.valueDensity = computeValueDensity(value, getVolume());
    }

    /**
     * calculates the value per cubic meter of this product
     * @param value the value in euros
     * @param volume the volume in cubic decimeters
     * @return the value per cubic meter of this product
     */
    private double computeValueDensity(int value, long volume)
    {
        long valueLong = (long) value * 1000;
        double valueDensity = (double)valueLong / (double)volume;
        return valueDensity;
    }

    /**
     * returns the value of the product
     * @return the value in euros
     */
    public int getValue() { return value;}

    /**
     * returns the name of the product
     * @return the name of the product
     */
    public String getName()
    {
        return name;
    }

    /**
     * returns the value density of the product. The value density is the value per cubic meter
     * @return the value of the product per cubic meter
     */
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
        return new Product(super.getLength(), super.getWidth(),
                super.getWidth(), value, name);
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
            return (product.getHeight() == super.getHeight())
                    && (product.getWidth() == super.getWidth())
                    && (product.getLength() == super.getLength())
                    && product.getName().equals(name)
                    && product.getValue() == value;
        }
    }

    @Override
    /**
     * Hashcode override so equals() method.
     */
    public int hashCode() {
        int result = getLength();
        result = 31 * result + getWidth();
        result = 31 * result + getHeight();
        result = 31 * result + getValue();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getValue();
        return result;
    }

    @Override
    /**
     * Returns a String format of the dimensions, volume and value of the product
     */
    public String toString()
    {
        return String.format("product description: %nName: %s length: %d width: %d height: %d value: $%d " +
                "cubic centimeters: %d value density: %f\n",
                name, super.getLength(), super.getWidth(), super.getHeight(), value, getVolume(), valueDensity);
    }

    @Override
    /**
     * Returns 0 if the value density of the compared product is equal, -1 if the value density is less
     * and 1 if the value density is higher.
     */
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
