/**
 * A bunch of utility functions for Strings.
 *
 * @author Patrick Totzke
 * @version 1.0
 */
public class StringTools {

  /**
   * Computes the length of a string.
   *
   * <p>This is done by first turning it into an Array of characters, then iteratively incrementing
   * an integer variable for every character.
   *
   * <p>This is of course are really silly solution because String.length or Array.size can be used
   * instead. In fact, the latter is implicitly used in the termination criterion of the for loop.
   *
   * @param str the string to consider
   * @return the length of the given string.
   */
  public static int length(String str) {
    char[] len = str.toCharArray();
    int a = 0;
    for (char ch : len) {
      a++;
    }
    return a;
  }

  /**
   * Swops all the letters of a string in the reverse order
   *
   * <p> First, creating a new empty string where the new string will be stored
   * 
   * <p> Second, start the loop to go through every single character in a string, the loop iterates 
   * while the whole string has not been gone through
   * 
   * <p> Third, adding a character to a new string
   * 
   * @param str the string to consider
   * @return the new reversed string
   */
  public static String swap(String s) {
    String rev = "";

    for (int j = s.length(); j > 0; --j) {
      rev = rev + (s.charAt(j - 1));
    }
    return rev;
  }

  /**
   * The main of the class
   *
   * <p> First, printing out the text to ask a user to enter a string
   * 
   * <p> Second, using Scanner to input the string
   * 
   * <p> Third, outputting the text and calling the methods to get the results
   * (we need the length of the string and the swoped string, so we call these methods)
   * 
   * @param arg this is what will be read from the terminal
   */
  public static void main(String[] arg) {
    System.out.print("Enter a string: ");
    java.util.Scanner scanner = new java.util.Scanner(System.in);
    String str = scanner.nextLine();
    System.out.println("It's length is " + length(str));
    System.out.println("It's swap is " + swap(str));
  }
}
