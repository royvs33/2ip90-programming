package ChallengeProgram.FractalGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public abstract class BaseFractal extends JPanel {
    public JPanel sidebar;
    public JPanel mainPane;
    public int viewX = 0;
    public int viewY = 0;
    public double zoom = 1.0;

    public BaseFractal() {
        //init panel
        sidebar = new JPanel();
        mainPane = new MainPane();
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, false, sidebar, mainPane);
        splitPane.setOneTouchExpandable(true);
        //set the layout to have 2 items, a sidebar and main frame
        this.setLayout(new GridLayout(1, 2));
        //add the split pane to the panel
        this.add(splitPane);
    }

    public int adjustForZoom(int original) {
        return (int) Math.round(original * zoom);
    }

    public int round(double toRound) {
        return (int) Math.round(toRound);
    }

    abstract public void drawItems(Graphics2D g2d);

     class MainPane extends JPanel implements MouseListener, MouseWheelListener {
        private int mouseX;
        private int mouseY;

        public MainPane() {
            this.addMouseListener(this);
            this.addMouseWheelListener(this);
        }

        /**
         * Draw all shapes
         * @param g the Graphics object
         */
        @Override
        protected void paintComponent(Graphics g) {
            // Convert Graphics object to a more modern Graphics2D object
            Graphics2D g2d = (Graphics2D) g;
//            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            super.paintComponent(g2d);
            drawItems(g2d);
        }

        /**
         * Invoked when a mouse button has been pressed on a component.
         *
         * @param e the event to be processed
         */
        @Override
        public void mousePressed(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
        }

        /**
         * Invoked when a mouse button has been released on a component.
         *
         * @param e the event to be processed
         */
        @Override
        public void mouseReleased(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            if (e.getButton() == MouseEvent.BUTTON1) {
                viewX += x - mouseX;
                viewY += y - mouseY;
            }
            else {
                viewX = viewY = 0;
                zoom = 1.0;
            }
            repaint();
        }

        /**
         * Invoked when the mouse wheel is rotated.
         *
         * @param e the event to be processed
         * @see MouseWheelEvent
         */
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            zoom += ((e.getPreciseWheelRotation() * -1) / 5d);
            if (zoom < 0) {
                zoom = 0;
            }
            repaint();
        }

        /**
         * Invoked when the mouse button has been clicked (pressed
         * and released) on a component.
         *
         * @param e the event to be processed
         */
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        /**
         * Invoked when the mouse enters a component.
         *
         * @param e the event to be processed
         */
        @Override
        public void mouseEntered(MouseEvent e) {
        }

        /**
         * Invoked when the mouse exits a component.
         *
         * @param e the event to be processed
         */
        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

}
