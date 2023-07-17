package pb.org.hexapoda.model;

import java.awt.*;

public class DockPoint {

    public enum Type {
        TOP_L(-45f),
        TOP_R(45f),
        CENTER_L(-90f),
        CENTER_R(90f),
        BOTTOM_L(-135f),
        BOTTOM_R(135f);

        final float rotation;

        Type(float rotation) {
            this.rotation = rotation;
        }
    }

    private final Type type;

    public DockPoint(Type initialType) {
        type = initialType;
    }

    public void draw(Graphics graphics, Point center, float rotation) {
        float _rotation = (rotation + type.rotation) % 360f;
        int shift = 4;      // width/2 - 1
        int distance = 5;   // width/2

        int x = center.x + shift - (int) (distance * Math.sin(Math.toRadians(_rotation)));
        int y = center.y + shift - (int) (distance * Math.cos(Math.toRadians(_rotation)));

        graphics.setColor(Color.WHITE);
        graphics.fillOval(x, y, 3, 3);
    }
}
