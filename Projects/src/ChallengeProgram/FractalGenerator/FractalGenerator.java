package ChallengeProgram.FractalGenerator;
/**
 * Challenge Program, FractalGenerator
 * main class
 *
 * @author Roy van Schaijk (1314599)
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.*;
import javax.swing.*;

public class FractalGenerator {
    private JFrame frame;
    private JMenuBar menuBar;
    private JPanel fractalPanel;

    private void buildGUI() {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Fractal Generator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1366, 720);

            menuBar = new JMenuBar();
            menuBar.add(createFileMenu());
            menuBar.add(createToolsMenu());
            menuBar.add(createFractalsMenu());
            frame.setJMenuBar(menuBar);

            fractalPanel = new PythagorasTree();
            frame.add(fractalPanel);

            frame.setVisible(true);
        });
    }

    private JMenu createFileMenu() {
        //create menu
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        //create variable for each item
        JMenuItem menuItem;


        menuItem = new JMenuItem("Load");
        menuItem.setMnemonic(KeyEvent.VK_L);
        menuItem.addActionListener(e -> {
            System.out.println("Load");
        });
        fileMenu.add(menuItem);

        menuItem = new JMenuItem("Save");
        menuItem.setMnemonic(KeyEvent.VK_S);
        menuItem.addActionListener(e -> {
            System.out.println("Save");
        });
        fileMenu.add(menuItem);

        fileMenu.addSeparator();

        menuItem = new JMenuItem("Quit");
        menuItem.setMnemonic(KeyEvent.VK_Q);
        menuItem.addActionListener(e -> {
            //close window
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        });
        fileMenu.add(menuItem);

        return fileMenu;
    }

    private JMenu createToolsMenu() {
        //create menu
        JMenu toolMenu = new JMenu("Tools");
        toolMenu.setMnemonic(KeyEvent.VK_T);
        return toolMenu;
    }

    private JMenu createFractalsMenu() {
        //create menu
        JMenu fractalMenu = new JMenu("Fractals");
        fractalMenu.setMnemonic(KeyEvent.VK_F);

        JMenuItem menuItem;

        menuItem = new JMenuItem("Fractal");
        menuItem.setMnemonic(KeyEvent.VK_1);
        menuItem.addActionListener(e -> replaceFractalPanel(new PythagorasTree()));
        fractalMenu.add(menuItem);

        return fractalMenu;
    }

    private void replaceFractalPanel(JPanel fractalPanel) {
        frame.remove(this.fractalPanel);
        frame.add(fractalPanel);
        frame.revalidate();
        frame.repaint();
    }


    public static void main(String[] a) {
        FractalGenerator fractalGenerator = new FractalGenerator();
        fractalGenerator.buildGUI();
    }
}
