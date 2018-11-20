/**
 * FancyLineDingus
 * An arguably ugly Dingus
 * @author Roy van Schaijk (1314599)
 * @author Luc Geven (1336029)
 * @author huub
 */

package Week5;

import java.awt.*;
import java.awt.geom.GeneralPath;

class FancyLineDingus extends Dingus {
    int[] startCoords;

    int[] coords1;
    int[] coords2;
    int[] coords3;

    public FancyLineDingus(int maxX, int maxY) {
        // intialize randomly the Dingus properties, i.e., position and color
        super(maxX, maxY);

        startCoords = new int[] { random.nextInt(maxX), random.nextInt(maxY) };

        coords1 = new int[] { random.nextInt(maxX), random.nextInt(maxY), random.nextInt(maxX), random.nextInt(maxY) };
        coords2 = new int[] { random.nextInt(maxX), random.nextInt(maxY), random.nextInt(maxX), random.nextInt(maxY) };
        coords3 = new int[] { random.nextInt(maxX), random.nextInt(maxY), startCoords[0], startCoords[1] };
    }

    @Override
    void draw(Graphics2D g2d) {
        g2d.setColor(color);
        GeneralPath shape = new GeneralPath();
        shape.moveTo(startCoords[0], startCoords[1]);
        shape.quadTo(coords1[0], coords1[1], coords1[2], coords1[3]);
        shape.quadTo(coords2[0], coords2[1], coords2[2], coords2[3]);
        shape.quadTo(coords3[0], coords3[1], coords3[2], coords3[3]);
        g2d.draw(shape);
    }
}
