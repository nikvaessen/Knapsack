import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by baxie on 16-12-15.
 */
public class Truck extends HollowSpace{
    //content of the truck
    private ArrayList<Product> content;

    /**
     * Construct a Truck object with a 3d volume
     * @param length the length of the truck in centimeters
     * @param width the width of the truck in centimeters
     * @param height the height of the truck in centimeters
     */
    public Truck(int length, int width, int height) {
        super(length, width, height);
        //setting content
        this.content = new ArrayList<>();
    }

    /**
     * Add a product to the truck.
     * @param product the product to be added to the truck
     * @throws HollowSpace.NoRoomException when the product cannot be added to the truck.
     */
    public void add(Product product) throws NoRoomException
    {
        try{
            super.fill(product, content.size());
            content.add(product);
            //System.out.println("Truck now has " + content.size() + " products");
        }
        catch(NoRoomException e){
            throw e;
        }
    }

    /**
     * returns the sum of all the products currently in the truck
     * @return the sum of the values of all products in the truck
     */
    public int getValue()
    {
        int value = 0;
        for(Product product : content)
        {
            value += product.getValue();
        }
        return value;
    }

    /**
     * Return the products and their frequency currently in the truck
     * @return a hashmap with all the individual products in the truck as keys and their frequency
     * as value pair.
     */
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
    /**
     * Returns a String format of the products in the truck and the sum of the value of all products
     */
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

    public void printTruckCoronally(int upToLength)
    {
        if(upToLength > getLength() || upToLength < 0)
            printTruckCoronally(0,getLength(),0);
        else
            printTruckCoronally(0,upToLength,0);
    }

    public void printTruckCoronally()
    {
        for(int i = 0; i < getLength(); i+= 5)
        {
            printTruckCoronally(0+i,1+i,1);
        }
        printTruckCoronally(this.getLength(),getLength() + 1,1);
    }

    public void printTruckCoronally(int startingFrom, int upToLength, int interval)
    {
        if(upToLength > getLength() || upToLength < 0)
        {
            return;
        }
        if(interval <= 0)
        {
            return;
        }
        if(startingFrom < 0 || startingFrom > upToLength || startingFrom > getLength())
        {
            return;
        }
        int truckLength = super.getLength();
        int truckWidth  = super.getWidth();
        int truckHeight = super.getHeight();
        int spacer = 0;
        int size = content.size();
        while(size > 0)
        {
            size = size / 10;
            spacer++;
        }
        if(spacer < 3)
        {
            spacer = 3;
        }
        if(startingFrom == 0) {
            System.out.printf("truck length: %d\ttruck width: %d\ttruck height: %d\tcontent: %d\tspacer: %d\n",
                    truckLength, truckWidth, truckHeight, content.size(), spacer);
        }
        for(int i = startingFrom; i < truckLength && i < upToLength; i += interval)
        {
            System.out.printf("Coronal slide %d out of %d:\n", i + 1, truckLength - 1);
            for(int j = truckHeight - 1; j >= 0 ; j--)
            {
                System.out.printf("%2d: ", j + 1);
                for(int k = 0; k < truckWidth; k++)
                {
                       System.out.printf("%" + spacer + "d ", space[i][k][j]);
                }
                System.out.println();
            }
            System.out.print("    ");
            for(int n = 1; n < getWidth() + 1; n++)
            {
                System.out.printf("|%-" + (spacer - 1) + "d ", n);
            }
            System.out.println("");
        }
    }

    @Override
    public Truck clone()  {
        return new Truck(getLength(), getWidth(), getHeight());
    }
}
