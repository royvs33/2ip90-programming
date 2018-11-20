/**
 * Name: Roy van Schaijk
 * Student ID: 1314599
 */

import java.util.*;

/**
 * MadTrucker
 */
public class MadTrucker {
    Scanner sc = new Scanner(System.in);
    int[] originalCans;
    int[] optimizedCans;
    int[] noStopPlaces;

    /**
     * Main method
     */
    void run() {
        int amountOfCans = sc.nextInt();
        originalCans = new int[amountOfCans];
        for (int i = 0; i < originalCans.length; i++) {
            originalCans[i] = sc.nextInt();
        }
        noStopPlaces = new int[amountOfCans - 1];
        for (int i = 0; i < noStopPlaces.length; i++) {
            noStopPlaces[i] = sc.nextInt();
        }
        optimizeCans();
        int[] workingSequence = trySequences(new int[0]);

        if (workingSequence == null) {
            System.out.println("This does not seem to work, but this shouldn't happen, ever");
        } else {
            for (int i = 0; i < workingSequence.length; i++) {
                System.out.print(indexOf(originalCans, workingSequence[i]));
                //if last one no space after
                if (i != workingSequence.length - 1) {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }

    /**
     * Recursive method to try all cans
     *
     * @param usedCans currently used cans
     * @return valid sequence
     */
    int[] trySequences(int[] usedCans) {
        int currentLocation = 0;
        for (int can : usedCans) {
            currentLocation += can;
        }
        boolean lastCan = true;
        for (int i = 0; i < optimizedCans.length; i++) {
            //only use unused cans
            if (!arrayContains(usedCans, optimizedCans[i])) {
                lastCan = false;
                int newLocation = currentLocation + optimizedCans[i];
                //check if not a noStop place
                if (!arrayContains(noStopPlaces, newLocation)) {
                    int[] newUsedCansIndices = arrayAdd(usedCans, optimizedCans[i]);
                    int[] newSequence = trySequences(newUsedCansIndices);
                    if (newSequence != null) {
                        //return if sequence is valid
                        return newSequence;
                    }
                }
            }
        }

        if (lastCan) {
            //return if all cans used (so finished)
            return usedCans;
        }
        return null;
    }

    /**
     * Optimize cans order
     */
    void optimizeCans() {
        //make a new version of the array
        optimizedCans = arrayClone(originalCans);
        //sort from low to high
        Arrays.sort(optimizedCans);
        //sort the noStopPlaces for easier use
        Arrays.sort(noStopPlaces);
    }


    /**
     * Check if array contains item
     *
     * @param array array to check in
     * @param item item to test for
     * @return true or false
     */
    public static boolean arrayContains(int[] array, int item) {
        for (int i : array) {
            if (i == item) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add item to array at position
     *
     * @param array array to add item to
     * @param item item to add
     * @param location the position where it should be added
     * @return new array
     */
    public static int[] arrayAdd(int[] array, int item, int location) {
        int[] newArray = new int[array.length + 1];
        newArray[location] = item;
        for (int i = 0; i < array.length; i++) {
            if (i < location) {
                newArray[i] = array[i];
            } else {
                newArray[i + 1] = array[i];
            }
        }
        return newArray;
    }

    /**
     * Clone array
     *
     * @param array array to clone
     * @return new array
     */
    public static int[] arrayClone(int[] array) {
        int[] newArray = new int[array.length];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }

    /**
     * Add item to array at last position
     *
     * @param array array to add item to
     * @param item item to add
     * @return new array
     */
    public static int[] arrayAdd(int[] array, int item) {
        return arrayAdd(array, item, array.length);
    }

    /**
     * Search array for index
     *
     * @param array array to clone
     * @param item item to find
     * @return new array
     */
    public static int indexOf(int[] array, int item) {
        for(int i = 0; i < array.length; i++) {
            if(array[i] == item) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Log array to console
     *
     * @param array array to print
     */
    public static void logArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            //if last one no space after
            if (i != array.length - 1) {
                System.out.print(' ');
            }
        }
        System.out.println();
    }

    /**
     * Initialization method
     *
     * @param args the arguments given to the class constructor
     */
    public static void main(String[] args) {
        (new MadTrucker()).run();
    }
}