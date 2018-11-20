/** Main class for HA Random Artist
 * can be used unchanged in most cases
 *
 * @author Roy van Schaijk (1314599)
 * @author Luc Geven (1336029)
 * @author kees
 * @author huub
 */

package Week5;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class RandomArtist {
    JFrame frame;
    Painting painting; // panel that provides the random painting
    JButton regenerateButton;
    JButton shotButton;

    RandomArtist() {
        // invokeLater: preferred way to create components
        SwingUtilities.invokeLater( new Runnable() {
            @Override
            public void run() {
                painting = new Painting();
                frame = new JFrame("Computer Assisted Random Artist");
                frame.add(painting, BorderLayout.CENTER);
                regenerateButton = new JButton("Regenerate");
                regenerateButton.addActionListener(painting); // painting provides reaction to buttonclick
                frame.add(regenerateButton, BorderLayout.SOUTH);
                shotButton = new JButton("Screenshot");
                shotButton.addActionListener(painting);
                frame.add(shotButton, BorderLayout.NORTH);
                frame.pack();
                painting.regenerate(); // can be done here since painting has a size!
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        } );
    }

    public static void main(String[] arg) {
        new RandomArtist();
    }
}
