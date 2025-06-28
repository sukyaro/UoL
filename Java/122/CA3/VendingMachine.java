/**
 * COMP122 CA3 Assignment (VendingMachine.java)
 *
 * Yaro Lebedysnkyi
 * Student ID: 201858746
 */



import java.util.*;


/**
 * Represents a vending machine that can sell books.
 * The machine accepts coins, calculates prices based on location, and handles secure restocking.
 */
class VendingMachine {
    private List<Book> shelf;
    private double locationFactor;
    private int cassette;
    private int safe;
    private String password;

    /**
     * Constructs a VendingMachine with a given location pricing factor and admin password.
     * @param locationFactor The factor to calculate book price based on number of pages.
     * @param password The password for authorized operations like restocking and emptying the safe.
     */
    public VendingMachine(double locationFactor, String password) {
        this.locationFactor = locationFactor;
        this.password = password;
        this.cassette = 0;
        this.safe = 0;
        this.shelf = new ArrayList<>();
    }

    /**
     * Returns the current balance of inserted coins (cassette).
     * @return Amount in the cassette.
     */
    public int getCassette() {
        return cassette;
    }

    /**
     * Inserts a coin into the vending machine if it is a valid denomination.
     * @param coin The value of the coin to insert.
     * @throws IllegalArgumentException If the coin is not an accepted denomination.
     */
    public void insertCoin(int coin) {
        int[] allowedCoins = {1, 2, 5, 10, 20, 50, 100, 200};
        boolean allowCheck = false;

        for (int i : allowedCoins) {
            if (i == coin) {
                allowCheck = true;
                break;
            }
        }

        if (allowCheck) {
            cassette += coin;
        }
        else {
            throw new IllegalArgumentException("This is an invalid coin");
        }
    }

    /**
     * Cancels the current transaction and returns the inserted coins.
     * @return The amount returned to the user.
     */
    public int cancel() {
        int cassetteCopy = cassette;
        cassette = 0;
        return cassetteCopy;
    }

    /**
     * Restocks the vending machine with a list of books if the password is correct.
     *
     * @param books List of books to add to the machine.
     * @param passwordInput Password for authorization.
     * @throws InvalidPasswordException If the password is incorrect.
     */
    public void restock(List<Book> books, String passwordInput) throws InvalidPasswordException {
        if (password.equals(passwordInput)) {
            shelf.addAll(books);
        }
        else {
            throw new InvalidPasswordException("Invalid password");
        }
    }

    /**
     * Empties the machine's safe and returns the collected money if the password is correct.
     * @param passwordInput Password for authorization.
     * @return Amount of money removed from the safe.
     * @throws InvalidPasswordException If the password is incorrect.
     */
    public int emptySafe(String passwordInput) throws InvalidPasswordException {
        if (password.equals(passwordInput)) {
            int safeCopy = safe;
            safe = 0;
            return safeCopy;
        } 
        else {
            throw new InvalidPasswordException("Invalid password");
        }
    }

    /**
     * Retrieves a list of all books currently in the vending machine.
     * @return List of book descriptions.
     */
    public List<String> getCatalogue() {
        List<String> allBooks = new ArrayList<>();

        for (Book currBook : shelf) {
            allBooks.add(currBook.toString());
        }

        return allBooks;
    }

    /**
     * Calculates the price of a book at the given index based on its number of pages and location factor.
     * @param index The index of the book in the shelf list.
     * @return The calculated price of the book.
     * @throws IndexOutOfBoundsException If the index is invalid.
     */
    public int getPrice(int index) {
        if (index <= shelf.size() &&  index >= 0) {
            return (int) Math.ceil(shelf.get(index).getPages() * locationFactor);
        } 
        else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Attempts to purchase a book by index. Requires enough money in the cassette.
     * @param index The index of the book in the shelf list.
     * @return The purchased Book object.
     * @throws CassetteException If there is not enough money inserted.
     * @throws IndexOutOfBoundsException If the index is invalid.
     */
    public Book buyBook(int index) throws CassetteException {
        int bookPrice = getPrice(index);
        if (index <= shelf.size() &&  index >= 0) {
            if (bookPrice <= cassette) {
                Book boughtBook = shelf.get(index);
                shelf.remove(index);
                cassette -= bookPrice;
                safe += bookPrice;
                return boughtBook;
            }
            else {
                throw new CassetteException("not enough money has been inserted");
            }
        } 
        else {
            throw new IndexOutOfBoundsException();
        }
    }
}