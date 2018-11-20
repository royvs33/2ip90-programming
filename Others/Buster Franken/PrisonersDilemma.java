/**
 * Assignment 6 -- Prisoner's Dilemma -- 2ip90
 * main class
 * 
 * @author Linde van Beers
 * @author Aidan Bundel
 * assignment group 21
 * 
 * assignment copyright Kees Huizing
 */

//import necessary packages
import javax.swing.SwingUtilities;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import javax.swing.event.*;

class PrisonersDilemma extends JPanel {
    JFrame frame;
    FieldPanel playingField;
    JSlider alphaSlider;
    double input;
    
    void buildGUI() {
        SwingUtilities.invokeLater( () -> {
            playingField = new FieldPanel(); //initialise playingfield
            frame = new JFrame("Prisoner's Dilemma"); //initialise frame
            frame.setPreferredSize(new Dimension(550, 600)); //set size of the frame
            frame.add(playingField, BorderLayout.CENTER); //set location of the panel to the center of the frame
            
            alphaSlider = new JSlider(0, 30, 10); //create slider for the alpha value
            alphaSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {    
                    input = alphaSlider.getValue() / 10; //get input from slider and map to alpha values (divide by 10)
                    playingField.setAlpha(input); //set the alpha value in playingfield
                }
            });
            
            frame.add(alphaSlider, BorderLayout.SOUTH); //set location of the slider to the south of the frame
            frame.pack(); //size the frame so that all its contents are at or above their preferred sizes
            frame.setLocationRelativeTo(null); //set location of the frame to the center of window
            frame.setVisible(true); //make the frame visible
        } );
    }
    
    public static void main( String[] a ) {
        (new PrisonersDilemma()).buildGUI();
    }
}
