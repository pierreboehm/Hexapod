package pb.org.hexapoda.model;

import pb.org.hexapoda.Simulator;

import java.awt.*;

public class Part {

    public enum State {
        PASSIVE,
        ACTIVE
    }

    private State state;
    private Point center;
    private float rotation;

    public Part(Point position) {
        setup(position, State.PASSIVE);
    }

    public Part(Point position, State state) {
        setup(position, state);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point position) {
        center = position;
    }

    public void turnLeft() {
        rotation = (rotation + 15f) % 360f;
    }

    public void turnRight() {
        rotation = (rotation - 15f) % 360f;
    }

    public void move() {
        center.x += Math.sin(Math.toRadians(rotation));
        center.y += Math.cos(Math.toRadians(rotation));
    }

    private void setup(Point position, State initialState) {
        center = new Point(position);
        state = initialState;
        rotation = (float) Simulator.getRandomNumberInRange(0, 359);
    }
}
