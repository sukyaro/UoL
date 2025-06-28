public class Credit {
  public static void main(String[] args) {
    System.out.print("Number:");
    java.util.Scanner scanner = new java.util.Scanner(System.in);

    long number = scanner.nextLong();
    long numberCopy = number;
    int numberLength = count(numberCopy);
    int[] numberArray = numAray(numberLength, number);
    boolean numValid = validation(numberArray, numberLength);
    
    if (numValid == true) {
      String compChoice = company(number, numberLength);
      System.out.println(compChoice);
    } else {
      System.out.println("INVALID");
    }
  }


  public static int count(long numberCopy) {
    int length = 0;
    while (numberCopy > 0) {
      length += 1;
      numberCopy /= 10;
    }
    return length;
  }


  public static int[] numAray(int numberLength, long number) {
    int[] numArray = new int[numberLength];

    for (int i = numberLength - 1; i >= 0; i--) {
      numArray[i] = (int) (number % 10);
      number /= 10;
    }
    return numArray;
  }


  public static boolean validation(int[] numberArray, int numberLength) {
    int sum = 0;
    for (int i = (numberLength - 2); i >= 0; i -= 2) {
      int addNum = numberArray[i] * 2;
      if (addNum > 9) {
        sum += addNum / 10;
        sum += addNum % 10;
      } else {
        sum += addNum;
      }
    }

    for (int i = (numberLength - 1); i >= 0; i -= 2) {
      sum += numberArray[i];
    }
  
    return (sum % 10 == 0);
  }


  public static String company(long number, int numberLength) {
    String strArr = Long.toString(number);
    if (strArr.startsWith("34") || strArr.startsWith("37")) {
        if (numberLength == 15) {
            return "AMEX";
        }
    } 
    else if (strArr.matches("^5[1-5].*")) {
        if (numberLength == 16) {
            return "MASTERCARD";
        }
    } 
    else if (strArr.startsWith("4")) {
        if (numberLength == 13 || numberLength == 16) {
            return "VISA";
        }
    }
    
    return "INVALID";
  }
}
