/**
 * @author S. Roubtsov
 * @version 1.5
 *
 * Class that check the correctness of the "Mad Trucker" output.
 * It takes in a loop the input: <test input> <test output> as a list of integers 
 * from a file taken as a parameter if one is given, 
 * otherwise it takes input from the console. Any non integer input stops the execution.
 * All excessive numbers are taken as the next test. 
 * It's not completely robust. So, the consistency of input is your responsibility.
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class MadTruckerChecker {
  private static boolean correct = true;
  private static int n;
  private static int[] fuelCounts;
  private static int[] givenOutput;
  
  private static ArrayList<Integer> noStops;
  public static void main(String[] args) { 
    
    try{
      Scanner sc;
      if (args.length != 0){        
        File in = new File(args[0]);
        sc = new Scanner(in);
      } else{
        sc = new Scanner(System.in);
      }
      //
      int testN = 0;
      while (sc.hasNextInt()){
        n = sc.nextInt();
        fuelCounts = new int[n];
        for (int i = 0; i < n; i++)
        { 
          if (sc.hasNextInt()){      
            fuelCounts[i] = sc.nextInt();
          } else{
            System.out.println("Not enough cans");
            System.exit(-1);//your input for this test is wrong
          }
          
        }
        
        noStops = new ArrayList<Integer>(n - 1);
        for (int i = 0; i < n-1; i++)
        { if (sc.hasNextInt()){      
          noStops.add(new Integer(sc.nextInt()));
        } else{
          System.out.println("Not enough places");
          System.exit(-1);//your input for this test is wrong
        }
        }
        
        givenOutput = new int[n];
        for (int i = 0; i < n; i++)
        {
          givenOutput[i] = sc.nextInt();
        }
        
        int currentZone = 0;
        correct = true;
        for (int i = 0; i < fuelCounts.length; i++){
          currentZone += fuelCounts[givenOutput[i]];
          if ( noStops.contains(currentZone)){
            correct = false;
            break;
          }  
        }      
        System.out.println();
        System.out.println("Output for test " + testN + ": " + ((correct)? "correct":"NOT correct") + " ends at zone: " + currentZone);
        testN++;
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }    
  
}
