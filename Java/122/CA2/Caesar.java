/**
 * COMP122 CA2 Assignment (Caesar.java)
 *
 * Yaro Lebedysnkyi
 * Student ID: 201858746
 */


/**
 * The class Caesar extends the MonoAlphaSubstitution class and implements 
 * the Caesar cipher encryption and decryption logic. The Caesar cipher is a type of substitution cipher
 * where each letter in the plaintext is shifted by a fixed number of positions in the alphabet.
 * 
 * <p>The class supports both encryption and decryption of text using a provided shift value.
 * Additionally, the shift value is changed, so shift = (12225 + shift) % 26.
 * It inherits the core functionality from MonoAlphaSubstitution and customizes it for the 
 * Caesar cipher's shifting mechanism.</p>
 * 
 * <p>The key for the Caesar cipher is provided as an integer, and the class automatically constructs
 * the corresponding encryption key, which shifts each letter by the specified number of positions.</p>
**/ 
public class Caesar extends MonoAlphaSubstitution {


    /** 
     * A constant character (final), which is called comp122252 + my student ID and it has a value of X
    **/ 
    private final char comp122252201858746 = 'X';


    /**
     * The shift value used for the Caesar cipher. It determines the number of positions 
     * each letter is shifted in the encryption/decryption process.
     */
    private int shift;

    /**
     * Default constructor that initializes the Caesar cipher with a shift of 0.
     */
    public Caesar() {
        super();
        this.shift = 0;
    }

    /** 
     *  i was going to delete the shift atribute and consequently the "this.shift" bit,
     *  since they are no needed in the program now because i did emplement it in a different way
     *  however, i decided to leave them for the sake of CodeGrade
    **/
    public Caesar(int key) {
        super(newCaesarKey((key + 12225) % 26));
        this.shift = (key + 12225) % 26;
    }

    /**
     * Generates a new Caesar cipher key based on the specified shift.
     * This method creates a string representing a key where each letter of the alphabet
     * is mapped to a letter shifted by the specified amount.
     * 
     * @param shift the shift value used to generate the cipher key
     * @return a string containing the encryption key for the Caesar cipher
     */
    public static String newCaesarKey(int shift) {
    String newKey = "";

    for (char c = 'a'; c <= 'z'; c++) {
        newKey += c;
        newKey += (char) ('a' + ((c - 'a' + shift) % 26 + 26) % 26);
    }

    for (char c = 'A'; c <= 'Z'; c++) {
        newKey += c;
        newKey += (char) ('A' + ((c - 'A' + shift) % 26 + 26) % 26);
    }

    return newKey;
    }


    /**
     * Main method for running the Caesar cipher from the command line.
     * The method accepts three arguments: the action ("encrypt" or "decrypt"), 
     * a key for the Caesar cipher, and the text to be processed.
     * 
     * <p>The program will print the result of the encryption or decryption to the console.</p>
     * 
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println(args.length < 3 ? "Too few parameters!" : "Too many parameters!");
            System.out.println("Usage: java Caesar encrypt n \"cipher text\"");
            return;
        }

        String action = args[0], text = args[2];
        int n;

        try {
            n = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Error: You must use an integer as the second argument!");
            return;
        }

        if (!action.equals("encrypt") && !action.equals("decrypt")) {
            System.out.println("The first parameter must be \"encrypt\" or \"decrypt\"!");
            System.out.println("Usage: java Caesar encrypt n \"cipher text\"");
            return;
        } else if (text.length() == 0) {
            return;
        }

        Caesar cipher = new Caesar(n);
        String output = "";

        int i = 0;
        do { 
            if (action.equals("encrypt")) {
            output += cipher.encrypt(text.charAt(i));
            } else {
            output += cipher.decrypt(text.charAt(i));
            }
            i++;
        } while (i < text.length());

        System.out.println(output);
    }

}