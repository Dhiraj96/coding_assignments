import java.util.Scanner;

public class ConsistencyCheck {

  /* This class performs a consistency check on an input of logical
   * statements. (2-SAT or two variables) i.e. all happen at the same time 
   *
   * Variables will be represented by numbers 1 ... n
   * and their negations by -1 ... -n
   *
   * These statements will be of the form:
   * a    (this means that variable a is True)
   * a b  (this means (a or b) )
   *
   * After the user inputs a list of such statements, followed by a "0" for
   * done, * you must determine if these statements are logically consistent
   * or not.
   *
   * Specifying that a variable is true and then false (or vice versa) should
   * overwrite the previous input, and not cause an inconsistency
   *
   * Example Run:
   * Number of variables?
   * >> 3
   * Please Input Logical Statements or 0 for done.
   * >> 1
   * >> 2 3
   * >> 2 -1
   * >> 3 -2
   * >> 0
   * This is consistent
   *
   *
   * Example Run:
   * Number of variables?
   * >> 3
   * Please Input Logical Statements or 0 for done.
   * >> 1
   * >> -1 2
   * >> -2 3 
   * >> -1 3
   * >> 0
   * This is inconsistent
   *
   *
   * Example Run:
   * Number of variables?
   * >> 1
   * Please Input Logical Statements or 0 for done.
   * >> 1
   * >> -1
   * >> 0
   * This is consistent
   *
   * Fun Reminder: these three statements are equivalent 
   * a \implies b 
   * \not a or b
   * \not b \implies \not a
   *
   * Fun Reminder:  
   * $a$ is equivalent to \not a \implies a
   */

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    System.out.println("Number of Variables?");
    int n = in.nextInt();
    in.nextLine();
    // TODO Create a graph with the appropriate number of vertices.

    System.out.println("Please Input Logical Statements or 0 for done.");

    while(true) {
      String[] input = in.nextLine().split(" ");
      if(input.length == 1) {
        int a = Integer.parseInt(input[0]);
        // TODO Add the representation of the statement a to your graph
      }
      else if (input.length == 2) {
        int a = Integer.parseInt(input[0]);
        int b = Integer.parseInt(input[1]);
        // TODO Add the representation of the statement a or b to your graph
      } 
    }

    // TODO Run the consistency check algorithm and output consistent or
    // inconsistent
    //
    // Bonus (5 pts): Output a valid assignment of the variables if consistent

  }
}
