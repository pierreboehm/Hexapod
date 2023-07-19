package pb.org.hexapoda.model;

import java.awt.*;
import java.util.AbstractMap;
import java.util.ArrayList;

public class Walker {

    private final ArrayList<AbstractMap.SimpleEntry> dockPoints = new ArrayList<>();
    private final Point center;
    private float rotation;

    public Walker(Point initialCenter, float initialRotation) {
        center = new Point(initialCenter);
        rotation = initialRotation;

        setupDockPoints();
    }

    public void draw(Graphics graphics) {
        drawDockPoints(graphics);
    }

    public void setCenter(Point center) {
        this.center.x = center.x;
        this.center.y = center.y;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    private void drawDockPoints(Graphics graphics) {

        for (AbstractMap.SimpleEntry entryPair : dockPoints) {
            DockPoint left = (DockPoint) entryPair.getKey();
            left.draw(graphics, center, rotation);

            DockPoint right = (DockPoint) entryPair.getValue();
            right.draw(graphics, center, rotation);
        }
    }

    private void setupDockPoints() {
        /*
                TL -+- TR
                CL -+- CR
                BL -+- BR
         */
        DockPoint.Type[][] dockPointPairs = {
                {DockPoint.Type.TOP_L, DockPoint.Type.TOP_R},
                {DockPoint.Type.CENTER_L, DockPoint.Type.CENTER_R},
                {DockPoint.Type.BOTTOM_L, DockPoint.Type.BOTTOM_R}
        };

        for (DockPoint.Type[] dockPoint : dockPointPairs) {
            DockPoint left = new DockPoint(dockPoint[0]);
            DockPoint right = new DockPoint(dockPoint[1]);

            AbstractMap.SimpleEntry<DockPoint, DockPoint> entry = new AbstractMap.SimpleEntry<>(left, right);
            dockPoints.add(entry);
        }
    }
}
