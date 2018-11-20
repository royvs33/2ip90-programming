/**
 * Assignment 6 -- Prisoner's Dilemma -- 2ip90
 * part PlayingField
 * 
 * @author Aidan Bundel
 * @author Linde van Beers
 * assignment group 21
 * 
 * assignment copyright Kees Huizing
 */

//import all necessary packages
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.*;
import javax.swing.SwingUtilities;
import java.awt.*;
import java.awt.event.*;

class FieldPanel extends JPanel {
    //Set the amount of patches in the x and y direction
    private static int PATCH_SIZE_X = 60;
    private static int PATCH_SIZE_Y = 60;
   
    //Define the grid of patches
    private Patch[][] grid;
    
    
    private double alpha; //Defection award factor
     
    //Make a timer
    private Timer timer;
    
    //Random number genrator
    private static final long SEED = 37; // seed for random number generator; any number goes
    public static final Random random = new Random(SEED);    
    
    //Make JPanel
    private JPanel panel;
    
    //private ArrayList<Boolean> neighbours;

    public FieldPanel() {
        //Initialize panel
        panel = new JPanel();
        //Set dimensions of panel
        panel.setPreferredSize(new Dimension(PATCH_SIZE_X * 10, PATCH_SIZE_Y * 10));
        
        //Initialize the grid with the right dimensions
        grid = new Patch[PATCH_SIZE_X][PATCH_SIZE_Y];
        
        //Fill the grid with patches with random booleans for cooperating or not and store their neighbours
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                grid[x][y] = new Patch(random.nextBoolean(), x, y, PATCH_SIZE_X, PATCH_SIZE_Y);                
                grid[x][y].storeNeighbours();
            }
        }
        
        //Initialize timer
        timer = new Timer(10000, new ActionListener() {
            //Perform a step every time the timer fires
            public void actionPerformed(ActionEvent e) {
                step();
            }
        });
        //Start the timer
        timer.start();
        
        //Initialize alpha at 1.2 to match the inuitial value of the slider
        setAlpha(1.2);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        // clear window
        super.paintComponent(g);
        
        //Starting position for painting
        int xPosition = 1;
        int yPosition = 1;
        
        //Paint every patch in the grid
        for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid.length; j++) {
                //Make the colour blue if the patch is cooperating and red if not
                if (grid[i][j].isCooperating()) {
                    g.setColor(Color.BLUE);
                } else {
                    g.setColor(Color.RED);
                }
                //Draw the patch
                g.fillOval(xPosition, yPosition, 9, 9);
                
                //Go to the location for the next patch in the row
                xPosition += 10;
            }
            //Go back to the first position in the row
            xPosition = 1;
            //Go to the next row;
            yPosition += 10;
        }
    }
    
  
    //Calculate and execute one step in the simulation 
    public void step() {
        
        //Calculate the score of each patch
        calculateScore();
        
        //Get the boolean-cooperating information about all patches in the grid
        boolean[][] booleanGrid = getGrid();
        //create an other grid to store results in
        boolean[][] resultGrid = getGrid();
        //For every patch, see what color they should become
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                //Import the neighbours of the patch
                ArrayList<int[]> neighbours = grid[x][y].getNeighbours();
                //variable to store the highest score encountered so far in the neighbours
                double highestScore = 0;
                //variable to score the cooperation-boolean of the highest scoring neighbour so far
                boolean highestScoreGetter = false;
                
                //Look through all neighbours to find the highest scoring one
                for (int i = 0; i < neighbours.size(); i++) {
                    
                    int[] index = neighbours.get(i);
                    
                    //compare the score of a neighbour to the current highest scoring one
                    if ( grid[index[0]][index[1]].getScore() > highestScore) {
                        //Set highestScore to current neighbour if he scored higher
                        highestScore = grid[index[0]][index[1]].getScore();
                        //and set highestScoreGetter to the boolean value of that neighbour
                        highestScoreGetter = booleanGrid[index[0]][index[1]];
                    }
                }
                //Set current patch cooperation to that of the highest score getter of the neighbours.
                resultGrid[x][y]=highestScoreGetter;
            }
        }
        //Set the grid with the right cooperation values
        setGrid(resultGrid);
        //Repaint the grid
        repaint();
    }
    
    //Calculate the score of all patches
    public void calculateScore() {
        
        //Walk through all the patches
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                 //Variable to count the amount of neighbours that are cooperating
                int count = 0;
                
                //Variable to store the score of the patch
                double score;
                
                //Find out whether current patch is cooperating or not
                boolean cooperating = grid[x][y].isCooperating();
                
                //Import the list of neighbours of current patch
                ArrayList<int[]> neighbours = grid[x][y].getNeighbours();
                
                //Count all cooperating neighbours
                for (int i = 0; i < neighbours.size(); i++) {
                    int[] index = neighbours.get(i);
                    if (grid[index[0]][index[1]].isCooperating()) {
                        count++;
                    }    
                }
                
                //Depending on whether you are cooperating or not, set the score
                if (cooperating) {
                    //Score is the amount of neighbours cooperating
                    //-1 because we also counted ourselves
                    score = count - 1;
                } else {
                    //Score is the amount of neighbours cooperating times the defection award factor
                    score = count * alpha;
                }
                
                //Set the score of the current patch
                grid[x][y].setScore(score);
            }
        }
    }
    
    //Sets the defection award factor
    public void setAlpha(double a) {
        alpha = a;
    }
    
    //Returns the defection award factor
    public double getAlpha() {
        return alpha;
    }

    // sets grid according to parameter inGrid
    // a patch should become cooperating if the corresponding
    // item in inGrid is true
    public void setGrid( boolean[][] inGrid) {
        for (int x = 0; x < grid.length; x++ ) {
            for (int y = 0; y < grid[x].length; y++ ) {
                //Set cooperating to the right boolean
                grid[x][y].setCooperating(inGrid[x][y]);
            }
        }
    }
    
    
    
    // return grid as 2D array of booleans
    // true for cooperators, false for defectors
    // precondition: grid is rectangular, has non-zero size and elements are non-null
    public boolean[][] getGrid() {
        
        boolean[][] resultGrid = new boolean[grid[0].length][grid.length];
        
        for (int x = 0; x < grid.length; x++ ) {
          for (int y = 0; y < grid[0].length; y++ ) {
              resultGrid[x][y] = grid[x][y].isCooperating();
          }
        }
        
        return resultGrid; 
    }
}

