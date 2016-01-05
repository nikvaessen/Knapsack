import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by baxie on 16-12-15.
 */
public class Truck extends Space{

    //content of the truck
    private ArrayList<Product> content;
    private long filledVolume;

    /**
     * Construct a Truck object with a 3d volume
     * @param length the length of the truck in millimeters
     * @param width the width of the truck in millimeters
     * @param height the height of the truck in millimeters
     */
    public Truck(int length, int width, int height) {
        super(length, width, height);
        //setting content
        this.content = new ArrayList<>();
    }

    public class TruckFillFailException extends Exception
    {
        public TruckFillFailException() { }

        public TruckFillFailException(String message)
        {
            super(message);
        }
    }

    public void add(Product product) throws TruckFillFailException
    {
        long newVolume = product.getVolume() + this.filledVolume;
        if( newVolume > super.getVolume())
        {
            throw new TruckFillFailException("adding the product will exceed the volume of the truck");
        }
        else
        {
            filledVolume = newVolume;
            content.add(product);
        }
    }

    public int getValue()
    {
        int value = 0;
        for(Product product : content)
        {
            value += product.getValue();
        }
        return value;
    }

    public HashMap<Product, Integer> getContent()
    {
        HashMap<Product, Integer> content = new HashMap<>();
        for(Product product : this.content)
        {
            if(content.containsKey(product))
            {
                content.put(product, content.get(product) + 1);
            }
            else
            {
                content.put(product, 1);
            }
        }
        return content;
    }
    
    @Override
    public String toString()
    {
        String truckString = "The truck has the following content:\n";
        HashMap<Product, Integer> content = getContent();
        for(Product product: content.keySet())
        {
            truckString += String.format("Product %s: %d pieces\n", product.getName(), content.get(product));
        }
        truckString += String.format("with a value of: %d", getValue());
        return truckString;
    }

}
