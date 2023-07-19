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
        headPart.setDimension(9, 9);
        partList.add(headPart);

        Part bodyPart = new Part(headPart, Part.Type.BODY, 10);
        bodyPart.setDimension(10, 10);
        partList.add(bodyPart);

        Part tailPart = new Part(bodyPart, Part.Type.TAIL, 10);
        tailPart.setDimension(8, 8);
        partList.add(tailPart);
    }
}
