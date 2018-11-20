/**
 * BackgroundDingus
 * Does exactly what the name says :-)
 *
 * @author Roy van Schaijk (1314599)
 * @author Luc Geven (1336029)
 * @author huub
 */

package Week5;

import java.awt.*;

class BackgroundDingus extends Dingus {
    protected Color firstRandomColor;
    protected Color secondRandomColor;

    public BackgroundDingus(int maxX, int maxY) {
        // intialize randomly the Dingus properties, i.e., position and color
        super(maxX, maxY);
        //random colors without alpha for gradient
        firstRandomColor = getRandomColor(false);
        secondRandomColor = getRandomColor(false);
    }

    @Override
    void draw(Graphics2D g2d) {
        GradientPaint gp = new GradientPaint(0, 0, color, maxX, maxY, secondRandomColor);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, maxX, maxY);
    }
}
