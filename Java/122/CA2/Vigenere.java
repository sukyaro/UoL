/**
 * COMP122 CA2 Assignment (Caesar.java)
 *
 * Yaro Lebedysnkyi
 * Student ID: 201858746
 */


/**
 * The Vigenere class implements the Vigenère cipher, a method of encrypting alphabetic text
 * by using a simple form of polyalphabetic substitution. The Vigenère cipher uses a key (a string of letters)
 * to determine the shift for each letter in the plaintext, cycling through the key for each letter in the message.
 * 
 * <p>This class supports both encryption and decryption using a variable-length key. It creates a separate
 * Caesar cipher for each letter in the key, and applies the corresponding Caesar shift for each character in
 * the plaintext or ciphertext.</p>
 * 
 * <p>The encryption and decryption operations are performed on a character-by-character basis, where the shifts
 * are applied based on the corresponding letter of the key. The key is reused in a cyclic manner, meaning the first
 * letter of the key is used for the first letter of the message, the second letter for the second, and so on.</p>
**/ 
public class Vigenere extends Substitution {


    /** 
     * A constant character (final), which is called comp122252 + my student ID and it has a value of X
    **/ 
    private final char comp122252201858746 = 'X';


    private Caesar[] implementShifts;
    private int keyIndex;
    private String key;


    /**
     * Default constructor that initializes the Vigenère cipher with an empty key and key index at 0.
     */
    public Vigenere() {
        this.key = "";
        this.keyIndex = 0;
        this.implementShifts = null;
    }

    /**
     * Constructs a Vigenère cipher with the specified key. The key is used to generate corresponding
     * Caesar ciphers for each letter in the key. The key index is initialized to 0.
     * 
     * Additionally, since the Ceasar shift is a specific shift (12225 + shift) % 26
     * I have decided to cancel it using my formula "(((int) key.charAt(i) - 'A') - 12225 % 26 + 26) % 26"
     * 
     * @param key the key used for the Vigenère cipher, which is a string of letters
     */
    public Vigenere(String key) {
        this.key = key;
        this.keyIndex = 0;
        this.implementShifts = new Caesar[key.length()];

        if (key != null && !key.isEmpty())
            for (int i = 0; i < key.length(); i++) {
                implementShifts[i] = new Caesar((((int) key.charAt(i) - 'A') - 12225 % 26 + 26) % 26);
        } 
        else {
            implementShifts = new Caesar[0];
        }
    }

    /**
     * Encrypts a single character using the Vigenère cipher. The encryption process applies the Caesar cipher
     * corresponding to the current key index, and then increments the key index for the next character.
     * 
     * <p>The method only encrypts alphabetic characters and leaves non-alphabetic characters unchanged.</p>
     * 
     * @param c the character to be encrypted
     * @return the encrypted character
     */
    public char encrypt(char c) {
        if (implementShifts == null || key.length() == 0) {
            return c;
        }

        int cAscii = (int) c;

        if ((cAscii >= 65 && cAscii <= 90) ||
            (cAscii >= 97 && cAscii <= 122)) {
                char encryptLetter = implementShifts[keyIndex].encrypt(c);
                keyIndex = (keyIndex + 1) % key.length();
                return encryptLetter;
        }
        keyIndex = (keyIndex + 1) % key.length();
        return c;
    }
        
    /**
     * Decrypts a single character using the Vigenère cipher. The decryption process applies the Caesar cipher
     * corresponding to the current key index, and then increments the key index for the next character.
     * 
     * <p>The method only decrypts alphabetic characters and leaves non-alphabetic characters unchanged.</p>
     * 
     * @param c the character to be decrypted
     * @return the decrypted character
     */
    public char decrypt(char c) {
        if (implementShifts == null || key.length() == 0) {
            return c;
        }
        
        int cAscii = (int) c;

        if ((cAscii >= 65 && cAscii <= 90) ||
            (cAscii >= 97 && cAscii <= 122)) {
                char decryptLetter = implementShifts[keyIndex].decrypt(c);
                keyIndex = (keyIndex + 1) % key.length();
                return decryptLetter;
        }
        keyIndex = (keyIndex + 1) % key.length();
        return c;
    }


    /**
     * Main method for running the Vigenère cipher from the command line.
     * The method accepts three arguments: the action ("encrypt" or "decrypt"), 
     * a key for the Vigenère cipher, and the text to be processed.
     * 
     * <p>The program will print the result of the encryption or decryption to the console.</p>
     * 
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println(args.length < 3 ? "Too few parameters!" : "Too many parameters!");
            System.out.println("Usage: java Vigenere encrypt key \"cipher text\"");
            return;
        }

        String action = args[0], key = args[1], text = args[2];

        if (!action.equals("encrypt") && !action.equals("decrypt")) {
            System.out.println("The first parameter must be \"encrypt\" or \"decrypt\"!");
            System.out.println("Usage: java Vigenere encrypt key \"cipher text\"");
            return;
        } else if (text.length() == 0) {
            return;
        }

        Vigenere cipher = new Vigenere(key);
        String output = "";

        for (int i= 0; i < text.length(); i++) {
            if (action.equals("encrypt")) {
                output += cipher.encrypt(text.charAt(i));
            }
            else {
                output += cipher.decrypt(text.charAt(i));
            }
        }

        System.out.println(output);
    }
}