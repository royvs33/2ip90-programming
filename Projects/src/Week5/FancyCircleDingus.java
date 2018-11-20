/**
 * FancyCircleDingus
 * A more modern CircleDingus
 *
 * @author Roy van Schaijk (1314599)
 * @author Luc Geven (1336029)
 * @author huub
 */

package Week5;

import java.awt.*;

class FancyCircleDingus extends Dingus {
    protected int radius;
    protected boolean filled; //true: filled, false: outline
    protected GradientPaint gradientPattern;

    public FancyCircleDingus(int maxX, int maxY) {
        // intialize randomly the Dingus properties, i.e., position and color
        super(maxX, maxY);
        // initialize randomly the CircleDingus properties, i.e., radius and filledness
        radius = random.nextInt(maxX/6) + 1;
        filled = random.nextBoolean();

        //override x and wide with respect to the radius because it is ugly if it falls outside of the window
        x = radius + random.nextInt(maxX - 2*radius);
        y = radius + random.nextInt(maxY - 2*radius);

        //generate random direction and width of the gradient, and paint this gradient over the current one
        Color transparent = new Color(0, 0, 0, 0);
        Color darker = new Color(0, 0, 0, random.nextInt(150));
        gradientPattern = new GradientPaint(x + random.nextInt(radius), y + random.nextInt(radius), transparent, x + random.nextInt(radius), y + random.nextInt(radius), darker);
    }

    @Override
    void draw(Graphics2D g2d) {

        g2d.setPaint(color);
        if (filled) {
            g2d.fillArc(x, y, radius, radius,0, 360);
        } else {
            g2d.drawArc(x, y, radius, radius,0, 360);
        }

        //draw the gradient pattern over the circle
        g2d.setPaint(gradientPattern);
        if (filled) {
            g2d.fillArc(x, y, radius, radius,0, 360);
        } else {
            g2d.drawArc(x, y, radius, radius,0, 360);
        }
    }
}
