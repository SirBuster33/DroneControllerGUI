package sample;

import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.*;

public class UdpPackageReceiver implements Runnable{

    boolean running = false;
    DatagramSocket socket;
    private byte[] buf = new byte[256];
    int port;
    static String command;
    List udpPackages;
    private Controller controller;

    //probably useless for this implementation
    static List<String> commands = new Stack<>();
    static List<List<String>> listOfSplitCommands = new Stack<>();
    List<String> splitCommandList;
    static List<Integer> intCommands = new Stack<>();




    public UdpPackageReceiver(List udpPackages, int port, Controller controller) {
        this.running = true;
        this.udpPackages = udpPackages;
        this.port = port;
        this.controller = controller;



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
                controller.handleCommand(command);



                // we were using this with a different implementation for the RC controls of the tello api
                commands.add(command);
                String splitCommand[] = command.split(" ");
                splitCommandList = Arrays.asList(splitCommand);
                for(String s: commands){
                    if(s.chars().allMatch(Character :: isDigit)){
                        try {
                            int i = Integer.parseInt(s.trim());
                            intCommands.add(i);
                        }
                        catch(NumberFormatException e)
                        {
                            System.out.println("NumberFormatException: " + e.getMessage());
                        }
                    }
                }
                listOfSplitCommands.add(splitCommandList);
                System.out.println(command);





            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
