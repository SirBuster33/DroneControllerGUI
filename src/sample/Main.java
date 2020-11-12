package sample;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.DatagramSocket;
import java.net.UnknownHostException;


public class Main extends Application {
    final private static double SCENE_WIDTH = 1000;
    final private static double SCENE_HEIGHT = SCENE_WIDTH/16 * 9;
    final private static double RADIUS = 10;
    //final private double MAX_SPEED = 5;
    //final private double INITIAL_VELOCITY = 0.1;
    //final private double GRAVITY_CONSTANT = 0.004;
    private double moveSpeed = 5;
    private double transitionDuration = 10/moveSpeed;


    public static void main(String[] args) {

        ObservableList<UdpPackage> savedPackages = FXCollections.observableArrayList();
        ObservableList<UdpPackage> loggedPackages = FXCollections.observableArrayList();

        UdpPackageReceiver receiver;
        DatagramSocket sender;

        launch(args);


    }
    @Override
    public void start(Stage primaryStage) throws Exception{

        Circle drone = new Circle();
        drone.setFill(Color.RED);
        drone.setRadius(RADIUS);
        drone.setLayoutX(SCENE_WIDTH/2);
        drone.setLayoutY(SCENE_HEIGHT-RADIUS);

        TranslateTransition trans = new TranslateTransition();
        trans.setDuration(Duration.seconds(transitionDuration));
        trans.setToX(0);
        trans.setToY(-3);
        trans.setAutoReverse(true);
        trans.setCycleCount(Animation.INDEFINITE);
        trans.setNode(drone);
        trans.play();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Pane root = loader.load();
        root.getChildren().add(drone);
        Scene scene = new Scene (root, SCENE_WIDTH, SCENE_HEIGHT);

        primaryStage.setTitle("Drone Emulator GUI");
        primaryStage.setScene(scene);
        primaryStage.show();


        //final Group group = new Group(labelInstructions(), drone, root);

        moveDroneKey(scene, drone);








    }

    private Label labelInstructions(){
        Label instructions = new Label("Use the arrow keys to move the circle");
        return instructions;
    }


    private void moveDroneKey(Scene scene, final Circle drone){
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()){
                    case UP: drone.setCenterY(drone.getCenterY() - moveSpeed); break;
                    case DOWN: drone.setCenterY(drone.getCenterY() + moveSpeed); break;
                    case LEFT: drone.setCenterX(drone.getCenterX() - moveSpeed); break;
                    case RIGHT: drone.setCenterX(drone.getCenterX() + moveSpeed); break;
                    case SHIFT: moveSpeed += 5; break;
                    case CONTROL: moveSpeed -= 5; break;
                }
            }
        });
    }



}
