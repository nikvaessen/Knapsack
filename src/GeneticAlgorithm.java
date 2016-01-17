
import java.util.*;

/**
 * Created by Bia on 1/11/2016.
 */
public class GeneticAlgorithm {

    //general parameters
    public static final int POPULATION_SIZE = 100;
    public static final int GENERATIONS = 100;
    public static final int MUTATION_CHANCE = 15;

    //specific parameters
    public static final boolean UNBOUNDED = true;
    public static final boolean ELITE_SELECTION = true;
    public static final int SELECTION_PERCENT = 40; //CANNOT BE BIGGER THAN 50
    public static final int AMOUNT_OF_ROTATION_MUTATIONS  = 10;
    public static final int AMOUNT_OF_POSITION_MUTATIONS  = 10;
    public static final int AMOUNT_OF_PRODUCT_MUTATIONS   = 10;

    //print info of each generation
    public static final boolean PRINT_IN_GENERATION = true;
    public static final boolean PRINT_SUBPOPULATIONS = false;
    public static final boolean PRINT_CONTENT = false;

    public static Random rng = new Random(System.currentTimeMillis());

    public static void main(String[] argv)
    {
        System.out.println("Beginning of Genetic Algorithm execution!");
        ProductSet baseLine;
        if(UNBOUNDED){
            baseLine = Knapsack.getDefaultUnboundedProductSet();
        }
        else{
            baseLine = Knapsack.getDefaultProductSet();
        }
        //creation of initial population
        ArrayList<ProductSet> population = new ArrayList<>();
        for(int i = 0; i < POPULATION_SIZE; i++)
        {
            ProductSet ps = baseLine.clone();
            ps.shuffle();
            population.add(ps);
            System.out.printf("Length of alleles of %d element in population: %d\n",
                    i, population.get(i).getAlleles().size());
        }
        //start genetic algorithm
        evolvePopulationMatrix(GENERATIONS, population);
    }

    public static void evolvePopulationMatrix(int generations, ArrayList<ProductSet> populationMatrix)
    {
        long beginTime = System.currentTimeMillis();
        int previousFitness = getTotalFitness(getElitistSubPopulation(40, populationMatrix));
        long endTimeGeneration, beginTimeGeneration;
        for(int i = 0; i < generations; i++)
        {
            if(PRINT_IN_GENERATION)
            {
                beginTimeGeneration = System.currentTimeMillis();
                if(PRINT_CONTENT){
                    System.out.printf("Generation %2d\tstate of population:\n", i + 1);
                }
                else{
                    System.out.printf("Generation %2d", i + 1);
                }
            }
            else{
                System.out.println("Generation " + (i + 1) + " has started.");
            }
            /*  pre-selection   */
            //first sort them on highest value
            sortBasedOnFitness(populationMatrix);
            //print state of population
            if(PRINT_IN_GENERATION && PRINT_CONTENT){
                printFitness(populationMatrix);
            }

            /*  selection   */
            ArrayList<ProductSet> parents;
            //elitist selection
            if(ELITE_SELECTION)
            {
                parents = getElitistSubPopulation(SELECTION_PERCENT, populationMatrix);
            }
            //roulette wheel selection
            else
            {
                parents = rouletteWheelSelection(SELECTION_PERCENT, populationMatrix);
            }

            /*  crossover */
            //get children( 2 children per parent)
            ArrayList<ProductSet> children = getCrossedOverSubPopulation(parents);
            children.addAll(getCrossedOverSubPopulation(parents));
            //rest of new generation filled with new random individuals
            ArrayList<ProductSet> newInd = getNewIndividuals(populationMatrix.size() - parents.size() - children.size(),
                    populationMatrix.get(0));
            //mutate children
            for(int j=0; j<children.size(); j++)
            {
                if(rng.nextInt(100)<MUTATION_CHANCE)
                {
                    children.get(j).mutatePosition(AMOUNT_OF_POSITION_MUTATIONS);
                    children.get(j).mutateRotation(AMOUNT_OF_ROTATION_MUTATIONS);
                    if(UNBOUNDED)
                    {
                        children.get(j).mutateProducts(AMOUNT_OF_PRODUCT_MUTATIONS);
                    }
                }
            }

            /*  post-crossover  */
            //create the new generation out the parents, children and new individuals
            ArrayList<ProductSet> newGeneration = new ArrayList<>(populationMatrix.size());
            if(PRINT_IN_GENERATION && PRINT_SUBPOPULATIONS){
                System.out.printf(
                        "sub populations:\n" +
                        "parents with size: %d\n%s" +
                        "children with size: %d\n%s" +
                        "new individuals with size: %d\n%s",
                        parents.size(), getFitnessString(parents),
                        children.size(), getFitnessString(children),
                        newInd.size(), getFitnessString(newInd));
            }
            newGeneration.addAll(parents);
            newGeneration.addAll(children);
            newGeneration.addAll(newInd);
            populationMatrix = newGeneration;
            //print results of generation
            if(PRINT_IN_GENERATION){
                endTimeGeneration = System.currentTimeMillis();
                System.out.printf(" took %4d ms to compute. ", endTimeGeneration - beginTimeGeneration);
                System.out.printf("Highest value individual is %d. ", populationMatrix.get(0).getFitness());
                int newFitness = getTotalFitness(parents);
                System.out.printf("Fitness improvement over previous generation: %d \n", newFitness - previousFitness);
                previousFitness = newFitness;
            }
        }

        //print results
        sortBasedOnFitness(populationMatrix);
        long endTime   = System.currentTimeMillis();
        System.out.printf("####################_RESULTS_####################\n");
        System.out.printf("Execution time: %d ms\t %d s\n", endTime - beginTime, (endTime - beginTime) / 1000 );
        System.out.printf("Truck with highest value: %d\n", populationMatrix.get(0).getFitness());
        System.out.printf("Content of truck: \n");
        populationMatrix.get(0).getFilledTruck().printTruckCoronally();
    }

