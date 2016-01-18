import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
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
        Label yRotate = new Label("Rotate around Y-axis");
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

//        Group subroot3D = new Group();
//        SubScene subscene3D = new SubScene(subroot3D, 800, 650, true, SceneAntialiasing.BALANCED);

        GridPane grid = new GridPane();
        SubScene subsceneUI = new SubScene(grid, 800, 400);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setGridLinesVisible(false);
        grid.setPadding(new Insets(5));

//        PerspectiveCamera cam = new PerspectiveCamera();
//        cam.setTranslateZ(-1000);
//        cam.setNearClip(0.1);
//        cam.setFarClip(2000.0);
//        cam.setFieldOfView(35);
//        subscene3D.setCamera(cam);

//        Rotate rx = new Rotate(0, Rotate.X_AXIS);
//        Rotate ry = new Rotate(0, Rotate.Y_AXIS);

//        Label xRotate = new Label("Rotate around X-axis");
//        GridPane.setConstraints(xRotate, 0, 0, 8, 1);
//        Slider xSlider = new Slider(0, 360, 60);
//        xSlider.valueProperty().addListener(new ChangeListener(){
//            @Override
//            public void changed(ObservableValue arg0, Object arg1, Object arg2) {
//                rx.setAngle(xSlider.getValue());
//            }
//        });
//        GridPane.setConstraints(xSlider, 0, 1, 8, 1);

//        Label yRotate = new Label("Rotate around Y-axis");
//        GridPane.setConstraints(yRotate, 0, 2, 8, 1);
//        Slider ySlider = new Slider(0, 360, 60);
//        ySlider.valueProperty().addListener(new ChangeListener(){
//            @Override
//            public void changed(ObservableValue arg00, Object arg11, Object arg22) {
//                ry.setAngle(ySlider.getValue());
//            }
//        });
//        GridPane.setConstraints(ySlider, 0, 3, 8, 1);
//
//        grid.getChildren().addAll(/*xRotate, xSlider, */yRotate, ySlider);

        Font font = new Font(15);

        Label productA = new Label("Product A:");
        productA.setFont(font);
        GridPane.setConstraints(productA, 0, 4, 8, 1);
        Label widthA = new Label("width:");
        GridPane.setConstraints(widthA, 0, 5);
        TextField setWidthA = new TextField("10");
        GridPane.setConstraints(setWidthA, 1, 5);
        data[0][0] = Integer.parseInt(setWidthA.getText());
        Label heightA = new Label("height:");
        GridPane.setConstraints(heightA, 2, 5);
        TextField setHeightA = new TextField("10");
        GridPane.setConstraints(setHeightA, 3, 5);
        data[0][1] = Integer.parseInt(setHeightA.getText());
        Label lengthA = new Label("length:");
        GridPane.setConstraints(lengthA, 4, 5);
        TextField setLengthA = new TextField("20");
        GridPane.setConstraints(setLengthA, 5, 5);
        data[0][2] = Integer.parseInt(setLengthA.getText());
        Label valueA = new Label("value:");
        GridPane.setConstraints(valueA, 6, 5);
        TextField setValueA = new TextField("3");
        GridPane.setConstraints(setValueA, 7, 5);
        data[0][3] = Integer.parseInt(setValueA.getText());

        grid.getChildren().addAll(productA, heightA, setHeightA, widthA, setWidthA, lengthA, setLengthA, valueA, setValueA);



        Label productB = new Label("Product B:");
        GridPane.setConstraints(productB, 0, 6, 8, 1);
        productB.setFont(font);
        Label widthB = new Label("width:");
        GridPane.setConstraints(widthB, 0, 7);
        TextField setWidthB = new TextField("10");
        GridPane.setConstraints(setWidthB, 1, 7);
        data[1][0] = Integer.parseInt(setWidthB.getText());
        Label heightB = new Label("height:");
        GridPane.setConstraints(heightB, 2, 7);
        TextField setHeightB = new TextField("15");
        GridPane.setConstraints(setHeightB, 3, 7);
        data[1][1] = Integer.parseInt(setHeightB.getText());
        Label lengthB = new Label("length:");
        GridPane.setConstraints(lengthB, 4, 7);
        TextField setLengthB = new TextField("20");
        GridPane.setConstraints(setLengthB, 5, 7);
        data[1][2] = Integer.parseInt(setLengthB.getText());
        Label valueB = new Label("value:");
        GridPane.setConstraints(valueB, 6, 7);
        TextField setValueB = new TextField("4");
        GridPane.setConstraints(setValueB, 7, 7);
        data[1][3] = Integer.parseInt(setValueB.getText());

        grid.getChildren().addAll(productB, heightB, setHeightB, widthB, setWidthB, lengthB, setLengthB, valueB, setValueB);

        Label productC = new Label("Product C:");
        GridPane.setConstraints(productC, 0, 8, 8, 1);
        productC.setFont(font);
        Label widthC = new Label("width:");
        GridPane.setConstraints(widthC, 0, 9);
        TextField setWidthC = new TextField("15");
        GridPane.setConstraints(setWidthC, 1, 9);
        data[2][0] = Integer.parseInt(setWidthC.getText());
        Label heightC = new Label("height:");
        GridPane.setConstraints(heightC, 2, 9);
        TextField setHeightC = new TextField("15");
        GridPane.setConstraints(setHeightC, 3, 9);
        data[2][1] = Integer.parseInt(setHeightC.getText());
        Label lengthC = new Label("length:");
        GridPane.setConstraints(lengthC, 4, 9);
        TextField setLengthC = new TextField("15");
        GridPane.setConstraints(setLengthC, 5, 9);
        data[2][2] = Integer.parseInt(setLengthC.getText());
        Label valueC = new Label("value:");
        GridPane.setConstraints(valueC, 6, 9);
        TextField setValueC = new TextField("5");
        GridPane.setConstraints(setValueC, 7, 9);
        data[2][3] = Integer.parseInt(setValueC.getText());

        grid.getChildren().addAll(productC, heightC, setHeightC, widthC, setWidthC, lengthC, setLengthC, valueC, setValueC);

