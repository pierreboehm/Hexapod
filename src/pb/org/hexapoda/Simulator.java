package pb.org.hexapoda;

import pb.org.hexapoda.model.Hexapod;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Simulator {

    private final static int DELAY_MILLISECONDS = 15;
    private final static int DIMENSION_XY = 800;
    private final static int SUBJECT_COUNT = 12; //2;
    private final static int SEED_COUNT = 40; //8;

    private final static Color BACKGROUND_COLOR = Color.decode("#3895d3");

    public static void setupEnvironment() {
        JFrame frame = new JFrame("Artifical environment");
        DrawPanel drawPanel = new DrawPanel();

        frame.add(drawPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // not implemented yet
            }

            @Override
            public void keyPressed(KeyEvent e) {
                drawPanel.updateKeyEvent(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // not implemented yet
            }
        });
        frame.pack();
        frame.setVisible(true);

        startLooper(frame);
    }

    public static int getRandomNumberInRange(int min, int max) {
        return (int)(Math.random() * ((max - min) + 1)) + min;
    }

    private static void startLooper(JFrame frame) {
        final Timer timer = new Timer(DELAY_MILLISECONDS, null);

        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!frame.isVisible()) {
                    timer.stop();
                } else {
                    frame.repaint();
                }
            }
        });

        timer.start();
    }

    private static class DrawPanel extends JPanel {

        private Hexapod hexapod;

        public DrawPanel() {
            setBackground(BACKGROUND_COLOR);
            setupHexapod();
        }

        @Override
        protected void paintComponent(final Graphics graphics) {
            // Calling to clear the artifacts!
            super.paintComponent(graphics);

            hexapod.draw(graphics);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(DIMENSION_XY, DIMENSION_XY);
        }

        public void updateKeyEvent(KeyEvent keyEvent) {
            hexapod.update(keyEvent);
        }

        private void setupHexapod() {
            hexapod = new Hexapod(new Point(DIMENSION_XY / 2, DIMENSION_XY / 2));
        }
    }
}
