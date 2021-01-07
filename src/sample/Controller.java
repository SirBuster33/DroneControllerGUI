package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.awt.*;
import java.net.*;



public class Controller {


    @FXML
    public Canvas canvas;
    public GraphicsContext graphics;
    public Label LabelX;
    public Label LabelY;
    public Label LabelZ;
    public Label LabelYaw;
    Drone drone = new Drone(212,175,20, 0);

    private ObservableList<UdpPackage> loggedPackages = FXCollections.observableArrayList();

    private UdpPackageReceiver receiver;
    @FXML
    private ListView<String> Commands;


    public void initialize() throws UnknownHostException {

        LabelX.setText("" + drone.getX());
        LabelY.setText("" + drone.getY());
        LabelZ.setText("" + drone.getRadius());
        LabelYaw.setText("" + drone.getYaw());

        System.out.println("creates list of packages");
        graphics = canvas.getGraphicsContext2D();
        graphics.setFill(Color.WHITE);
        graphics.fillRect(0,0,canvas.getWidth(), canvas.getHeight());
        drone.draw(graphics);

        //add udp server/receiver
        receiver = new UdpPackageReceiver(loggedPackages, 6000, this);
        // Run the receiver in a separate thread to the gui.
        new Thread(receiver).start();
    }

    public void handleCommand(String command){
        Platform.runLater(() -> {
            if (Commands != null) {
                Commands.getItems().add(0, command);
            }
            Thread moveDrone = new Thread(new Move(command, drone, graphics, canvas));
            moveDrone.start();
        });
    }
}







