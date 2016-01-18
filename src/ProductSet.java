import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by baxie on 11-1-16.
 */
public class ProductSet implements Comparable, Cloneable
{
    private ArrayList<Product> set;
    private Truck truck;
    private Random rng;

    private boolean fitnessKnown;
    private int fitness;

    //alleles
    private Set<Product> alleles;

    public ProductSet(Truck truck,  Random rng)
    {
        set = new ArrayList<>();
        this.truck = truck.clone();
        this.rng = rng;
        fitnessKnown = false;
        alleles = new LinkedHashSet();
    }

    public ProductSet(Truck truck, Random rng, Set<Product> alleles)
    {
        set = new ArrayList<>();
        this.truck = truck;
        this.rng = rng;
        fitnessKnown = false;
        this.alleles = alleles;
    }

    public void addProduct(Product p, int frequency)
    {
        //boolean check = alleles.add(p);
        //System.out.printf("tried to add product with name %s to the set. succes: %b\n", p.getName(), check);
        //System.out.printf("Size of set: %d\n", alleles.size());
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
        //System.out.printf("Length of set of alleles: %d. Global length: %d\n", alleles.length, this.alleles.size());
        for(int i = 0; i < mutations; i++)
        {
            int index = rng.nextInt(set.size());
            set.remove(index);
            Product mutatedProduct = (Product) alleles[rng.nextInt(alleles.length)];
            mutatedProduct = mutatedProduct.clone();
            set.add(mutatedProduct);
        }
    }

    public void insertionMutation(int amount)
    {
        Object[] alleles = this.alleles.toArray();
        for(int i = 0; i < amount; i++)
        {
            Product insertedProduct = (Product) alleles[rng.nextInt(alleles.length)];
            set.add(insertedProduct.clone());
        }
    }

    public void deletionMutation(int amount)
    {
        for(int i = 0; i < amount; i++)
        {
            set.remove(rng.nextInt(set.size()));
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
        truck.emptySpace();
        for(Product p :set)
         {
             try
             {
                 if(truck.canFit(p)) truck.add(p);
                 else break;
             }
             catch (HollowSpace.NoRoomException e)
             {
                e.printStackTrace();
             }
        }
        fitness = truck.getValue();
//        int correctionForSize = set.size() - truck.getContent().size();
//        fitness -= correctionForSize;
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

    public ArrayList<Product> getList()
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

    public static ProductSet[] createChilddren(ProductSet father, ProductSet mother)
    {
        //one point crossover
        Random rng = new Random();
        ArrayList<Product> fatherDNA = father.getList();
        ArrayList<Product> motherDNA = mother.getList();
        int fatherPoint = rng.nextInt(fatherDNA.size());
        int motherPoint = rng.nextInt(motherDNA.size());
        //create sub arrays 
        ArrayList<Product> fatherFront = new ArrayList<>();
        ArrayList<Product> fatherBack  = new ArrayList<>();
        for(int i = 0; i < fatherDNA.size(); i++)
        {
            if(i < fatherPoint)
            {
                fatherFront.add(fatherDNA.get(i));    
            }
            else
            {
                fatherBack.add(fatherDNA.get(i));
            }
        }
        ArrayList<Product> motherFront = new ArrayList<>();
        ArrayList<Product> motherBack  = new ArrayList<>();
        for(int i = 0; i < motherDNA.size(); i++)
        {
            if(i < motherPoint)
            {
                motherFront.add(motherDNA.get(i));
            }
            else
            {
                motherBack.add(motherDNA.get(i));
            }
        }
        //create child 1 by combining father front and mother back
        ProductSet child1 = new ProductSet(father.getTruck().clone(), father.getRng(), father.getAlleles());
        for(int i = 0; i < fatherFront.size(); i++)
        {
            child1.add(fatherFront.get(i));
        }
        for(int i = 0; i < motherBack.size(); i++)
        {
            child1.add(motherBack.get(i));
        }
        //create child 2 by combining mother front and father back
        ProductSet child2 = new ProductSet(father.getTruck().clone(), father.getRng(), father.getAlleles());
        for(int i = 0; i < motherFront.size(); i++)
        {
            child2.add(motherFront.get(i));
        }
        for(int i = 0; i < fatherBack.size(); i++)
        {
            child2.add(fatherBack.get(i));
        }
        return new ProductSet[] {child1, child2};
    }

    @Override
    public ProductSet clone()
    {
        ProductSet clone = new ProductSet(truck.clone(), rng, alleles);
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

