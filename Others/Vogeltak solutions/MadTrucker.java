import java.util.Scanner;
import java.util.Arrays;

/**
 * Order of refuel calculator
 * @author *********
 */

class MadTrucker {
    /**
     * The scanner that is used to read from the default input.
     */
    Scanner scanner = new Scanner (System.in );
    
    /**
     * The available fule cans, sorted from small to large.
     */
    int [] fuelCan;
    
    /**
     * The places where we are not allowed to stop, sorted from small to large.
     */
    int [] invalidStop;

    /**
     * The recursive function that looks for a valid can sequence.
     * @param usedCans The cans that were already used, in order of when they were used.
     * @return Returns null if there was no valid sequence found, the sequence in which the cans were used otherwise.
     */
    int [] makeAttempt(int [] usedCans) {
        // Calculate where we are at.
        int location = 0;
        for(int can : usedCans) {
            location += can;
        }
        
        // Wherther there are any unused cans left
        boolean haveLeft = false;
        for(int can : fuelCan) {
            // If the can has not yet been used
            if(!Arrays2.in(usedCans, can)) {
                haveLeft = true;
                
                // Calculate the new location
                int loc = location + can;
                
                // Check if the new location would be valid
                if(!Arrays2.in(invalidStop, loc)) {
                    //Recursively continue to find the rest of the route
                    int [] newUsedCans = Arrays2.insert(usedCans, can);
                    int [] attempt = makeAttempt(newUsedCans);
                    
                    // If this was a valid attempt, return it
                    if(attempt != null) {
                        return attempt;
                    }
                }
            }
        }
        
        // If there are no unused cans left, return the sequence
        if(!haveLeft) {
            return usedCans;
        }
        
        // If there was not found a valid solution, return null
        return null;
    }
    
    /**
     * Finds a valid can sequence.
     */
    void run() {
        // The amount of cans
        int n = scanner.nextInt();

        // Read the fuel cans
        fuelCan = new int[n];
        for(int i = 0; i < n; i++) {
            fuelCan[i] = scanner.nextInt();
        }
        
        // Read the invalid stops
        invalidStop = new int[n-1];
        for(int i = 0; i < n-1; i++) {
            invalidStop[i] = scanner.nextInt();
        }
        
        // Keep the origional array order before sorting it
        int [] origionalCans = Arrays2.insert(fuelCan, 0);

        // Sort the cans and the stops for the algorythm to be more effective
        Arrays.sort(fuelCan);
        Arrays.sort(invalidStop);
        
        // Try to find a sequence
        int []sequence = makeAttempt(new int[0]);
        
        // Print an error when no valid sequence was found
        if(sequence == null) {
            System.out.println("Failed to find valid sequence.");
        } else {
            // Print the used cans in nth can instead of the value of the can
            for(int can : sequence) {
                for(int i = 0; i < origionalCans.length; i++) {
                    if(origionalCans[i] == can) {
                        System.out.print(i+" ");
                    }
                }
            }
        }
    }

    /**
     * Program entry point.
     */
    public static void main(String a[]) {
        // We like to run it run it
        // We like to, run it!.
        new MadTrucker().run();
    }
}

/**
 * Array utilities
 */
class Arrays2 {
    /**
     * Reverse an array.
     * @param array The array to reverse
     */
    public static void reverse(int[] arr) {
        for(int i = 0; i < arr.length/2;i++) {
            int head = arr[i];
            int tailI = arr.length-i-1;
            arr[i] = arr[tailI];
            arr[tailI] = head;
        }
    }
    
    /**
     * Checks if a value is in an array.
     * @param array The array to check if a value is in.
     * @param needle The value to search in the array.
     * @return Returns true if the value is in the array, false if it is not.
     */
    public static boolean in(int[] arr, int needle) {
        for(int i : arr) {
            if(i == needle) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Insert an item into an array by creating a new one.
     * @param array The array to append the value to.
     * @param item The item to append to the array.
     * @return The new array containing all elements from the origional array and the newly inserted item.
     */
    public static int [] insert(int[] arr, int item) {
        int [] newArray = Arrays.copyOf(arr, arr.length+1);
        newArray[arr.length] = item;
        return newArray;
    }
}
