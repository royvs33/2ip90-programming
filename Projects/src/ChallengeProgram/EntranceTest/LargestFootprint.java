/*
    Name: Roy van Schaijk
    Student ID: 1314599
 */

import java.util.stream.IntStream;

public class LargestFootprint {
    int[] numberSequence;
    int[] uniqueSequence;
    int largestFootprint = 0;

    void Start() {
        uniqueSequence = IntStream.of(numberSequence).distinct().toArray();
        for (int i = 0; i < uniqueSequence.length; i++) {
            int footprint = CalcFootprint(uniqueSequence[i]);
            largestFootprint = footprint > largestFootprint ? footprint : largestFootprint;
        }
        System.out.println(largestFootprint);
    }

    int CalcFootprint(int n) {
        boolean foundFirst = false;
        int firstIndex = 0;
        int lastIndex = 0;
        for (int i = 0; i < numberSequence.length; i++) {
            if (numberSequence[i] == n) {
                lastIndex = i;
                if (!foundFirst) {
                    foundFirst = true;
                    firstIndex = i;
                }
            }
        }
        return lastIndex - firstIndex + 1;
    }

    public static void main(String[] args) {
        LargestFootprint instance = new LargestFootprint();

        instance.numberSequence = new int[]{7, 1, 4, 2, 1, 4, 4, 4};
        instance.Start();
    }
}