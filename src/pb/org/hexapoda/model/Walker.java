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
        float _rotation = (rotation - 90f) % 360f;
        int _distance = distance;
        int x = center.x + 4 - (int) (_distance * Math.sin(Math.toRadians(_rotation)));
        int y = center.y + 4 - (int) (_distance * Math.cos(Math.toRadians(_rotation)));

        graphics.setColor(Color.WHITE);
        graphics.fillOval(x, y, 3, 3);

        _rotation = (rotation + 90f) % 360f;
        x = center.x + 4 - (int) (_distance * Math.sin(Math.toRadians(_rotation)));
        y = center.y + 4 - (int) (_distance * Math.cos(Math.toRadians(_rotation)));

        graphics.fillOval(x, y, 3, 3);

        // --

        _rotation = (rotation - 45f) % 360f;
        x = center.x + 4 - (int) (_distance * Math.sin(Math.toRadians(_rotation)));
        y = center.y + 4 - (int) (_distance * Math.cos(Math.toRadians(_rotation)));

        graphics.fillOval(x, y, 3, 3);

        _rotation = (rotation + 45f) % 360f;
        x = center.x + 4 - (int) (_distance * Math.sin(Math.toRadians(_rotation)));
        y = center.y + 4 - (int) (_distance * Math.cos(Math.toRadians(_rotation)));

        graphics.fillOval(x, y, 3, 3);

        // --

        _rotation = (rotation - 135f) % 360f;
        x = center.x + 4 - (int) (_distance * Math.sin(Math.toRadians(_rotation)));
        y = center.y + 4 - (int) (_distance * Math.cos(Math.toRadians(_rotation)));

        graphics.fillOval(x, y, 3, 3);

        _rotation = (rotation + 135f) % 360f;
        x = center.x + 4 - (int) (_distance * Math.sin(Math.toRadians(_rotation)));
        y = center.y + 4 - (int) (_distance * Math.cos(Math.toRadians(_rotation)));

        graphics.fillOval(x, y, 3, 3);
    }

    public void setCenter(Point center) {
        this.center.x = center.x;
        this.center.y = center.y;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
}