//        Node cubes = greedy();
//        cubes.getTransforms().addAll(ry);
//        subroot3D.getChildren().add(cubes);


        Button greedy = new Button("Greedy Algorithm");
        greedy.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                root.getChildren().add(create3dContent(greedy()));
            }
        });
        GridPane.setConstraints(greedy, 0, 11, 2, 2, HPos.CENTER, VPos.CENTER);

        Button easy = new Button("Easy Solution");
        easy.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                try {
                    root.getChildren().add(create3dContent(easy()));
                } catch (HollowSpace.NoRoomException e1) {
                    e1.printStackTrace();
                }
            }
        });
        GridPane.setConstraints(easy, 2, 11, 2, 2, HPos.CENTER, VPos.CENTER);
        Button random = new Button("Random placement");
        GridPane.setConstraints(random, 4, 11, 2, 2, HPos.CENTER, VPos.CENTER);
        Button divide = new Button("Divide-and-Conquer");
        GridPane.setConstraints(divide, 6, 11, 2, 2, HPos.CENTER, VPos.CENTER);

        grid.getChildren().addAll(greedy, easy, random, divide);

//        TextArea description = new TextArea(result);
//        description.setEditable(false);
//        GridPane.setConstraints(description, 2, 12, 4, 1);
//        grid.getChildren().add(description);

        SubScene subscene3d = create3dContent(easy());

        root.setAlignment(subsceneUI, Pos.BOTTOM_LEFT);
        root.getChildren().add(subsceneUI);

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

    private Group easy() throws HollowSpace.NoRoomException {

        Product A = new Product(10, 10, 20, 3/*MINIMUM_PRIZE + rng.nextInt(MAXIMUM_PRIZE - MINIMUM_PRIZE)*/, "A");
        Product B = new Product(10, 15, 20, 4/*MINIMUM_PRIZE + rng.nextInt(MAXIMUM_PRIZE - MINIMUM_PRIZE)*/, "B");
        Product C = new Product(15, 15, 15, 5/*MINIMUM_PRIZE + rng.nextInt(MAXIMUM_PRIZE - MINIMUM_PRIZE)*/, "C");

        Truck truck = new Truck(165, 25, 40);

        for(int i = 0; i < 32; i++){
            truck.add(A);
            truck.add(B);
        }

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
        Product A = new Product(data[0][0], data[0][1], data[0][2], data[0][3], "A");
        Product B = new Product(data[1][0], data[1][1], data[1][2], data[1][3], "B");
        Product C = new Product(data[2][0], data[2][1], data[2][2], data[2][3], "C");
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
                        box.setTranslateY( -100 + 6*j );
                        box.setTranslateZ( 6*k );
                        root.getChildren().add(box);

                    } else {

                        PhongMaterial material = materialList.get(array[i][j][k]);
                        Box box = new Box(5, 5, 5);
                        box.setMaterial(material);
                        box.setTranslateX( -100 + 6*i );
                        box.setTranslateY( -100 + 6*j );
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