    public static void sortBasedOnFitness(List<ProductSet> arrayList)
    {
        Collections.sort(arrayList, Collections.reverseOrder());
    }

    public static int getTotalFitness(List<ProductSet> population)
    {
        int fitnessSum = 0;
        for(ProductSet ps : population)
        {
            fitnessSum += ps.getFitness();
        }
        return fitnessSum;
    }

    public static ArrayList<ProductSet> getElitistSubPopulation(int percent, List<ProductSet> population)
    {
        double n = population.size() * (double) percent / 100;
        ArrayList<ProductSet> elitistSubPopulation = new ArrayList<>();
        //sortBasedOnFitness(population);
        for(int i=0; i<n; i++)
            elitistSubPopulation.add(population.get(i));

        return elitistSubPopulation;
    }

    public static ArrayList<ProductSet> rouletteWheelSelection(int percent, List<ProductSet> population)
    {
        ArrayList<ProductSet> selectedPopulation = new ArrayList<>();
        int sumOfFitnesses = 0, m, k=0, fitness = 0;
        double n = population.size() * (double) percent / 100;
        Random rng = new Random();

        for(int i=0; i<population.size(); i++)
            sumOfFitnesses += population.get(i).getFitness();

        while(k<n)
        {
            m = rng.nextInt(sumOfFitnesses);
            for(int i=0; i<population.size(); i++)
            {
                fitness += population.get(i).getFitness();
                if(fitness >= m)
                {
                    selectedPopulation.add(k, population.get(i));
                    break;
                }
            }
            fitness = 0;
            k++;
        }

        return selectedPopulation;
    }

    public static ArrayList<ProductSet> getCrossedOverSubPopulation(List<ProductSet> parents)
    {
        ArrayList<ProductSet> crossedOverPopulation = new ArrayList<>(parents.size()/2);
        int n = parents.size();
        int cnt = 0;
        for(int i=0; i<n; i+=2)
        {
            ProductSet child = ProductSet.createChild(parents.get(i), parents.get(i + 1));
            crossedOverPopulation.add(cnt, child);
            cnt++;
        }
        return crossedOverPopulation;
    }

    public static void replaceSubPopulation(int from, int until,
                                            List<ProductSet> toBeReplaced, List<ProductSet> replacements)
    {
        int k=0;
        for(int i=from; i<until; i++)
        {
            toBeReplaced.add(i, replacements.get(k));
            k++;
        }
    }

    public static ArrayList<ProductSet> getNewIndividuals(int amount, ProductSet baseLine)
    {
        ArrayList<ProductSet> set = new ArrayList<>();
        for(int i = 0; i < amount; i++)
        {
            ProductSet ps = baseLine.clone();
            ps.shuffle();
            List psContent = ps.getList();
            for(int j = 0; j < psContent.size(); j++)
            {
                ps.mutateRotation(ps.size());
            }
            set.add(ps);
        }
        return set;
    }

    public static void printFitness(ArrayList<ProductSet> population)
    {
        int count = 0;
        for(ProductSet p : population)
        {
            count++;
            System.out.printf("Fitness of element %3d: %d. content: %s\n", count, p.getFitness(), p.toString());
        }
    }

    public static String getFitnessString(ArrayList<ProductSet> population)
    {
        String toReturn = "";
        int count = 0;
        for(ProductSet p : population)
        {
            count++;
            toReturn += String.format("Fitness of element %3d: %d. content: %s\n", count, p.getFitness(), p.toString());
        }
        return toReturn;
    }

}
