/**
 * COMP122 CA3 Assignment (Book.java)
 *
 * Yaro Lebedysnkyi
 * Student ID: 201858746
 */


/**
 * The class epresents a book with a title, author, edition, and content.
 */
class Book {
    private String title;
    private String author;
    private String content;
    private int edition;


    /**
     * Returns the title of the book.
     * @return the book's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the author of the book.
     * @return the book's author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Returns the content of the book.
     * @return the book's content
     */
    public String getContent() {
        return content;
    }

    /**
     * Returns the edition number of the book.
     * @return the book's edition
     */
    public int getEdition() {
        return edition;
    }

    /**
     * Calculates the number of pages in the book.
     * Each page has got 666 characters.
     * @return the number of pages, rounded up
     */
    public int getPages() {
        return (int) Math.ceil((double) content.length() / 666);
    }

    /**
     * Returns a string representation of the book,
     * including title, author, and edition.
     * Each of them is on a new line.
     * @return a formatted string representing the book
     */
    @Override
    public String toString() {
        return "Title: " + title + "\n" + "Author: " + author + "\n" + "Edition: " + edition;
    }

    /**
     * Constructs a new Book with the specified title, author, content, and edition.
     *
     * @param t the title of the book
     * @param a the author of the book
     * @param c the textual content of the book
     * @param e the edition number of the book
     */
    public Book(String t, String a, String c, int e) {
        this.title = t;
        this.author = a;
        this.content = c;
        this.edition = e;
    }  


    // Testing
    /*
    public static void main(String[] args) {
        String content = "a".repeat(150000);

        Book newBook = new Book("Lol Book", "Yaro Boo", content, 567);

        System.out.println(newBook.toString());
        System.out.println("Number of pages: " + newBook.getPages());
    }
    */
    
}

