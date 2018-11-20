/*
    Name: Luc Geven and Roy van Schaijk
    Student ID: 1336029 and 1314599
 */

import java.util.*;

public class Rectangle {
    public Rectangle() {

    }

    void run() {
        //initialize Scanner
        Scanner sc = new Scanner(System.in);
        //left top x of rectangle
        int ltx = sc.nextInt();
        //left top y of rectangle
        int lty = sc.nextInt();
        //right bottom x of rectangle
        int rbx = sc.nextInt();
        //right bottom y of rectangle
        int rby = sc.nextInt();
        //test point x
        int tpx = sc.nextInt();
        //test point y
        int tpy = sc.nextInt();

        //validate rectangle
        if (
            //check if right x is larger than the left x
            rbx >= ltx  &&
            //check if top y is larger than the bottom y
            lty >= rby
        ) {
            //check if the test point is inside the rectangle
            if (
                //check if test point x is larger than the left x
                tpx >= ltx &&
                //check if test point x is smaller than the right x
                tpx <= rbx &&
                //check if test point y is larger than the bottom y
                tpy >= rby &&
                //check if test point y is smaller than the top y
                tpy <= lty
            ) {
                System.out.println("inside");
            } else {
                System.out.println("outside");
            }
        } else {
            System.out.println("error");
        }
    }

    public static void main(String[] args) {
        (new Rectangle()).run();
    }
}