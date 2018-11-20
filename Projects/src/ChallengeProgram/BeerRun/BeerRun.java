/**
 * Name: Roy van Schaijk
 * Student ID: 1314599
 */

import java.util.*;

/**
 * BeerRun is a program that calculates the maximum score that is possible
 * to achieve given a grid with values and rules about the allowed moves.
 */
public class BeerRun {
    Scanner sc = new Scanner(System.in);

    /**
     * Main method of Beer Run that chooses which part is going to run
     */
    void run() {
        //double next to make sure that input all on the same line is parsed correctly
        //(that's what the Momotor tests like to do)
        String part = sc.next() + " " + sc.next();
        switch (part) {
            case "Part 1":
                partOne();
                break;
            case "Part 2":
                partTwo();
                break;
            case "Part 3":
                partThree();
                break;
        }
    }

    /**
     * Part 1, which starts left and moves NE, E, SE
     * Approach, calculate the highest score in pairs of 2, starting with the last
     * Example:
     * 1 3 4
     * 7 2 1
     * 4 1 8
     * start with
     * 3 4
     * 2 1
     * 1 8
     * best for each
     * 3 + 4 = 7
     * 2 + 8 = 10
     * 1 + 8 = 9
     * you now have
     * 1 7
     * 7 10
     * 4 9
     * this makes
     * 1 + 10 = 11
     * 7 + 10 = 17
     * 4 + 10 = 14
     * best option: 17
     */
    void partOne() {
        int[][] grid = getGrid();

        //Start with second last column
        for (int col = grid.length - 2; col >= 0; col--) {
            for (int row = 0; row < grid[col].length; row++) {
                if (row == 0) { // if row is on top, only use column E, SE
                    grid[col][row] += max(grid[col + 1][row], grid[col + 1][row + 1]);
                } else if (row == grid[col].length - 1) { // if row is on bottom, only use column NE, E
                    grid[col][row] += max(grid[col + 1][row - 1], grid[col + 1][row]);
                } else { //else use all directions (NE, E, SE)
                    grid[col][row] += max(grid[col + 1][row - 1], grid[col + 1][row], grid[col + 1][row + 1]);
                }
            }
        }
        //Get maximum value out of the first column
        int max = maxArr(grid[0]);
        System.out.println(max);
    }

    /**
     * Part 1, which specifies the moves that are possible
     */
    void partTwo() {
        int[][] initialGrid = getGrid();
        //duplicate grid
        int[][] currentGrid = copyGrid(initialGrid);
        //get number of directions
        int amtOfDirs = sc.nextInt();
        //create string array with directions
        char[] directions = new char[amtOfDirs];
        //input the directions
        for (int i = 0; i < amtOfDirs; i++) {
            directions[i] = sc.next().charAt(0);
        }

        //calculate a new grid with the best options and start at the last direction, and work back from there
        for (int i = directions.length - 1; i >= 0; i--) {
            currentGrid = calcNewGridWithDirection(initialGrid, currentGrid, directions[i]);
        }

        //get the highest value out of the grid
        int max = maxArr(currentGrid[0]);
        for (int i = 1; i < currentGrid.length; i++) {
            if (maxArr(currentGrid[i]) > max) {
                max = maxArr(currentGrid[i]);
            }
        }

        System.out.println(max);

    }

