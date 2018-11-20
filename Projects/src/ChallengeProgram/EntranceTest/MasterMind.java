/*
    Name: Roy van Schaijk
    Student ID: 1314599
 */

import java.util.stream.IntStream;

public class MasterMind {
    int[] code;
    int[] attempt;
    int blacks = 0;
    int whites = 0;

    void Start() {
        for (int i = 0; i < 4; i++) {
            if (CheckBlack(i)) {
                blacks++;
            } else if (CheckWhite(i)) {
                whites++;
            }
        }

        for (int i = 0; i < blacks; i++) {
            System.out.print('X');
        }

        for (int i = 0; i < whites; i++) {
            System.out.print('O');
        }
    }

    boolean CheckBlack(int pos) {
        return code[pos] == attempt[pos];
    }

    boolean CheckWhite(int pos) {
        //should not be executed if black
        return IntStream.of(code).anyMatch(i -> i == attempt[pos]);
    }

    public static void main(String[] args) {
        MasterMind instance = new MasterMind();
        instance.code = new int[]{1, 3, 4, 6};
        instance.attempt = new int[]{1, 3, 6, 4};
        instance.Start();
    }
}