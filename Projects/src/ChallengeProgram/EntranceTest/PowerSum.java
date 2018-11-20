/*
    Name: Roy van Schaijk
    Student ID: 1314599
 */

public class PowerSum {

    void S(int n) {
        int totalValue = 1;
        for (int exponent = 1; exponent <= n; exponent++) {

//            int currNumber = 1;
//            for (int i = 1; i <= exponent; i++) {
//                currNumber = currNumber * 2;
//            }

            int currNumber = 1 << exponent; //bit shift operator

            totalValue = totalValue + currNumber;
        }
        System.out.println(totalValue);
    }

    int amount;
    int value = 1;
    int currentExponent = 0;

    void SRec() {
        if (currentExponent < amount) {
            currentExponent++;
            int currNumber = 1 << currentExponent; //bit shift operator
            value = value + currNumber;
            SRec();
        } else {
            System.out.println(value);
        }
    }

    public static void main(String[] args) {
        int n = 3;

        //non-recursive
        (new PowerSum()).S(n);

        //recursive
        PowerSum powerRec = new PowerSum();
        powerRec.amount = n;
        powerRec.SRec();
    }
}