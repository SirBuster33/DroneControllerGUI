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

    //String command = UdpPackageReceiver.command;

    public static void main(String[] args) {
        launch(args);


    }

    @Override
    public void start(Stage primaryStage) throws Exception{


        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Pane root = loader.load();
        Scene scene = new Scene (root);

        primaryStage.setTitle("Drone Emulator GUI");
        primaryStage.setScene(scene);
        primaryStage.show();



        //final Group group = new Group(labelInstructions(), drone, root);


    }

    private Label labelInstructions(){
        Label instructions = new Label("Instructions");
        return instructions;
    }




}
