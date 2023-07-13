package pb.org.hexapoda.model;

import pb.org.hexapoda.Simulator;

import java.awt.*;
import java.util.AbstractMap;

public class Part {

    public enum Type {
        HEAD,
        BODY,
        TAIL
    }

    private Type type;
    private Point center;
    private float rotation;
    private int width = 10;
    private int height = 10;

    public Part(Point position, Type type) {
        setup(position, type);
    }

    public Part(Point position, Type type, float rotation) {
        setup(position, type, rotation);
    }

    public void draw(Graphics graphics) {
        graphics.setColor(type == Type.HEAD ? Color.DARK_GRAY : Color.LIGHT_GRAY);
        graphics.fillOval(center.x, center.y, width, height);

        if (type == Type.BODY) {
            // draw dropPoints
        }
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point position) {
        center.x = position.x;
        center.y = position.y;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public void setDimension(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public AbstractMap.SimpleEntry<Integer, Integer> getDimension() {
        return new AbstractMap.SimpleEntry<Integer, Integer>(width, height);
    }

    public void turnLeft() {
        rotation = (rotation + 15f) % 360f;
        //System.out.println("left rotation: " + rotation);
    }

    public void turnRight() {
        rotation = (rotation - 15f) % 360f;
        //System.out.println("right rotation: " + rotation);
    }

    public void move() {
        center.x += width * Math.sin(Math.toRadians(rotation));
        center.y += height * Math.cos(Math.toRadians(rotation));
    }

    public void move(Part part) {
        center = part.getCenter();
        rotation = part.getRotation();
    }

    public void move(Point partCenter, float partRotation) {
        center = partCenter;
        rotation = partRotation;
    }

    private void setup(Point position, Type initialType) {
        center = new Point(position);
        type = initialType;
        rotation = (float) Simulator.getRandomNumberInRange(0, 359);
    }

    private void setup(Point position, Type initialType, float initialRotation) {
        center = new Point(position);
        type = initialType;

        int direction = Simulator.getRandomNumberInRange(0, 1);
        if (direction == 1) {
            rotation = initialRotation + 15f % 360f;
        } else {
            rotation = initialRotation - 15f % 360f;
        }
    }
}
