/**
 * FancyStarDingus
 * Probably should leave out the Fancy here as well :-)
 *
 * @author Roy van Schaijk (1314599)
 * @author Luc Geven (1336029)
 * @author huub
 */

package Week5;

import java.awt.*;

class FancyStarDingus extends Dingus {
    private int x2; // X coordinate of the top vertex of 1st triangle (x is the bottom left vertex)
    private int x3; // X coordinate of the bottom right vertex of the 1st triangle
    private int y2; // Y coordinate of the 1st triangle's top vertex
    private int y3; // Y coordinate of the 2nd triangle's 2 top vertexes
    private int y4; // Y coordinate of the 2nd triangle's bottom vertex

    public FancyStarDingus(int maxX, int maxY) {
        // intialize randomly the Dingus properties, i.e., position and color
        super(maxX, maxY);

        //6 points 3 x coordinates, 4 y coordinates
        // The length of each edge of the triangle (equilateral)
        int size = 1 + random.nextInt(200);
        x2 = x + size / 2;
        x3 = x + size;

        y2 = y - (int) (Math.sqrt(3) * size / 2);
        y3 = y2 + (y - y2) / 3;
        y4 = y + (y - y2) / 3;
    }

    @Override
    void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillPolygon(new int[]{x, x2, x3}, new int[]{y, y2, y}, 3); // Draws a filled triangle
        g2d.fillPolygon(new int[]{x, x2, x3}, new int[]{y3, y4, y3}, 3); // Draws a filled triangle
    }
}
