/**
 * COMP122 CA1 Assignment (Caesar.java)
 *
 * Yaro Lebedysnkyi
 * Student ID: 201858746
 */


class Caesar {

    /**
    * Main method of the class
    *
    * <p> Checking that the right number of arguments have been passed to the terminal
    * 
    * <p> Calling the method to rotate the characters in a string
    * 
    * @param args from the terminal
   */
    public static void main(String[] args) {
        if (args.length > 2) {
            System.out.println("Too many parameters!\nUsage: java Caesar n \"cipher text\"");
            return;
        }

        if (args.length < 2) {
            System.out.println("Too few parameters!\nUsage: java Caesar n \"cipher text\"");
            return;
        }

        // Some additional validation, this checks that the first arument (supposed to be an integer) is an integer
        // If it is not an integer, the error occurs and the program terminates
        int shift;
        try {
            shift = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Error: You must use an integer as the first argument!");
            return;
        }

        String originString = args[1];
        System.out.println(rotate(shift, originString));
    }

    /**
    * Rotatig the whole string
    *
    * <p> Creating a new string for a rotated stiring
    * 
    * <p> Going through every single character and plugging them into a different
    * method which will rotate them
    * 
    * <p> The rotated character is added to a string
    * 
    * @param intString the string to consider and the shift integer
    * @return a new rotated string
    */
    public static String rotate(int shift, String originString) {
        String encryptedString = "";
        for (int i = 0; i <= (originString.length() - 1) ; i++) {
            encryptedString += rotate(shift, originString.charAt(i));
        }

        return encryptedString;
    }

    /**
    * Rotatig every character induvidually
    *
    * <p> Translating every character into the ASCII value
    * 
    * <p> Checking that the shift works properly considering large shifts and negative shifts
    * 
    * @param intString the string to consider and the shift integer
    * @return a new rotated char
    */
    public static char rotate(int shift, char originChar) {
        int originCharAscii = (int) originChar;
        int newCharInt;

        // Initially the algorithm for shifting has been implemented differently
        // It has been changed so that the shift could be a large integer (positive/negative)
        if (originCharAscii >= 65 && originCharAscii <= 90) {
            newCharInt = 65 + (originCharAscii - 65 + (shift % 26) + 26) % 26;
        } 
        
        else if (originCharAscii >= 97 && originCharAscii <= 122) {
            newCharInt = 97 + (originCharAscii - 97 + (shift % 26) + 26) % 26;

        }

        // Returning the original character if it is not a letter
        else {
            newCharInt = originCharAscii;
        }

        return (char) newCharInt;
    }
}
