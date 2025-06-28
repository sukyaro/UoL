import java.util.*;

public class TestBookVendingSystem {
    public static void main(String[] args) {
        try {
            // Step 1: Initialize Press and check catalogue
            String path = "/Users/sukayarik/Desktop/Java/122/CA3/books"; // Adjust if needed
            Press press = new Press(path, 5);
            List<String> catalogue = press.getCatalogue();

            assert catalogue.contains("11-0.txt") : "Catalogue does not contain 11-0.txt";

            // Step 2: Request 3 books
            List<Book> books = press.request("11-0.txt", 3);
            assert books.size() == 3 : "Did not receive 3 books";

            for (Book b : books) {
                assert b.getTitle() != null && !b.getTitle().isEmpty() : "Missing title";
                assert b.getAuthor() != null && !b.getAuthor().isEmpty() : "Missing author";
                assert b.getPages() > 0 : "Book has zero pages";
            }

            // Step 3: Initialize VendingMachine
            VendingMachine machine = new VendingMachine(1.5, "secret123");

            // Step 4: Restock with books
            machine.restock(books, "secret123");
            List<String> vendCat = machine.getCatalogue();
            assert vendCat.size() == 3 : "Vending machine did not restock correctly";

            // Step 5: Insert valid coins
            machine.insertCoin(100);
            machine.insertCoin(50);
            assert machine.getCassette() == 150 : "Cassette value incorrect after inserting coins";

            // Step 6: Check price of book and buy it
            int price = machine.getPrice(0);
            assert price <= 150 : "Book is too expensive to buy";

            Book boughtBook = machine.buyBook(0);
            assert boughtBook != null : "Book was not bought";
            assert machine.getCassette() == 150 - price : "Cassette not updated after purchase";

            // Step 7: Cancel remaining coins (simulate user cancel)
            int refunded = machine.cancel();
            assert refunded >= 0 : "Refund should be non-negative";

            // Step 8: Empty safe
            int withdrawn = machine.emptySafe("secret123");
            assert withdrawn == price : "Safe balance incorrect";

            System.out.println("✅ All system tests passed without errors.");

        } catch (Exception e) {
            System.err.println("❌ Test failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
