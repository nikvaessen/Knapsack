import java.util.*;

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

    //alleles
    private Set alleles;

    public ProductSet(Truck truck,  Random rng)
    {
        set = new ArrayList<>();
        this.truck = truck.clone();
        this.rng = rng;
        fitnessKnown = false;
        alleles = new LinkedHashSet();
    }

    public void addProduct(Product p, int frequency)
    {
        boolean check = alleles.add(p);
        System.out.printf("tried to add product with name %s to the set. succes: %b\n", p.getName(), check);
        System.out.printf("Size of set: %d\n", alleles.size());
        for(int i = 0; i < frequency; i++)
        {
            set.add(p);
        }
        shuffle();
    }

    public void add(Product p)
    {
        addProduct(p, 1);
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

    public void mutateProducts(int mutations)
    {
        fitnessKnown = false;
        Object[] alleles = this.alleles.toArray();
        System.out.printf("Length of set of alleles: %d. Global length: %d\n", alleles.length, this.alleles.size());
        for(int i = 0; i < mutations; i++)
        {
            int index = rng.nextInt(set.size());
            set.remove(index);
            Product mutatedProduct = (Product) alleles[rng.nextInt(alleles.length)];
            mutatedProduct = mutatedProduct.clone();
            set.add(mutatedProduct);
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

    private void calculateFitness()
    {
        fitnessKnown = true;
       for(Product p :set)
        {
            try
            {
                if(truck.canFit(p)) truck.add(p);
                else break;
            } catch (HollowSpace.NoRoomException e)
            {
                e.printStackTrace();
            }
        }
        fitness = truck.getValue();
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

    public Set getAlleles() { return alleles;}

    public Truck getTruck()
    {
        return truck;
    }

    public Truck getFilledTruck()
    {
        if(fitnessKnown)
        {
            return truck;
        }
        else{
            calculateFitness();
            return truck;
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

    @Override
    public String toString()
    {
        String toReturn = "[";
        for(Product p : set)
        {
            toReturn += p.getName() + ",";
        }
        return toReturn + "]";
    }
}

