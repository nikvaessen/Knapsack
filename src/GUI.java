import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Aleksandra on 2016-01-14.
 */
public class GUI extends Application{

    private int[][] data;
    private String result;

    @Override public void start(Stage stage) throws HollowSpace.NoRoomException {

        stage.setTitle("Cubes");
        stage.setScene(makeScene());
        stage.show();

    }

    /**
     *
     * @param cubes
     * @return SubScene with visualisation and slider to rotate it
     */
    private SubScene create3dContent(Group cubes){

        Group root = new Group();
        SubScene content3d = new SubScene(root, 800, 650);

        Group group = new Group();
        SubScene subscene3d = new SubScene(group, 800, 650, true, SceneAntialiasing.BALANCED);

        VBox sRoot = new VBox();
        SubScene sliderscene = new SubScene(sRoot, 800, 40);

        PerspectiveCamera cam = new PerspectiveCamera();
        cam.setTranslateZ(-1000);
        cam.setNearClip(0.1);
        cam.setFarClip(2000.0);
        cam.setFieldOfView(35);
        subscene3d.setCamera(cam);

        Rotate ry = new Rotate(0, Rotate.Y_AXIS);
        ry.setAxis(new Point3D(500, 0, 0));

        Label yRotate = new Label("Rotate");
        Slider ySlider = new Slider(0, 360, 60);
        ySlider.valueProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue arg00, Object arg11, Object arg22) {
                ry.setAngle(ySlider.getValue());
            }
        });
        sRoot.getChildren().addAll(yRotate, ySlider);

        cubes.getTransforms().add(ry);
        group.getChildren().add(cubes);

        root.getChildren().addAll(subscene3d, sliderscene);

        return content3d;
    }

    /**
     * Creates a scene with 3d visualisation and control panel.
     * @return Scene = main scene of the application
     */
    private Scene makeScene() throws HollowSpace.NoRoomException {

        data = new int[3][4];

        StackPane root = new StackPane();
        Scene scene = new Scene(root, 800, 650, true);

        Group group = new Group();
        SubScene subscene3d = new SubScene(group, 800, 650);

        GridPane grid = new GridPane();
        SubScene subsceneUI = new SubScene(grid, 800, 300);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setGridLinesVisible(false);
        grid.setPadding(new Insets(5));

        Label label = new Label("Choose the algorithm");
        label.setFont(new Font(15));
        GridPane.setConstraints(label, 0, 0, 4, 1, HPos.CENTER, VPos.CENTER);

        Button greedy = new Button("Greedy Algorithm");
        greedy.setPrefWidth(195);
        greedy.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                if(group.getChildren().size() != 0){
                    group.getChildren().remove(0);
                }
                group.getChildren().add(create3dContent(greedy()));
                TextArea description = new TextArea(result);
                description.setEditable(false);
                GridPane.setConstraints(description, 0, 2, 4, 3);
                grid.getChildren().add(description);
            }
        });

        GridPane.setConstraints(greedy, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER);

        Button easy = new Button("Divide-and-conquer");
        easy.setPrefWidth(195);
        easy.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                try {
                    if(group.getChildren().size() != 0){
                        group.getChildren().remove(0);
                    }
                    group.getChildren().add(create3dContent(easy()));
                    TextArea description = new TextArea(result);
                    description.setEditable(false);
                    GridPane.setConstraints(description, 0, 2, 4, 3);
                    grid.getChildren().add(description);
                } catch (HollowSpace.NoRoomException e1) {
                    e1.printStackTrace();
                }
            }
        });
        GridPane.setConstraints(easy, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER);
        Button random = new Button("Random Placement");
        random.setPrefWidth(195);
        random.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                if(group.getChildren().size() != 0){
                    group.getChildren().remove(0);
                }
                group.getChildren().add(create3dContent(random()));
                TextArea description = new TextArea(result);
                description.setEditable(false);
                GridPane.setConstraints(description, 0, 2, 4, 3);
                grid.getChildren().add(description);
            }
        });

        GridPane.setConstraints(random, 2, 1, 1, 1, HPos.CENTER, VPos.CENTER);

        Button genetic = new Button("Genetic Algorithm");
        genetic.setPrefWidth(195);
        genetic.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                if(group.getChildren().size() != 0){
                    group.getChildren().remove(0);
                }
                group.getChildren().add(create3dContent(genetic()));
                TextArea description = new TextArea(result);
                description.setEditable(false);
                GridPane.setConstraints(description, 0, 2, 4, 3);
                grid.getChildren().add(description);
            }
        });
        GridPane.setConstraints(genetic, 3, 1, 1, 1, HPos.CENTER, VPos.CENTER);

