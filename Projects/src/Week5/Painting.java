/*
 * part of HA Random artist
 *
 * @author Roy van Schaijk (1314599)
 * @author Luc Geven (1336029)
 * @author huub
 * @author kees
 */

package Week5;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

public class Painting extends JPanel implements ActionListener {

   /*---- Randomness ----*/
    /** you can change the variable SEED if you like
     *  the same program with the same SEED will generate exactly the same sequence of pictures
     */
    final static long SEED = 30; // seed for the random number generator; any number works

    /** do not change the following two lines **/
    final static Random random = new Random( SEED ); // random generator to be used by all classes
    int numberOfRegenerates = 0;

   /*---- Screenshots ----
    * do not change
    */
    char current = '0';
    String filename = "randomshot_"; // prefix
    
   /*---- Dinguses ----*/
    ArrayList<Dingus> shapes;
    String[] possibleShapes = {
        FancyCircleDingus.class.getName(),
        FancyLineDingus.class.getName(),
        ArrowDingus.class.getName(),
        FancyStarDingus.class.getName(),
    };
    final int WINDOW_WIDTH = 800;
    final int WINDOW_HEIGHT = 450;

    public Painting() {
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT)); // make panel 800 by 450 pixels.
    }

    /**
     * Draw all shapes
     * @param g the Graphics object
     */
    @Override
    protected void paintComponent(Graphics g) {
        // Convert Graphics object to a more modern Graphics2D object
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g2d);
        // draw all shapes
        for (Dingus shape : shapes) {
            shape.draw(g2d);
        }
    }


    /**
     * reaction to button press
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if ( "Regenerate".equals(e.getActionCommand()) ) {
            regenerate();
            repaint();
        } else {
            // screenshot
            saveScreenshot( this, filename+current++ ); // ++ to show of compact code :-)
        }
    }

    /**
     * Regenerate the shapes
     */
    void regenerate() {
        numberOfRegenerates++; // do not change
        
        // clear the shapes list
        shapes = new ArrayList<>();

        // create random shapes

        //always draw a background "shape"
        shapes.add(new BackgroundDingus(WINDOW_WIDTH, WINDOW_HEIGHT));

        //get amount between 9 and 29
        int amountOfShapes = 9 + random.nextInt(20);
        for (int i = 0; i < amountOfShapes; i++) {
            shapes.add(getRandomShape());
        }
    }

    /**
     * Gets a random shape
     */
    private Dingus getRandomShape() {
        try {
            int shapeIndex = random.nextInt(possibleShapes.length);
            return (Dingus) Class.forName(possibleShapes[shapeIndex]).getConstructor(new Class[] {int.class, int.class}).newInstance(new Object[] {WINDOW_WIDTH, WINDOW_HEIGHT});
        } catch (Exception e) {
            e.printStackTrace();
            //fallback, but should never happen, otherwise the compiler is going to cry :-(
            return new CircleDingus(WINDOW_WIDTH, WINDOW_HEIGHT);
        }
    }

    /** 
     * saves a screenshot of a Component on disk
     *  overides existing files
     *
     * @param component - Component to be saved
     * @param name - filename of the screenshot, followed by a sequence number
     * 
     * do not change
     */
    void saveScreenshot(Component component, String name) {
        String randomInfo = ""+SEED+"+"+ (numberOfRegenerates-1); //minus 1 because the initial picture should not count
        System.out.println( SwingUtilities.isEventDispatchThread() );
        BufferedImage image =
            new BufferedImage(
                              component.getWidth(),
                              component.getHeight(),
                              BufferedImage.TYPE_INT_RGB
                             );
        // call the Component's paint method, using
        // the Graphics object of the image.
        Graphics graphics = image.getGraphics();
        component.paint( graphics ); // alternately use .printAll(..)
        graphics.drawString(randomInfo, 0, component.getHeight());
        
        try {
            ImageIO.write(image, "PNG", new File(name+".png"));
            System.out.println( "Saved screenshot as "+ name );
        } catch( IOException e ) {
            System.out.println( "Saving screenshot failed: "+e );
        }
    }
    
}