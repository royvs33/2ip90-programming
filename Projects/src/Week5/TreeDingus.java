/**
 * TreeDingus - part of HA RandomArtist
 * a little bit more advanced example of a shape
 * a tree consisting of a trunk (rectangle) and a crown (circle)
 *
 * @author huub
 */

package Week5;

import java.awt.*;

class TreeDingus extends Dingus {
    private int crownRadius;
    private int trunkHeight;
    private int trunkWidth;
    private boolean filled; // true: filled; false: outline

    public TreeDingus(int maxX, int maxY) {
        // initialize Dingus properties
        super(maxX, maxY);

        // initialize TreeDingus properties
        crownRadius = random.nextInt(maxX/4); // or something more sophisticated
        trunkHeight = random.nextInt((maxY-2*crownRadius)/2);
        trunkWidth = crownRadius/3 + 1;
        filled = random.nextBoolean();
    }

    @Override
    void draw(Graphics2D g2d) {
        // draw crown
        g2d.setColor(color);
        if (filled) {
            // more general way to draw an oval than with fillOval (hint :-)
            g2d.fillArc(x, y, (2 * crownRadius), (2 * crownRadius), 0, 360);
        } else {
            g2d.drawArc(x, y, (2 * crownRadius), (2 * crownRadius), 0, 360);
        }

        // draw trunk
        g2d.setColor(color);
        int xx = x + crownRadius - (trunkWidth / 2);
        int yy = y + (2 * crownRadius);
        if (filled) {
            g2d.fillRect(xx, yy, trunkWidth, trunkHeight);
        } else {
            g2d.fillRect(xx, yy, trunkWidth, trunkHeight);
        }
    }
}