    /**
     * Calc new grid according to Part 2
     *
     * @param initialGrid - The original grid from input
     * @param addToGrid   - The current state of the grid
     * @param direction   - The direction in which you are allowed to go
     * @return A grid with the maximum score for each starting point
     */
    int[][] calcNewGridWithDirection(int[][] initialGrid, int[][] addToGrid, char direction) {
        int[][] currentGrid = copyGrid(initialGrid);
        int columns = initialGrid.length;
        int rows = initialGrid[0].length;

        for (int colIndex = 0; colIndex < columns; colIndex++) {
            for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
                switch (direction) {
                    case 'N': {
                        if (rowIndex == 0) { //first row, so not possible, using 0 for that
                            currentGrid[colIndex][rowIndex] = 0;
                        } else if (colIndex == 0) { //first column, so NW not possible
                            currentGrid[colIndex][rowIndex] += max(addToGrid[colIndex][rowIndex - 1], addToGrid[colIndex + 1][rowIndex - 1]);
                        } else if (colIndex == (columns - 1)) { //last column, so NE not possible
                            currentGrid[colIndex][rowIndex] += max(addToGrid[colIndex - 1][rowIndex - 1], addToGrid[colIndex][rowIndex - 1]);
                        } else { // NW, N & NE possible
                            currentGrid[colIndex][rowIndex] += max(addToGrid[colIndex - 1][rowIndex - 1], addToGrid[colIndex][rowIndex - 1], addToGrid[colIndex + 1][rowIndex - 1]);
                        }
                        break;
                    }
                    case 'E': {
                        if (colIndex == (columns - 1)) { //last column, so not possible, using 0 for that
                            currentGrid[colIndex][rowIndex] = 0;
                        } else if (rowIndex == 0) { //first row, so NE not possible
                            currentGrid[colIndex][rowIndex] += max(addToGrid[colIndex + 1][rowIndex], addToGrid[colIndex + 1][rowIndex + 1]);
                        } else if (rowIndex == (rows - 1)) { //last row, so SE not possible
                            currentGrid[colIndex][rowIndex] += max(addToGrid[colIndex + 1][rowIndex - 1], addToGrid[colIndex + 1][rowIndex]);
                        } else { // NE, E & SE possible
                            currentGrid[colIndex][rowIndex] += max(addToGrid[colIndex + 1][rowIndex - 1], addToGrid[colIndex + 1][rowIndex], addToGrid[colIndex + 1][rowIndex + 1]);
                        }
                        break;
                    }
                    case 'S': {
                        if (rowIndex == (rows - 1)) { //last row, so not possible, using 0 for that
                            currentGrid[colIndex][rowIndex] = 0;
                        } else if (colIndex == 0) { //first column, so SW not possible
                            currentGrid[colIndex][rowIndex] += max(addToGrid[colIndex][rowIndex + 1], addToGrid[colIndex + 1][rowIndex + 1]);
                        } else if (colIndex == (columns - 1)) { //last column, so SE not possible
                            currentGrid[colIndex][rowIndex] += max(addToGrid[colIndex - 1][rowIndex + 1], addToGrid[colIndex][rowIndex + 1]);
                        } else { // SW, S & SE possible
                            currentGrid[colIndex][rowIndex] += max(addToGrid[colIndex - 1][rowIndex + 1], addToGrid[colIndex][rowIndex + 1], addToGrid[colIndex + 1][rowIndex + 1]);
                        }
                        break;
                    }
                    case 'W': {
                        if (colIndex == 0) { //first column, so not possible, using 0 for that
                            currentGrid[colIndex][rowIndex] = 0;
                        } else if (rowIndex == 0) { //first row, so NW not possible
                            currentGrid[colIndex][rowIndex] += max(addToGrid[colIndex - 1][rowIndex], addToGrid[colIndex - 1][rowIndex + 1]);
                        } else if (rowIndex == (rows - 1)) { //last row, so SW not possible
                            currentGrid[colIndex][rowIndex] += max(addToGrid[colIndex - 1][rowIndex - 1], addToGrid[colIndex - 1][rowIndex]);
                        } else { // NW, W & SW possible
                            currentGrid[colIndex][rowIndex] += max(addToGrid[colIndex - 1][rowIndex - 1], addToGrid[colIndex - 1][rowIndex], addToGrid[colIndex - 1][rowIndex + 1]);
                        }
                        break;
                    }
                }
            }
        }

