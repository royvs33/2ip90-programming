package ChallengeProgram.FractalGenerator;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Hashtable;

public class PythagorasTree extends BaseFractal {
    private Color backgroundColor;
    private Color lineColor;
    private Color squareFillColor;
    private Color triangleFillColor;
    private double acuteAngleSizeLeft;
    private double acuteAngleSizeRight;
    private int iterationsAmount;
    private int baseSquareSize = 200;
    private final int ANGLE_DEFAULT = 45;

    public PythagorasTree() {
        super();
        //set defaults
        backgroundColor = Color.PINK;
        lineColor = Color.GREEN;
        squareFillColor = Color.GRAY;
        triangleFillColor = Color.MAGENTA;
        acuteAngleSizeLeft = Math.toRadians(ANGLE_DEFAULT);
        acuteAngleSizeRight = Math.toRadians(90d - acuteAngleSizeLeft);
        iterationsAmount = 15;

        sidebar.add(getAngleSlider());
        sidebar.add(getBaseSizeSlider());
    }

    @Override
    public void drawItems(Graphics2D g2d) {
        int xPos = (getWidth() / 2) + viewX;
        int d = baseSquareSize / 2;
        int yPos = getHeight() + viewY;

        drawTree(g2d, iterationsAmount, xPos, yPos, xPos + d, yPos);
    }

    /**
     * Recursively draw the tree part, this draws a base square with a triangle on top, and calls the next iteration
     *
     * @param lbx the left bottom x of the square
     * @param lby the left bottom y of the square
     * @param rbx the right bottom x of the square
     * @param rby the right bottom y of the square
     */
    private void drawTree(Graphics2D g2d, int iterationsLeft, int lbx, int lby, int rbx, int rby) {
        //calculate vector lb -> rb
        int dx = rbx - lbx;
        int dy = rby - lby;

        //points rt and lt
        int rtx = rbx + dy;
        int rty = rby - dx;
        int ltx = lbx + dy;
        int lty = lby - dx;

        //pythagoras
        double sqSize = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        if (sqSize <= 0) {
            return;
        }
        double slope = Math.acos(dx / sqSize);

        double cosSlope = Math.cos(slope);
        double sinSlope = Math.sin(slope);
        double angleLeftcos = Math.cos(acuteAngleSizeLeft);
        double angleLeftsin = Math.sin(acuteAngleSizeLeft);

        g2d.setColor(squareFillColor);
        g2d.fillPolygon(new int[]{lbx, rbx, rtx, ltx}, new int[]{lby, rby, rty, lty}, 4);

        double triangleLeftSideWidth = angleLeftcos * sqSize;
        double triangleMidWidth = triangleLeftSideWidth * angleLeftcos;
        double triangleHeight = triangleLeftSideWidth * angleLeftsin;
        double triangleMidX = ltx + (triangleMidWidth * cosSlope);
        double triangleMidY = lty + (triangleMidWidth * sinSlope * (dy < 0 ? -1 : 1));
        double triangleTopX = triangleMidX + (triangleHeight * sinSlope * (dy < 0 ? -1 : 1));
        double triangleTopY = triangleMidY - (triangleHeight * cosSlope);

        g2d.setColor(triangleFillColor);
        g2d.fillPolygon(new int[]{rtx, ltx, round(triangleTopX)}, new int[]{rty, lty, round(triangleTopY)}, 3);

        if (--iterationsLeft > 0) {
            drawTree(g2d, iterationsLeft, ltx, lty, round(triangleTopX), round(triangleTopY));
            drawTree(g2d, iterationsLeft, round(triangleTopX), round(triangleTopY), rtx, rty);
        }
    }

    /**
     * Method that creates a angle slider
     * @return a angle slider
     */
    private JPanel getAngleSlider() {
        // declare and initialize a panel that will contain a slider and a label
        JPanel sliderBox = new JPanel();
        // set the layout of the sliderbox to a grid layout
        sliderBox.setLayout(new GridLayout(2, 0));
        // add label to the sliderbox with the standard interval text
        sliderBox.add(new JLabel("Angle"));

        // declare and initialize slider that the user can use to change the step interval
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 1, 89, ANGLE_DEFAULT);


        // change label text below slider
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(1, new JLabel("1°"));
        labelTable.put(89, new JLabel("89°"));
        slider.setLabelTable(labelTable);

        // set some settings for slider
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        // add listener to the slider
        slider.addChangeListener(e -> {
            double angleLeftDeg = slider.getValue();
            acuteAngleSizeLeft = Math.toRadians(angleLeftDeg);
            acuteAngleSizeRight = Math.toRadians(90d - acuteAngleSizeLeft);
            repaint();
        });

        // add slider to the sliderbox
        sliderBox.add(slider);

        // return sliderbox
        return sliderBox;
    }

    /**
     * Method that creates a base size slider
     * @return a base size slider
     */
    private JPanel getBaseSizeSlider() {
        // declare and initialize a panel that will contain a slider and a label
        JPanel sliderBox = new JPanel();
        // set the layout of the sliderbox to a grid layout
        sliderBox.setLayout(new GridLayout(2, 0));
        // add label to the sliderbox with the standard interval text
        sliderBox.add(new JLabel("Base size"));

        // declare and initialize slider that the user can use to change the step interval
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 40, 500, baseSquareSize);


        // change label text below slider
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(40, new JLabel("40"));
        labelTable.put(200, new JLabel("200"));
        slider.setLabelTable(labelTable);

        // set some settings for slider
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        // add listener to the slider
        slider.addChangeListener(e -> {
            baseSquareSize = slider.getValue();
            repaint();
        });

        // add slider to the sliderbox
        sliderBox.add(slider);

        // return sliderbox
        return sliderBox;
    }

}
