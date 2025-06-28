/**
 * COMP122 CA3 Assignment (InvalidPasswordException.java)
 *
 * Yaro Lebedysnkyi
 * Student ID: 201858746
 */


/**
 * An unchecked exception thrown when an incorrect password is provided
 * for a secured operation such as restocking or accessing the safe.
 */
class InvalidPasswordException extends RuntimeException {
    /**
     * Constructs a new InvalidPasswordException with no detail message.
     */
    public InvalidPasswordException () {}

    /**
     * Constructs a new InvalidPasswordException with the specified detail message.
     * @param explanation The reason for the exception.
     */
    public InvalidPasswordException (String explanation) {
        super(explanation);
    }
}