//        Button pento = new Button("Pentominoes");
//        pento.setPrefWidth();
//        pento.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                group.getChildren().remove(0);
//                group.getChildren().add(create3dContent(pento()));
//                TextArea description = new TextArea(result);
//                description.setEditable(false);
//                GridPane.setConstraints(description, 0, 1, 4, 3);
//                grid.getChildren().add(description);
//            }
//        });

        grid.getChildren().addAll(label, greedy, easy, random, genetic);

        root.setAlignment(subsceneUI, Pos.BOTTOM_LEFT);
        root.getChildren().addAll(subsceneUI, subscene3d);

        return scene;

    }


    /**
     * Creates materials for the products.
     * @return Random material
     */
    private PhongMaterial createMaterial(){

        ArrayList<PhongMaterial> materials = new ArrayList<>();

        PhongMaterial redStuff = new PhongMaterial(Color.RED);
        PhongMaterial blueStuff = new PhongMaterial(Color.BLUE);
        PhongMaterial pinkStuff = new PhongMaterial(Color.PINK);
        PhongMaterial greenStuff = new PhongMaterial(Color.GREEN);
        PhongMaterial yellowStuff = new PhongMaterial(Color.YELLOW);

        materials.add(redStuff);
        materials.add(blueStuff);
        materials.add(pinkStuff);
        materials.add(greenStuff);
        materials.add(yellowStuff);

        Random rng = new Random();
        int random = rng.nextInt(materials.size());

        return materials.get(random);

    }

    private Product[] createProducts(){

        Product A = new Product(data[0][0], data[0][1], data[0][2], data[0][3], "A");
        Product B = new Product(data[1][0], data[1][1], data[1][2], data[1][3], "B");
        Product C = new Product(data[2][0], data[2][1], data[2][2], data[2][3], "C");

        Product[] products = {A, B, C};

        return products;
    }

    private Group easy() throws HollowSpace.NoRoomException {

        Product A = new Product(10, 10, 20, 3/*MINIMUM_PRIZE + rng.nextInt(MAXIMUM_PRIZE - MINIMUM_PRIZE)*/, "A");
        Product B = new Product(10, 15, 20, 4/*MINIMUM_PRIZE + rng.nextInt(MAXIMUM_PRIZE - MINIMUM_PRIZE)*/, "B");
        Product C = new Product(15, 15, 15, 5/*MINIMUM_PRIZE + rng.nextInt(MAXIMUM_PRIZE - MINIMUM_PRIZE)*/, "C");
        Product[] originals = {A, B, C};

        Truck truck = new Truck(165, 25, 40);

        for(int i = 0; i < 32; i++){
            truck.add(A);
            truck.add(B);
        }

        result = truck.toString();

        Group cubes = createCubes(truck);

        return cubes;
    }

    /**
     * Runs a greedy algorithm and creates a visualisation of the result
     * @return group = visualisation of the result
     */
    private Group greedy(){

        //create rng
        Random rng = new Random(System.currentTimeMillis());

        //create a set of products
        //product A, B and C as described in the project booklet
        Product A = new Product(10, 10, 20, 3/*MINIMUM_PRIZE + rng.nextInt(MAXIMUM_PRIZE - MINIMUM_PRIZE)*/, "A");
        Product B = new Product(10, 15, 20, 4/*MINIMUM_PRIZE + rng.nextInt(MAXIMUM_PRIZE - MINIMUM_PRIZE)*/, "B");
        Product C = new Product(15, 15, 15, 5/*MINIMUM_PRIZE + rng.nextInt(MAXIMUM_PRIZE - MINIMUM_PRIZE)*/, "C");
        Product[] originals = {A, B, C};

        //creation of set
        Product[] set = Knapsack.createRandomProductArray(originals, rng);

        //create a truck as described in the project booklet and fill it with the made set
        Truck truck = new Truck(165, 25, 40);
        Knapsack.greedyFill(truck, set);

        result = truck.toString();

        Group cubes = createCubes(truck);
        return cubes;
    }

    private Group random(){
        Product A = new Product(10, 10, 20, 3/*MINIMUM_PRIZE + rng.nextInt(MAXIMUM_PRIZE - MINIMUM_PRIZE)*/, "A");
        Product B = new Product(10, 15, 20, 4/*MINIMUM_PRIZE + rng.nextInt(MAXIMUM_PRIZE - MINIMUM_PRIZE)*/, "B");
        Product C = new Product(15, 15, 15, 5/*MINIMUM_PRIZE + rng.nextInt(MAXIMUM_PRIZE - MINIMUM_PRIZE)*/, "C");
        Product[] originals = {A, B, C};

        Truck truck = new Truck(165, 25, 40);

        RandomFill.randomFill(truck , originals) ;

        result = truck.toString();

        Group cubes = createCubes(truck);
        return cubes;
    }

    private Group genetic(){
        if(GeneticAlgorithm.PRINT_START_OF_METHOD){
            System.out.println("Beginning of Genetic Algorithm execution!");
        }

        ProductSet baseLine;
        if(GeneticAlgorithm.UNBOUNDED){
            baseLine = Knapsack.getDefaultUnboundedProductSet();
        }
        else{
            baseLine = Knapsack.getDefaultProductSet();
        }
//        System.out.printf("Length of alleles of baseline element in population: %d\n",
//                baseLine.getAlleles().size());

        //creation of initial population
        ArrayList<ProductSet> population = new ArrayList<>();
        for(int i = 0; i < GeneticAlgorithm.POPULATION_SIZE; i++)
        {
            ProductSet ps = baseLine.clone();
            ps.shuffle();
            population.add(ps);
//            System.out.printf("Length of alleles of %d element in population: %d\n",
//                    i, population.get(i).getAlleles().size());
        }
        //start genetic algorithm
        GeneticAlgorithm.evolvePopulationMatrix(GeneticAlgorithm.GENERATIONS, population);
        GeneticAlgorithm.sortBasedOnFitness(population);
        ProductSet bestInd = population.get(0);
        bestInd.getFitness();

        result = (bestInd.getFilledTruck()).toString();

        Group cubes = createCubes(bestInd.getFilledTruck());

        return cubes;

    }

