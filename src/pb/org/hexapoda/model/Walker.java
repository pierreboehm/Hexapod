package pb.org.hexapoda.model;

import java.awt.*;

public class Walker {

    private Point center;
    private int distance;
    private float rotation = 0f;

    public Walker(Point initialCenter, float rotation, int distance) {
        center = new Point(initialCenter);
        this.rotation = rotation;
        this.distance = distance;
    }

    public void draw(Graphics graphics) {
        // TODO:
    }

    public void setCenter(Point center) {
        this.center.x = center.x;
        this.center.y = center.y;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
}
