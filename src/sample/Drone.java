package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Drone {
    int x, y, radius, yaw;

    public Drone(int x, int y, int radius, int yaw){
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.yaw = yaw;
    }
    void setX(int x) {
        this.x = x;
    }
    int getX() {
        return this.x;
    }

    void setY(int y) {
        this.y = y;
    }
    int getY() {
        return this.y;
    }

    void setRadius(int radius){
        this.radius = radius;
    }
    int getRadius(){
        return this.radius;
    }

    void setYaw(int yaw){
        this.yaw = yaw;
    }
    int getYaw(){
        return this.yaw;
    }

    public void draw(GraphicsContext graphics){
        graphics.setFill(Color.RED);
        graphics.fillOval(x-radius, y-radius, radius * 2, radius * 2);

    }

}
