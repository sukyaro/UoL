import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class StringApp {

  /**
   * Reads the contents of a file and returns it as a String.
   *
   * @param path the path to the file
   * @return the contents of the file as a String, or "file not found!" if an IOException occurs
   */
  public static String readFileAsString(String path) {
    try {
      return Files.readString(Path.of(path));
    } catch (IOException e) {
      return "file not found!";
    }
  }


  // Part 1
  public static String pow(String s, int n) {
    String newS = "";
    for (int i = 0; i < n; i++) {
      newS += s;
    }
    return newS;
  }

  // Part 2
  public static int factorCount(String a, String f) {
    int counter = 0;

    while (a.length() > 0) {
      int index = a.indexOf(f);
      if (index != -1) {
        counter += 1;
        a = a.substring(index + f.length());
      } else {
        break;          
      }
    }
    return counter;
  }


  
  public static int factorCount(String a, String f, boolean caseSensitive) {
    int counter = 0;
    if (caseSensitive == true) {
      counter = factorCount(a, f);
    } else {
      counter = factorCount(a.toLowerCase(), f.toLowerCase());
    }
    return counter;
  }

  public static void letterCounter(String str) {
    str = str.toLowerCase();
    int[] counter = new int[26];
    // Using "char-substraction" to perform counting the letters
    for (int i = 0; i < str.length(); i++) {
        char currentChar = str.charAt(i);
        if (currentChar >= 'a' && currentChar <= 'z') {
            counter[currentChar - 'a']++;
        }
    }

    for (char i = 'a'; i <= 'z'; i++)  {
      System.out.println(i + ": " + counter[i - 'a']);
    }

  }


  // Part 3
  public static void main(String[] args) {
    String input1 = args[0];
    //String input2 = args[1];

    letterCounter(input1);
    //System.out.println(factorCount(input1, input2));

    //String s = "helloworld heLLomoon hellosun HELLO lamp post";
    //System.out.println(factorCount(s, "hello", true));
    //System.out.println(factorCount(s, "hello", false));

    /*
    for (char letter = 'a'; letter <= 'z'; letter++) { // why does this work?
      // replace the next line. Can you explain what + means here and why?
      System.out.println(letter + ": " + 0);
    }
    */
  }
}
