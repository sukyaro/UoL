
public class Newton {
  public static void main(String[] args) {

    if (args.length < 2 || args.length > 3) {
      System.out.println("Incorrect Number of Parameters\nUsage: java Newton number guess epsilon");
      return;
    }

    double n = Double.parseDouble(args[0]), guess = Double.parseDouble(args[1]), coeficient = 0.0000001;
    if (args.length > 2) {
      coeficient = Double.parseDouble(args[2]);
    }

    double answer = sqRoot(n, guess, coeficient);
    System.out.println(answer);
  }

  public static double sqRoot(Double n, Double guess, Double coeficient) {
    double newGuess = ((n / guess) + guess) / 2;

    if (Math.abs(guess - newGuess) > coeficient) {
      return sqRoot(n, newGuess, coeficient);
    } else {
      return newGuess;
    }
  }
}