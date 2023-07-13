package pb.org.hexapoda.model;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Hexapod {

    private ArrayList<Part> partList = new ArrayList<Part>();

    public Hexapod(Point position) {
        setup(position);
    }

    public void draw(Graphics graphics) {
        for (Part part : partList) {
            part.draw(graphics);
        }
    }

    public void update(KeyEvent keyEvent) {
        Part headPart = partList.get(0);

        updateHeadRotation(headPart, keyEvent.getKeyCode());
        updateBodyPosition();
        updateHeadPosition(headPart);
    }

    private void updateHeadRotation(Part headPart, int keyCode) {
        // 37 left, 38 up, 39 right, 40 down
        switch (keyCode) {
            case 37: {
                headPart.turnLeft();
                break;
            }
            case 39: {
                headPart.turnRight();
                break;
            }
            default: {
                break;
            }
        }
    }

    private void updateHeadPosition(Part headPart) {
        headPart.move();
    }

    private void updateBodyPosition() {
        for (int index = partList.size() - 1; index > 0; index--) {
            Part nextPart = partList.get(index);
            Part prevPart = partList.get(index - 1);

            nextPart.setCenter(prevPart.getCenter());
            nextPart.setRotation(prevPart.getRotation());
        }
    }

    private void setup(Point position) {
        Part headPart = new Part(position, Part.Type.HEAD);
        headPart.setDimension(10, 10);
        partList.add(headPart);

        Point bodyPosition = new Point();
        bodyPosition.x = headPart.getCenter().x - (int)(10 * Math.sin(Math.toRadians(headPart.getRotation()/* - 180 % 360*/)));
        bodyPosition.y = headPart.getCenter().y - (int)(10 * Math.cos(Math.toRadians(headPart.getRotation()/* - 180 % 360*/)));

        Part bodyPart = new Part(bodyPosition, Part.Type.BODY, headPart.getRotation());
        bodyPart.setDimension(10, 10);
        partList.add(bodyPart);

        Point tailPosition = new Point();
        tailPosition.x = bodyPart.getCenter().x - (int)(10 * Math.sin(Math.toRadians(bodyPart.getRotation()/* - 180 % 360*/)));
        tailPosition.y = bodyPart.getCenter().y - (int)(10 * Math.cos(Math.toRadians(bodyPart.getRotation()/* - 180 % 360*/)));

        Part tailPart = new Part(tailPosition, Part.Type.TAIL, bodyPart.getRotation());
        tailPart.setDimension(8, 8);
        partList.add(tailPart);
    }
}
