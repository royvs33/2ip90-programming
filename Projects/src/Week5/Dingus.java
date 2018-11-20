/**
 * Dingus --  part of HA Random Artist
 * abstract class representing an arbitrary shape
 *
 * @author Roy van Schaijk (1314599)
 * @author Luc Geven (1336029)
 * @author huub
 */

package Week5;

import java.awt.*;
import java.util.Random;

abstract class Dingus {
    /**
     * random generator to be used by all subclasses of Dingus
     * do not change
     */
    Random random = Painting.random;

    /**
     * postion of the shape (upper left corner)
     **/
    protected int x, y;

    /**
     * color used for drawing this shape
     **/
    protected Color color;

    /**
     * maximal coordinates; drawing area is (0,0)- (maxX,maxY)
     **/
    int maxX, maxY;

    /**
     * initializes color and position to random values
     *
     * @param maxX, maxY - give maximum values for the position
     */
    public Dingus(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        // initialize to a random position
        x = random.nextInt(maxX);
        y = random.nextInt(maxY);
        // initialize to a random color
        color = getRandomColor(true);
    }

    public Color getRandomColor(boolean allowAlpha) {
        //this is the most "random" color I could think of
        float red = random.nextFloat();
        float green = random.nextFloat();
        float blue = random.nextFloat();
        if (allowAlpha) {
            float alpha = random.nextFloat();
            return new Color(red, green, blue, alpha);
        } else {
            return new Color(red, green, blue);
        }
    }

    abstract void draw(Graphics2D g2d);
}
