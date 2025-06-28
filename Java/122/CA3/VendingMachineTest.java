


import java.util.*;

public class VendingMachineTest {
    public static void main(String[] args) {
        // Create some sample books
        Book b1 = new Book("Title One", "Author A", "A".repeat(1000), 1);
        Book b2 = new Book("Title Two", "Author B", "B".repeat(2000), 2);
        List<Book> books = Arrays.asList(b1, b2);

        // Create a vending machine
        VendingMachine vm = new VendingMachine(0.1, "secret123");

        // Insert valid and invalid coins
        try {
            vm.insertCoin(100);
            vm.insertCoin(50);
            System.out.println("Cassette after inserting coins: " + vm.getCassette());
            vm.insertCoin(3);  // Invalid coin
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected invalid coin: " + e.getMessage());
        }

        // Try restocking with wrong and right password
        try {
            vm.restock(books, "wrongPassword");
        } catch (InvalidPasswordException e) {
            System.out.println("Caught expected wrong password: " + e.getMessage());
        }

        try {
            vm.restock(books, "secret123");
            System.out.println("Restock successful.");
        } catch (InvalidPasswordException e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }

        // View catalogue
        System.out.println("Catalogue:");
        for (String bookDesc : vm.getCatalogue()) {
            System.out.println(bookDesc);
        }

        // Buy a book
        try {
            Book bought = vm.buyBook(0);
            System.out.println("Bought book: " + bought.getTitle());
        } catch (Exception e) {
            System.out.println("Error buying book: " + e.getMessage());
        }

        // Cancel purchase
        int refund = vm.cancel();
        System.out.println("Refunded: " + refund);

        // Empty the safe with correct and incorrect password
        try {
            int withdrawn = vm.emptySafe("wrong");
            System.out.println("Withdrawn: " + withdrawn);
        } catch (InvalidPasswordException e) {
            System.out.println("Safe access denied: " + e.getMessage());
        }

        try {
            int withdrawn = vm.emptySafe("secret123");
            System.out.println("Withdrawn from safe: " + withdrawn);
        } catch (InvalidPasswordException e) {
            System.out.println("Unexpected safe access error.");
        }
    }
}
