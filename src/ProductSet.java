import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by baxie on 11-1-16.
 */
public class ProductSet implements Comparable, Cloneable
{
    private List<Product> set;
    private Truck truck;
    private Random rng;

    private boolean fitnessKnown;
    private int fitness;

    public ProductSet(Truck truck,  Random rng)
    {
        set = new ArrayList<>();
        this.truck = truck.clone();
        this.rng = rng;
        fitnessKnown = false;
    }

    public void addProduct(Product p, int frequency)
    {
        for(int i = 0; i < frequency; i++)
        {
            set.add(p);
        }
        shuffle();
    }

    public void add(Product p)
    {
        set.add(p);
    }


    public void mutatePosition(int mutations)
    {
        fitnessKnown = false;
        for(int i = 0; i < mutations; i++)
        {
            switchElements(rng.nextInt(set.size()), rng.nextInt(set.size()));
        }
    }

    public void mutateRotation(int mutations)
    {
        fitnessKnown = false;
        for(int i = 0; i < mutations; i++)
        {
            int index = rng.nextInt(set.size());
            Product[] alleles = set.get(index).getRotations();
            set.remove(index);
            set.add(index,alleles[rng.nextInt(alleles.length)]);
        }
    }

    public int getFitness()
    {
        if(fitnessKnown)
        {
            return fitness;
        }
        else
        {
            calculateFitness();
            return fitness;
        }
    }

    public void shuffle()
    {
        fitnessKnown = false;
        Collections.shuffle(set);
    }

    private void switchElements(int pos1, int pos2)
    {
        Collections.swap(set, pos1, pos2);
    }

    private int calculateFitness()
    {
        fitnessKnown = true;
        int count = 0;
        while(truck.canFit(set.get(count)))
        {
            try
            {
                truck.add(set.get(count));
            } catch (HollowSpace.NoRoomException e)
            {
                e.printStackTrace();
            }
        }
        return truck.getValue();
    }

    @Override
    public int compareTo(Object object) {
        if ((object == null)) {
            throw new NullPointerException();
        } else if (!(object instanceof ProductSet)) {
            throw new ClassCastException();
        } else if (object == this) {
            return 0;
        } else {
            if(!fitnessKnown)
            {
                calculateFitness();
            }
            int otherFitness = ((ProductSet) object).getFitness();
            if( getFitness() > otherFitness )
            {
                return 1;
            }
            if(otherFitness == getFitness())
            {
                return 0;
            }
            else{
                return -1;
            }
        }
    }

    public List getList()
    {
        return set;
    }

    public Truck getTruck()
    {
        return truck;
    }

    public Truck getFilledTruck()
    {
        if(fitnessKnown)
        {
            return truck.clone();
        }
        else{
            calculateFitness();
            return truck.clone();
        }
    }

    public Random getRng()
    {
        return rng;
    }

    public int size()
    {
        return set.size();
    }

    public static ProductSet createChild(ProductSet father, ProductSet mother)
    {
        ProductSet child = new ProductSet(father.getTruck(), father.getRng());
        Random rng = new Random();
        int n = father.getList().size();
        for(int i=0; i<n; i++)
        {
            if(rng.nextBoolean())
            {
                child.getList().add(i, father.getList().get(i));
            }
            else
            {
                child.getList().add(i, mother.getList().get(i));
            }
        }

        return child;
    }


    @Override
    public ProductSet clone()
    {
        ProductSet clone = new ProductSet(truck, rng);
        for(Product p : set)
        {
            clone.add(p.clone());
        }
        return clone;
    }
}

