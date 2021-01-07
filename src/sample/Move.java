package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;

public class Move implements Runnable {
    String command;
    Drone drone;
    GraphicsContext graphics;
    Canvas canvas;
    Label labelX;
    Label labelY;
    Label labelZ;
    Label labelYaw;


    public Move(String command, Drone drone, GraphicsContext graphics, Canvas canvas, Label labelX, Label labelY, Label labelZ, Label labelYaw) {
        this.command = command;
        this.drone = drone;
        this.graphics = graphics;
        this.canvas = canvas;
        this.labelX = labelX;
        this.labelY = labelY;
        this.labelZ = labelZ;
        this.labelYaw = labelYaw;
    }

    public void Move(String command, Drone drone, GraphicsContext graphics, Canvas canvas, Label labelX, Label labelY, Label labelZ, Label labelYaw) {
        int speed = 5;
        switch (command) {
            case "takeoff":
                drone.setRadius(drone.getRadius() * 2);
                drone.setY((int)canvas.getHeight() / 2);
                drone.setX((int)canvas.getWidth() / 2);
                resetCanvas(graphics, canvas);
                labelZ.setText("" + drone.getRadius());
                break;
            case "land":
                drone.setRadius(drone.getRadius());
                drone.setY((int)canvas.getHeight() / 2);
                drone.setX((int)canvas.getWidth() / 2);
                resetCanvas(graphics, canvas);
                labelZ.setText("" + drone.getRadius());
                break;
            case "backward":
                drone.setY(drone.getY() + speed);
                resetCanvas(graphics, canvas);
                labelX.setText("" + drone.getX());
                break;
            case "forward":
                drone.setY(drone.getY() - speed);
                resetCanvas(graphics, canvas);
                labelX.setText("" + drone.getX());
                break;
            case "left":
                drone.setX(drone.getX() - speed);
                resetCanvas(graphics, canvas);
                labelY.setText("" + drone.getY());
                break;
            case "right":
                drone.setX(drone.getX() + speed);
                resetCanvas(graphics, canvas);
                labelY.setText("" + drone.getY());
                break;
            case "up":
                drone.setRadius(drone.getRadius() + speed);
                resetCanvas(graphics, canvas);
                labelZ.setText("" + drone.getRadius());
                break;
            case "down":
                drone.setRadius(drone.getRadius() - speed);
                resetCanvas(graphics, canvas);
                labelZ.setText("" + drone.getRadius());
                break;
            case "cw":
                drone.setY(drone.getYaw() + speed);
                resetCanvas(graphics, canvas);
                labelYaw.setText("" + drone.getYaw());
                break;
            case "ccw":
                drone.setY(drone.getYaw() - speed);
                resetCanvas(graphics, canvas);
                labelYaw.setText("" + drone.getYaw());
                break;
            case "flip b":
                // drone.setY(drone.getY() - speed);
                // resetCanvas(graphics, canvas);
                break;
            /*case "speed x":
                break;*/
        }
    }

    public void resetCanvas(GraphicsContext graphics, Canvas canvas) {
        graphics.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        graphics.setFill(Color.WHITE);
        graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        drone.draw(graphics);
    }

    @Override
    public void run(){
        Move(command, drone, graphics, canvas, labelX, labelY, labelZ, labelYaw);
    }
}




