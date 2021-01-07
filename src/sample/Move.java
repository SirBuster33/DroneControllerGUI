package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Move implements Runnable {
    String command;
    Drone drone;
    GraphicsContext graphics;
    Canvas canvas;


    public Move(String command, Drone drone, GraphicsContext graphics, Canvas canvas) {
        this.command = command;
        this.drone = drone;
        this.graphics = graphics;
        this.canvas = canvas;
    }

    public void Move(String command, Drone drone, GraphicsContext graphics, Canvas canvas) {
        int speed = 5;
        switch (command) {
            case "takeoff":
                drone.setRadius(30);
                drone.setY((int)canvas.getHeight() / 2);
                drone.setX((int)canvas.getWidth() / 2);
                resetCanvas(graphics, canvas);
                break;
            case "land":
                drone.setRadius(20);
                drone.setY((int)canvas.getHeight() / 2);
                drone.setX((int)canvas.getWidth() / 2);
                resetCanvas(graphics, canvas);
                break;
            case "backward":
                drone.setY(drone.getY() + speed);
                resetCanvas(graphics, canvas);
                break;
            case "forward":
                drone.setY(drone.getY() - speed);
                resetCanvas(graphics, canvas);
                break;
            case "left":
                drone.setX(drone.getX() - speed);
                resetCanvas(graphics, canvas);
                break;
            case "right":
                drone.setX(drone.getX() + speed);
                resetCanvas(graphics, canvas);
                break;
            case "up":
                drone.setRadius(drone.getRadius() + speed);
                resetCanvas(graphics, canvas);
                break;
            case "down":
                drone.setRadius(drone.getRadius() - speed);
                resetCanvas(graphics, canvas);
                break;
            case "cw":
                drone.setYaw(drone.getYaw() + speed);
                resetCanvas(graphics, canvas);
                break;
            case "ccw":
                drone.setYaw(drone.getYaw() - speed);
                resetCanvas(graphics, canvas);
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
        Move(command, drone, graphics, canvas);
    }
}




