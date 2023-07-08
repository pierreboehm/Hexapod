package pb.org.hexapoda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Simulator {

    private final static int DELAY_MILLISECONDS = 15;
    private final static int DIMENSION_XY = 800;
    private final static int SUBJECT_COUNT = 12; //2;
    private final static int SEED_COUNT = 40; //8;

    private final static Color BACKGROUND_COLOR = Color.decode("#3895d3");

    public static void setupEnvironment() {
        JFrame frame = new JFrame("Artifical environment");

        frame.add(new DrawPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        public DrawPanel() {
            setBackground(BACKGROUND_COLOR);
        }

        @Override
        protected void paintComponent(final Graphics graphics) {
            // Calling to clear the artifacts!
            super.paintComponent(graphics);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(DIMENSION_XY, DIMENSION_XY);
        }
    }
}
