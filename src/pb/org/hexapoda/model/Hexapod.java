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
        Part activePart = partList.get(0);

        // 37 left, 38 up, 39 right, 40 down
        int keyCode = keyEvent.getKeyCode();
        switch (keyCode) {
            case 37: {
                activePart.turnLeft();
                break;
            }
            case 39: {
                activePart.turnRight();
                break;
            }
            default: {
                break;
            }
        }

        for (int index = partList.size() - 1; index > 0; index--) {
            Part nextPart = partList.get(index);
            Part prevPart = partList.get(index - 1);

            nextPart.setCenter(prevPart.getCenter());
            nextPart.setRotation(prevPart.getRotation());
        }

        activePart.move();
    }

    private void setup(Point position) {
        Part activePart = new Part(position, Part.State.ACTIVE);
        partList.add(activePart);

        Point nextPosition = new Point();
        nextPosition.x = activePart.getCenter().x - (int)(10 * Math.sin(Math.toRadians(activePart.getRotation()/* - 180 % 360*/)));
        nextPosition.y = activePart.getCenter().y - (int)(10 * Math.cos(Math.toRadians(activePart.getRotation()/* - 180 % 360*/)));

        Part nextPart1 = new Part(nextPosition, Part.State.PASSIVE, activePart.getRotation());
        partList.add(nextPart1);

        nextPosition = new Point();
        nextPosition.x = nextPart1.getCenter().x - (int)(10 * Math.sin(Math.toRadians(nextPart1.getRotation()/* - 180 % 360*/)));
        nextPosition.y = nextPart1.getCenter().y - (int)(10 * Math.cos(Math.toRadians(nextPart1.getRotation()/* - 180 % 360*/)));

        Part nextPart2 = new Part(nextPosition, Part.State.PASSIVE, nextPart1.getRotation());
        partList.add(nextPart2);
    }
}
