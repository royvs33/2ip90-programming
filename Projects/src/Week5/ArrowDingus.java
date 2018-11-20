/**
 * ArrowDingus
 *
 * @author Roy van Schaijk (1314599)
 * @author Luc Geven (1336029)
 * @author huub
 */

package Week5;

import java.awt.*;

class ArrowDingus extends Dingus {
    protected int arrowBaseWidth;
    protected int arrowBaseHeight;
    protected int arrowPointerWidth;
    protected int arrowPointerHeight;
    protected int rotation;
    protected boolean filled;


    public ArrowDingus(int maxX, int maxY) {
        // intialize randomly the Dingus properties, i.e., position and color
        super(maxX, maxY);

        arrowBaseWidth = 20 + random.nextInt(maxY / 10);
        arrowBaseHeight = 40 + random.nextInt(maxY / 7);
        arrowPointerWidth = arrowBaseWidth + 20 + random.nextInt(80);
        arrowPointerHeight = 10 + random.nextInt(maxY / 7);
        rotation = random.nextInt(360);
        filled = random.nextBoolean();

        //override x and wide with respect to the radius because it is ugly if it falls outside of the window
        x = 50 + random.nextInt(maxX - 100);
        y = 50 + random.nextInt(maxY - 100);

    }

    @Override
    void draw(Graphics2D g2d) {
        //translate the view to right pos
        g2d.translate(x, y);
        //set the color generated by Dingus
        g2d.setColor(color);
        //rotate the view to affect render
        g2d.rotate(Math.toRadians(rotation));

        //the base of the arrow
        if (filled) {
            g2d.fillRect(-(arrowBaseWidth / 2), 0, arrowBaseWidth, arrowBaseHeight);
        } else {
            g2d.drawRect(-(arrowBaseWidth / 2), 0, arrowBaseWidth, arrowBaseHeight);
        }

        int[] triangleX = {
            -arrowPointerWidth / 2,
            0,
            arrowPointerWidth / 2
        };
        int[] triangleY = {
            0,
            -arrowBaseHeight,
            0
        };

        if (filled) {
            g2d.fillPolygon(triangleX, triangleY, 3);
        } else {
            g2d.drawPolygon(triangleX, triangleY, 3);
        }

        //rotate image back, otherwise everything will be rotated
        g2d.rotate(-Math.toRadians(rotation));
        //translate the view back
        g2d.translate(-x, -y);
    }
}