        return currentGrid;
    }

    /**
     * Third part, calculates the best way in all possible directions
     */
    void partThree() {
        //ask the user to input the initial grid
        int[][] initialGrid = getGrid();
        //ask the maximum amount of steps
        int amountOfSteps = sc.nextInt();

        int max = 0;
        for (int columnIndex = 0; columnIndex < initialGrid.length; columnIndex++) {
            for (int rowIndex = 0; rowIndex < initialGrid[0].length; rowIndex++) {
                //calculate the best possible score for each starting point
                max = max(max, calcMoves(amountOfSteps, initialGrid, columnIndex, rowIndex));
            }
        }
        System.out.println(max);
    }

    /**
     * A recursive function that calculates the best possible direction to go,
     * starting from the end and working back from there.
     *
     * @param moves - The amount of moves allowed (length of recursion)
     * @param grid  - The grid as inputted
     * @param posX  - Starting position X
     * @param posY  - Starting position Y
     * @return The maximum score possible
     */
    int calcMoves(int moves, int[][] grid, int posX, int posY) {
        if (moves == 1) {
            int bestMove = 0;
            for (int direction = 0; direction < 8; direction++) {
                if (movePossible(direction, grid, posX, posY)) {
                    bestMove = max(bestMove, grid[getXMove(direction, posX)][getYMove(direction, posY)]);
                }
            }
            return grid[posX][posY] + bestMove;
        } else {
            int bestMove = 0;
            for (int direction = 0; direction < 8; direction++) {
                if (movePossible(direction, grid, posX, posY)) {
                    int[][] gridCopy = copyGrid(grid);
                    gridCopy[posX][posY] = 0;
                    int move = calcMoves(moves - 1, gridCopy, getXMove(direction, posX), getYMove(direction, posY));
                    bestMove = max(bestMove, grid[posX][posY] + move);
                }
            }
            return bestMove;
        }
    }

    /**
     * Check if a move is possible in the grid
     *
     * @param moveIndex - The index of the move
     * @param grid      - The grid on which the move needs to be checked
     * @param posX      - X starting position of the move
     * @param posY      - Y starting position of the move
     * @return Boolean that shows if a move is possible
     */
    private static boolean movePossible(int moveIndex, int[][] grid, int posX, int posY) {
        switch (moveIndex) {
            case 0: //NW
                return posY != 0 && posX != 0;
            case 1: //N
                return posY != 0;
            case 2: //NE
                return posY != 0 && posX != grid.length - 1;
            case 3: //E
                return posX != grid.length - 1;
            case 4: //SE
                return posY != grid[0].length - 1 && posX != grid.length - 1;
            case 5: //S
                return posY != grid[0].length - 1;
            case 6: //SW
                return posY != grid[0].length - 1 && posX != 0;
            case 7: //W
                return posX != 0;
        }
        return true;
    }

    /**
     * get the new X coordinate according to the moveIndex
     *
     * @param moveIndex - the index of the move
     * @param posX      - X starting position of the move
     * @return the new X coordinate
     */
    private static int getXMove(int moveIndex, int posX) {
        if (moveIndex >= 2 && moveIndex <= 4) { //NE|E|SE
            return posX + 1;
        } else if (moveIndex == 1 || moveIndex == 5) { //N|S
            return posX;
        } else { //NW|SW|W
            return posX - 1;
        }
    }

    /**
     * get the new Y coordinate according to the moveIndex
     *
     * @param moveIndex - the index of the move
     * @param posY      - Y starting position of the move
     * @return the new Y coordinate
     */
    private static int getYMove(int moveIndex, int posY) {
        if (moveIndex <= 2) { //NW|N|NE
            return posY - 1;
        } else if (moveIndex == 3 || moveIndex == 7) { //E|W
            return posY;
        } else { //SE|S|SW
            return posY + 1;
        }
    }

    /**
     * Get grid input and return 2 dimensional array
     *
     * @return the grid as on input
     */
    private int[][] getGrid() {
        //get grid size
        int cols = sc.nextInt();
        int rows = sc.nextInt();

        int[][] grid = new int[cols][rows];

        for (int rowcnt = 0; rowcnt < rows; rowcnt++) {
            for (int colcnt = 0; colcnt < cols; colcnt++) {
                grid[colcnt][rowcnt] = sc.nextInt();
            }
        }

        return grid;
    }

    /**
     * Make a real "Copy" of the grid, because java sucks
     *
     * @param grid The grid to be copied
     * @return New grid with same data
     */
    private static int[][] copyGrid(int[][] grid) {
        int[][] copyGrid = new int[grid.length][grid[0].length];
        for (int i = 0; i < copyGrid.length; i++) {
            copyGrid[i] = grid[i].clone();
        }
        return copyGrid;
    }

    /**
     * convert arguments to array and run the maxArray
     *
     * @param n integers of which the maximum value needs to be retrieved
     * @return maximum value
     */
    private static int max(int... n) {
        return maxArr(n);
    }

    /**
     * calc max value out of all ints in array
     *
     * @param array Array out of which max value needs to be returned
     * @return maximum value
     */
    private static int maxArr(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    /**
     * Initialization method
     *
     * @param args the arguments given to the class constructor
     */
    public static void main(String[] args) {
        BeerRun instance = new BeerRun();
        instance.run();
    }
}