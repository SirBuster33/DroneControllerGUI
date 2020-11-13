package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.net.*;

public class Controller {

    private ObservableList<UdpPackage> loggedPackages = FXCollections.observableArrayList();

    private UdpPackageReceiver receiver;


    public void initialize() throws UnknownHostException {
        System.out.println("creates list of packages");

        //add udp server/receiver
        receiver = new UdpPackageReceiver(loggedPackages, 6000);
        // Run the receiver in a separate thread to the gui.
        new Thread(receiver).start();

    }

}
