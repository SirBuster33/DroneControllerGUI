package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;

import java.awt.*;
import java.net.*;

import static sample.UdpPackageReceiver.commands;

public class Controller {


    @FXML
    public Canvas canvas;
    public GraphicsContext graphics;
    Drone drone = new Drone(100,100,20);



    private ObservableList<UdpPackage> loggedPackages = FXCollections.observableArrayList();

    private UdpPackageReceiver receiver;
    @FXML
    private ListView<String> Commands;




    public void initialize() throws UnknownHostException {
        System.out.println("creates list of packages");
        graphics = canvas.getGraphicsContext2D();
        graphics.fillRect(0,0,canvas.getWidth(), canvas.getHeight());
        graphics.setFill(Color.WHITE);
        drone.draw(graphics);

        //add udp server/receiver
        receiver = new UdpPackageReceiver(loggedPackages, 6000, this);
        // Run the receiver in a separate thread to the gui.
        new Thread(receiver).start();
    }

    public void handleCommand(String command){
        if(Commands != null){
            Commands.getItems().add(0, command);
        }
        Thread moveDrone = new Thread(new Move(command, drone, graphics, canvas));
        moveDrone.start();
    }






}
