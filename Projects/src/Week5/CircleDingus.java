/**
 * CircleDingus -- part of HA RandomArtist
 * example of a very simple Dingus
 *
 * @author Roy van Schaijk (1314599)
 * @author Luc Geven (1336029)
 * @author huub
 */

package Week5;

import java.awt.*;

class CircleDingus extends Dingus {
    protected int radius;
    protected boolean filled; //true: filled, false: outline

    public CircleDingus(int maxX, int maxY) {
        // intialize randomly the Dingus properties, i.e., position and color
        super(maxX, maxY);
        // initialize randomly the CircleDingus properties, i.e., radius and filledness
        radius = random.nextInt(maxX/4);
        filled = random.nextBoolean();
    }

    @Override
    void draw(Graphics2D g2d) {
        g2d.setColor(color);
        if (filled)
            g2d.fillArc(x, y, radius, radius,0, 360);
        else
            g2d.drawArc(x, y, radius, radius,0, 360);
    }
}
