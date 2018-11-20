/**
 * Assignment 6 -- Prisoner's Dilemma -- 2ip90
 * part Patch
 * 
 * @author Linde van Beers
 * @author Aidan Bundel
 * assignment group 21
 * 
 * assignment copyright Kees Huizing
 */

//import necessary package
import java.util.*;

class Patch {
    private boolean strategy; //strategy boolean
    private int locationX; //patch x location
    private int locationY; //patch y location
    private ArrayList<int[]> neighbours; //to store the patch's neigbours
    private int xSize; //number of grid columns
    private int ySize; //number of grid rows
    private double score; //score of the patch
    
    public Patch(boolean s, int x, int y, int xPatches, int yPatches) {
        //initialise variables in the constructor
        strategy = s;
        locationX = x;
        locationY = y;
        xSize = xPatches;
        ySize = yPatches;
    }
    
    public void storeNeighbours() {
        neighbours = new ArrayList<int[]>(); //initialise neighbours as an arraylist of int arrays
        int xNeighbour; //x position of the neighbour
        int yNeighbour; //y position of the neighbour
        
        //loop through the 8 neighbours 
        for (int x = locationX - 1; x < locationX + 2; x++) {
            for (int y = locationY - 1; y < locationY + 2; y++) {
                if (x == -1) { //if left neighbour = -1, take the rightmost patch in the row instead
                    xNeighbour =  xSize - 1;
                } else { //use modulo operator to take the leftmost patch of the row again
                    xNeighbour = x % xSize; 
                }
                if ( y == -1) { //if top neighbour = -1, take the last patch in the column instead
                    yNeighbour = ySize - 1;
                } else { //use modulo operator to take the leftmost patch of the column again
                    yNeighbour = y % ySize;
                }
                neighbours.add(new int[] {xNeighbour, yNeighbour}); //add the x and y values to the neighbours list
            }
        }
    }
    
    //get the list of neighbours
    public ArrayList<int[]> getNeighbours() {
        return neighbours;
    }
    
    //returns true if and only if patch is cooperating
    boolean isCooperating() {
        if (strategy) {
            return true;
        } else {
            return false;
        }
    }
    
    //set strategy to C if is C is true and to D if false
    void setCooperating(boolean coop) {
        if (coop) {
            strategy = true;
        } else {
            strategy = false;
        }
    }
    
    //change strategy from C to D and vice versa
    void toggleStrategy() {
        if (strategy) {
            strategy = false;
        } else {
            strategy = true;
        }
    }
    
    //set the score
    void setScore(double s) {
        score = s;
    }
    
    //return score of this patch in current round
    double getScore() {
        return score;
    }
}
