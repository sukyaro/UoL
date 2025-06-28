/**
 * COMP122 CA2 Assignment (Substitution.java)
 *
 * Yaro Lebedysnkyi
 * Student ID: 201858746
 */


/**
 * An abstract class that represents a generic substitution cipher.
 * This class provides basic encryption and decryption functionality 
 * for individual characters and full strings.
 **/
public abstract class Substitution implements Cipher {
    /** 
     * A constant character (final), which is called comp122252 + my student ID and it has a value of X
    **/ 
    private final char comp122252201858746 = 'X';


    /**
     * Encrypts/Decrypts a single character using the substitution cipher.
     * 
     * @param c the character to be encrypted
     * @return the encrypted character
     **/
    public abstract char encrypt(char c);
    public abstract char decrypt(char c);


    /**
     * Encrypts a given plaintext string by applying the substitution 
     * cipher to each character.
     * 
     * @param plaintext the string to be encrypted
     * @return the encrypted string
     */
    @Override
    public String encrypt(String plaintext) {
        String newString = "";
        for (int i = 0; i < plaintext.length(); i++) {
            newString += encrypt(plaintext.charAt(i));
        }
        return newString;
    }


    /**
     * Decrypts the given plaintext string by applying the substitution 
     * cipher to each character.
     * 
     * @param plaintext the string to be encrypted
     * @return the encrypted string
     */
    @Override
    public String decrypt(String cryptotext) {
        String originalString = "";
        for (int i = 0; i < cryptotext.length(); i++) {
            originalString += decrypt(cryptotext.charAt(i));
        }
        return originalString;
    }
}