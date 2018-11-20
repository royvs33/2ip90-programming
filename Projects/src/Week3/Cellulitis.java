import java.util.Scanner;

/**
 * Cellulitis
 * by Roy van Schaijk (1314599)
 * and Luc Geven (1336029)
 */
public class Cellulitis {

    //Array to store the current generation
    boolean[] currentGeneration;
    //Intermediate array that it copied at the end of each generation to currentGeneration
    boolean[] newGeneration;
    //Holds the values of the rules, more info at method newCellValueByRules()
    boolean[] ruleSet;
    //Initialize Scanner
    Scanner scanner = new Scanner(System.in);
    //String that holds the automata (A, B or U)
    String automaton;
    //Integer that holds the amount of generations to calculate/print
    int generationsCount;

    /**
     * Read the and store:
     * - The automaton type (A, B or U)
     * - The length of a row (and stored by initializing the boolean[])
     * - The amount of generations to compute and print
     */
    public void readGeneral() {
        automaton = scanner.next();
        currentGeneration = new boolean[scanner.nextInt()];
        generationsCount = scanner.nextInt();
    }

    /**
     * Read the initial cells by indexes that are given (starting at 1).
     * Needs to throw away out of bound indexes.
     * The given cells need to be set to true, others to false
     */
    public void readInitial() {
        if (scanner.next().equals("init_start")) {
            while (scanner.hasNextInt()) {
                //make the index start at 0 https://i.redd.it/bpnzu5yy126z.png
                int index = scanner.nextInt() - 1;
                //check if index is valid
                if (index < currentGeneration.length) {
                    //set the cell to true
                    currentGeneration[index] = true;
                }
            }
            //to capture init_end
            scanner.next();
        }
    }

    /**
     * Read the rules for automaton U.
     * Input is given by 7 booleans in a row (0 or 1).
     * This is stored inside the ruleSet.
     */
    public void readRules() {
        //store if each rule 0-7 is true or false
        ruleSet = new boolean[8];
        for (int i = 0; i < 8; i++) {
            ruleSet[i] = scanner.nextInt() == 1;
        }
    }

    /**
     * Calculate the new cell value based on automata A.
     * Rules are:
     * If occupied, only stays occupied if exactly one neighbour is occupied.
     * If empty, only stays empty if both neighbours are empty.
     *
     * @param k index of cell we need to calculate a new value for.
     * @return Cell value for next generation.
     */
    boolean newCellValueByA(int k) {
        //if currently occupied
        if (currentGeneration[k]) {
            if (k == 0) { //if first cell
                //only one neighbour possible, so true/false depends on that one
                if (k == currentGeneration.length - 1) { //if only 1 cell
                    return false;
                } else {
                    return currentGeneration[k + 1];
                }
            } else if (k == currentGeneration.length - 1) { //if last cell
                //only one neighbour possible, so true/false depends on that one
                return currentGeneration[k - 1];
            } else {
                //true if not equal but at least one is true (so exactly one neighbour occupied)
                return !(currentGeneration[k - 1] && currentGeneration[k + 1])
                        && (currentGeneration[k - 1] || currentGeneration[k + 1]);
            }
        } else { //currently empty
            if (k == 0) { //if first cell
                //only one neighbour  possible, so true/false depends on that one
                if (k == currentGeneration.length - 1) { //if only 1 cell
                    return false;
                } else {
                    return currentGeneration[k + 1];
                }
            } else if (k == currentGeneration.length - 1) { //if last cell
                //only one neighbour possible, so true/false depends on that one
                return currentGeneration[k - 1];
            } else {
                //only false if they are both are false, no none of the 2 is true
                return currentGeneration[k - 1] || currentGeneration[k + 1];
            }
        }
    }

    /**
     * Calculate the new cell value based on automata B.
     * Rules are:
     * If occupied, only stays occupied if right neighbour is empty.
     * If empty, occupied if exactly one neighbour is occupied.
     *
     * @param k index of cell we need to calculate a new value for.
     * @return Cell value for next generation.
     */
    boolean newCellValueByB(int k) {
        //if currently occupied
        if (currentGeneration[k]) {
            if (k == currentGeneration.length - 1) { //if last cell
                //no right neighbour, so always true
                return true;
            } else {
                //only true if right neighbour is false
                return !currentGeneration[k + 1];
            }
        } else { //currently empty
            if (k == 0) { //if first cell
                //only one neighbour possible, so true/false depends on that one
                if (k == currentGeneration.length - 1) { //if only 1 cell
                    return false;
                } else {
                    return currentGeneration[k + 1];
                }
            } else if (k == currentGeneration.length - 1) { //if last cell
                //only one neighbour possible, so true/false depends on that one
                return currentGeneration[k - 1];
            } else {
                //true if not equal but at least one is true (so exactly one neighbour occupied)
                return !(currentGeneration[k - 1] && currentGeneration[k + 1])
                        && (currentGeneration[k - 1] || currentGeneration[k + 1]);
            }
        }
    }

    /**
     * Calculate the new cell value based on the current generation and automata U (the ruleSet variable)
     * The eight indexes on ruleSet correspond to this list:
     * 0: 000
     * 1: 001
     * 2: 010
     * 3: 011
     * 4: 100
     * 5: 101
     * 6: 110
     * 7: 111
     * The binary numbers correspond to the neighbourhood of a cell;
     * the cell to his left, his own cell and the cell to his right respectively.
     * The binary value is converted to an index and the value of the index corresponds to
     * the output that should result when this situation is encountered.
     *
     * @param k index of cell we need to calculate a new value for.
     * @return Cell value for next generation.
     */
    boolean newCellValueByRules(int k) {
        int index = 0;

        //left neighbour
        if (k != 0 && currentGeneration[k - 1]) {
            index += 4;
        }

        //current cell
        if (currentGeneration[k]) {
            index += 2;
        }

        //right neighbour
        if (k != currentGeneration.length - 1 && currentGeneration[k + 1]) {
            index += 1;
        }

        //return true/false based on what the ruleSet defined as output
        return ruleSet[index];
    }

    /**
     * Draw the current generation on the console
     */
    public void draw() {
        for (boolean pos : currentGeneration) {
            if (pos) {
                System.out.print('*');
            } else {
                System.out.print(' ');
            }
        }
        System.out.println();
    }

    /**
     * Calculate the new generation and replace the current generation
     */
    public void computeNextGeneration() {
        newGeneration = currentGeneration.clone();

        for (int i = 0; i < currentGeneration.length; i++) {
            switch (automaton) {
                case "A":
                    newGeneration[i] = newCellValueByA(i);
                    break;
                case "B":
                    newGeneration[i] = newCellValueByB(i);
                    break;
                case "U":
                    newGeneration[i] = newCellValueByRules(i);
                    break;
                default:
                    break;
            }
        }

        currentGeneration = newGeneration;
    }

    /**
     * Method called to get all input, calculate the generations and show them to the user
     */
    public void run() {
        readGeneral();
        readInitial();
        if (automaton.equals("U")) {
            readRules();
        }

        draw();
        for (int generation = 1; generation < generationsCount; generation++) {
            computeNextGeneration();
            draw();
        }

    }

    /**
     * The main method that defines what to run
     */
    public static void main(String[] args) {
        (new Cellulitis()).run();
    }
}