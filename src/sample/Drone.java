package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Drone {
    int x, y, radius;

    public Drone(int x, int y, int radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
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

    public void draw(GraphicsContext graphics){
        graphics.fillOval(x-radius, y-radius, radius * 2, radius * 2);
        graphics.setFill(Color.BLACK);

    }

}
