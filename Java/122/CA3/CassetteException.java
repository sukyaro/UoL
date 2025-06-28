/**
 * COMP122 CA3 Assignment (CassetteException.java)
 *
 * Yaro Lebedysnkyi
 * Student ID: 201858746
 */


/**
 * An unchecked exception thrown when a transaction cannot be completed
 * due to insufficient funds in the vending machine's cassette.
 */
class CassetteException extends RuntimeException {
    /**
     * Constructs a new CassetteException with no detail message.
     */
    public CassetteException () {}

    /**
     * Constructs a new CassetteException with the specified detail message.
     * @param explanation The reason for the exception.
     */
    public CassetteException (String explanation) {
        super(explanation);
    }
}