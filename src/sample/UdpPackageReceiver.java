package sample;

import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class UdpPackageReceiver implements Runnable{

    boolean running = false;
    DatagramSocket socket;
    private byte[] buf = new byte[256];
    int port;
    static String command;

    List udpPackages;
    static List<String> commands = new Stack<>();




    public UdpPackageReceiver(List udpPackages, int port) {
        this.running = true;
        this.udpPackages = udpPackages;
        this.port = port;


        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void shutDown(){
        running = false;
    }

    @Override
    public void run() {


        while (running)
        {
            buf = new byte[256];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try {
                socket.receive(packet);
                System.out.println("\npackage arrived!");
                UdpPackage udpPackage = new UdpPackage("name", packet.getData(), packet.getAddress(), socket.getLocalAddress(), packet.getPort(), socket.getLocalPort());
                udpPackages.add(udpPackage);
                String command = new String(packet.getData()).trim();
                this.command = command;
                System.out.println(command);
                commands.add(UdpPackageReceiver.command);
                //System.out.println(commands);
                /*Ifor (int i = 0; i<UdpPackageReceiver.commands.size(); i++){
                    commands.remove("stop");
                }*/


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
