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

import java.lang.reflect.InvocationTargetException;
import java.net.DatagramSocket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Stack;


public class Main extends Application {
    final private static double SCENE_WIDTH = 1000;
    final private static double SCENE_HEIGHT = SCENE_WIDTH/16 * 9;
    final private static double RADIUS = 25;
    //final private double MAX_SPEED = 5;
    //final private double INITIAL_VELOCITY = 0.1;
    //final private double GRAVITY_CONSTANT = 0.004;
    private double moveSpeed = 50;
    //private double transitionDuration = 10/moveSpeed;
    private double centerX = 100;
    private double centerY = 100;
    public Circle drone = new Circle(centerY, centerX, RADIUS, Color.RED);
    String command = UdpPackageReceiver.command;




    public static void main(String[] args) {

        ObservableList<UdpPackage> savedPackages = FXCollections.observableArrayList();
        ObservableList<UdpPackage> loggedPackages = FXCollections.observableArrayList();

        UdpPackageReceiver receiver;
        DatagramSocket sender;

        launch(args);


    }
    @Override
    public void start(Stage primaryStage) throws Exception{

        /*drone.setFill(Color.RED);
        drone.setRadius(RADIUS);
        drone.setLayoutX(SCENE_WIDTH/2);
        drone.setLayoutY(SCENE_HEIGHT-RADIUS);*/


        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Pane root = loader.load();
        root.getChildren().add(drone);
        Scene scene = new Scene (root, SCENE_WIDTH, SCENE_HEIGHT);

        primaryStage.setTitle("Drone Emulator GUI");
        primaryStage.setScene(scene);
        primaryStage.show();


        //final Group group = new Group(labelInstructions(), drone, root);

        //moveDroneKey(scene, drone);
        //moveDroneController();

    }

    private Label labelInstructions(){
        Label instructions = new Label("Use the arrow keys to move the circle");
        return instructions;
    }

    /*private void moveDroneController(){
        try {
            switch (command) {
                case "done":
                    drone.setCenterX(drone.getCenterX());
                    System.out.println("commmand read");
                    break;
                case "moveright":
                    drone.setCenterX(drone.getCenterX() - 5);
                    break;
                case "moveleft":
                    drone.setCenterX(drone.getCenterX() + 5);
                    break;
                case "moeup":
                    drone.setCenterY(drone.getCenterY() - 5);
                    break;
                case "movedown":
                    drone.setCenterY(drone.getCenterY() + 5);
                    break;
            }
        }
        catch (Exception e){
            e.printStackTrace();

        }
    }*/


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
