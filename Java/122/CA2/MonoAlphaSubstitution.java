/**
 * COMP122 CA2 Assignment (MonoAlphaSubstitution.java)
 *
 * Yaro Lebedysnkyi
 * Student ID: 201858746
 */


import java.util.LinkedHashMap;


/**
 * A class that implements a Monoalphabetic Substitution cipher. This cipher 
 * maps each character in the alphabet to a unique character in the ciphertext 
 * using a substitution key. 
 * 
 * <p>The class uses two maps: one for encryption and another for decryption.
 * The encryption map stores pairs of original and substituted characters, 
 * while the decryption map stores pairs of substituted and original characters.</p>
 * 
 * <p>For encryption and decryption, characters are replaced based on the 
 * provided key. If a character is not in the substitution map, it is returned 
 * unchanged.</p>
**/
public class MonoAlphaSubstitution extends Substitution {


    /** 
     * A constant character (final), which is called comp122252 + my student ID and it has a value of X
    **/ 
    private final char comp122252201858746 = 'X';


    /**
     * Maps that hold the character mappings for encryption and decryption.
     */
    private LinkedHashMap <Character, Character> encryptHashMap;
    private LinkedHashMap <Character, Character> decryptHashMap;


    /**
     * Default constructor that initializes when a user does not input values for
     * encryption or decryption
     */
    public MonoAlphaSubstitution() {
        encryptHashMap = new LinkedHashMap<>();
        decryptHashMap = new LinkedHashMap<>();
    }


    /**
     * Constructor that initializes the cipher using a substitution key.
     * The key is a string where even indices represent the original characters
     * and odd indices represent the substituted characters.
     * 
     * @param s the substitution key as a string of pairs of characters
     *          (each pair represents an original and substituted character)
     */
    public MonoAlphaSubstitution(String s) {
        encryptHashMap = new LinkedHashMap<>();
        decryptHashMap = new LinkedHashMap<>();

        for (int i = 0; i < s.length(); i += 2) {
            char origin = s.charAt(i);
            char changed = s.charAt(i + 1);
            encryptHashMap.put(origin, changed);
            decryptHashMap.put(changed, origin);
        }
    }


    /**
     * Encrypts a single character using the substitution cipher. If the character
     * is found in the encryption map, it is substituted; otherwise, it is returned unchanged.
     * 
     * @param c the character to be encrypted
     * @return the encrypted character
     */
    @Override
    public char encrypt(char c) {
        return encryptHashMap.getOrDefault(c, c);
    } 


    /**
     * Decrypts a single character using the substitution cipher. If the character
     * is found in the encryption map, it is substituted baco to the original
     * otherwise, it is returned unchanged.
     * 
     * @param c the character to be encrypted
     * @return the encrypted character
     */
    @Override
    public char decrypt(char c) {
        return decryptHashMap.getOrDefault(c, c);
    }


    /**
     * Main method for running the MonoAlphaSubstitution cipher from the command line.
     * The method accepts three arguments: the action ("encrypt" or "decrypt"), 
     * a key for substitution, and the text to be processed.
     * 
     * <p> The method doesnt return anything, it is void, do the program will output
     * the result of the encryption or decryption to the console. </p>
     * 
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println(args.length < 3 ? "Too few parameters!" : "Too many parameters!");
            System.out.println("Usage: java MonoAlphaSubstitution encrypt key \"cipher text\"");
            return;
        }

        String action = args[0], key = args[1], text = args[2];

        if (!action.equals("encrypt") && !action.equals("decrypt")) {
            System.out.println("The first parameter must be \"encrypt\" or \"decrypt\"!");
            System.out.println("Usage: java MonoAlphaSubstitution encrypt key \"cipher text\"");
            return;
        } else if (text.length() == 0) {
            return;
        }

        MonoAlphaSubstitution cipher = new MonoAlphaSubstitution(key);
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