//    private Group pento(){
//
//        PerfectPento p = new PerfectPento() ;
//        int[][][] container = new int[33][5][8] ;
//        p.perfectSolution() ;
//
//        Group cubes = createCubes(container);
//
//
//    }

    /**
     * Creates visualisation of an empty array.
     * @return group = empty array
     */
    private Group createEmpty() {

        Group root = new Group();

        int[][][] array = new int[165][25][40];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                for (int k = 0; k < array[0][0].length; k++) {
                    Box box = new Box(5, 5, 5);
                    box.setMaterial(new PhongMaterial(Color.WHITE));
                    box.setTranslateX(-100 + 6 * i);
                    box.setTranslateY(-100 + 6 * j);
                    box.setTranslateZ(6 * k);
                    root.getChildren().add(box);
                }
            }
        }

        return root;
    }

    /**
     * Creates a 3d visualisation of the array.
     * @param truck
     * @return visualisation as a group
     */
    private Group createCubes(Truck truck){

        Group root = new Group();

        HashMap<Product, Integer> productList = truck.getContent();
        int numberOfProducts = 0;
        for(Product product: productList.keySet()){
            numberOfProducts += productList.get(product);
        }

        HashMap<Integer, PhongMaterial> materialList = new HashMap<>();
        for(int i = 0; i < numberOfProducts; i++){
            PhongMaterial material = createMaterial();
            materialList.put(i, material);
        }


        int[][][] array = truck.getSpace();

        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[0].length; j++){
                for(int k = 0; k < array[0][0].length; k++){

                    if(array[i][j][k] == -1){

                        Box box = new Box(5, 5, 5);
                        box.setMaterial(new PhongMaterial(Color.WHITE));
                        box.setTranslateX( -100 + 6*i );
                        box.setTranslateY( 6*j );
                        box.setTranslateZ( 6*k );
                        root.getChildren().add(box);

                    } else {

                        PhongMaterial material = materialList.get(array[i][j][k]);
                        Box box = new Box(5, 5, 5);
                        box.setMaterial(material);
                        box.setTranslateX( -100 + 6*i );
                        box.setTranslateY( 6*j );
                        box.setTranslateZ( 6*k );
                        root.getChildren().add(box);
                    }
                }
            }
        }

        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }


}
