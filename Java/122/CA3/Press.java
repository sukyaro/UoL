/**
 * COMP122 CA3 Assignment (Press.java)
 *
 * Yaro Lebedysnkyi
 * Student ID: 201858746
 */


import java.util.*;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.*;


/**
 * The Press class represents a virtual press machine that stores and prints books from text files.
 * It manages book editions and controls the number of copies printed at once.
 */
class Press {
    private Map<String, List<Book>> shelf;
    private int shelfSize;
    private Map<String, Integer> edition;
    private String textPath;


    /**
     * Constructs a Press object by loading all valid text book files from a given directory.
     * @param pathToBookDir The path to the directory containing book text files.
     * @param size The number of copies printed in one batch (shelf size).
     */
    public Press(String pathToBookDir, int size) {
        this.shelf = new HashMap<>();
        this.shelfSize = size;
        this.edition = new HashMap<>();
        this.textPath = pathToBookDir;


        try {
            File directoryPath = new File(textPath);
            File filesList[] = directoryPath.listFiles();
            for(File file : filesList) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    shelf.put(file.getName(), new ArrayList<>());
                    edition.put(file.getName(), 0);
                }
            }
        }
        catch (Exception e) {}


        //Testing
        /*
        for (Map.Entry<String, List<Book>> entry : shelf.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        for (Map.Entry<String, Integer> entry : edition.entrySet()) {
            System.out.println("Book ID: " + entry.getKey() + ", Edition: " + entry.getValue());
        }
        */
    }


    /**
     * Parses a book text file and creates a new Book object.
     * @param bookID The filename of the book.
     * @param edition The edition number to assign to the printed book.
     * @return A Book object with parsed title, author, and content.
     * @throws IOException If the file cannot be read or required metadata is missing.
     */
    protected Book print(String bookID, int edition) throws IOException {
        if (!shelf.containsKey(bookID)) {
            throw new IllegalArgumentException("There is no such a book");
        }

        File fileHandler = new File(textPath, bookID);
        String title="";
        String author="";
        String content="";
        boolean hasContentStarted = false;

        Pattern titlePattern = Pattern.compile("^\\s*\\uFEFF?Title:\\s*(.*)");
        Pattern authorPattern = Pattern.compile("^\\s*\\uFEFF?Author:\\s*(.*)");
        Pattern contenntPattern = Pattern.compile("^\\*{3}\\s*START OF.*$", Pattern.CASE_INSENSITIVE);

        try (Scanner scan = new Scanner(fileHandler)) {
            while (scan.hasNextLine()) {
                String newLine = scan.nextLine();
                //System.out.println(newLine);

                if (!hasContentStarted) {
                    Matcher contennMatcher = contenntPattern.matcher(newLine);
                    if (contennMatcher.find()) {
                        hasContentStarted = true;
                    }
                
                    Matcher titleMatcher = titlePattern.matcher(newLine);
                    Matcher authorMatcher = authorPattern.matcher(newLine);

                    if (titleMatcher.find()) {
                        title = newLine.substring(titleMatcher.start(1), titleMatcher.end(1)).trim();
                    } 
                    else if (authorMatcher.find()) {
                        author = newLine.substring(authorMatcher.start(1), authorMatcher.end(1)).trim();
                    }
                } 
                else {
                    content += newLine + "\n";
                }
            }
        }
        catch (FileNotFoundException e) {
            throw new IOException("The files not a book");
        }

        if (title.isEmpty()) {
            throw new IOException("Theres no title!");
        }

        if (author.isEmpty()) {
            throw new IOException("There's no author!");
        }

        
        //System.out.println(content.substring(0, 3000));

        return new Book(title, author, content, edition);
    }

    /**
     * Returns a list of all book IDs (filenames) that can be produced by the press.
     * @return A list of book filenames available in the catalogue.
     */
    public List<String> getCatalogue() {
        List<String> canProduce = new ArrayList<>();

        for (Map.Entry<String, List<Book>> entry : shelf.entrySet()) {
            canProduce.add(entry.getKey());
        }

        return canProduce;
    }

    /**
     * Requests a specific number of copies of a book.
     * If not enough copies are available, it prints more as needed.
     * @param bookID The ID (filename) of the book.
     * @param amount The number of copies requested.
     * @return A list of Book objects, or an empty list if printing fails.
     * @throws IllegalArgumentException If the book does not exist.
     */
    public List<Book> request(String bookID, int amount) throws IllegalArgumentException{
        List<Book> requestedBooks = new ArrayList<>();
        List<Book> inAVendingMachine = shelf.get(bookID);

        if (!shelf.containsKey(bookID)) {
            throw new IllegalArgumentException("there is no such a book!");
        }

        while (!inAVendingMachine.isEmpty() && requestedBooks.size() < amount) {
            requestedBooks.add(inAVendingMachine.get(0));
            inAVendingMachine.remove(0);
        }

        while (requestedBooks.size() < amount) {
            int remaining = amount - requestedBooks.size();
            int toPrintAll = remaining + shelfSize;

            try {
                if (remaining > 0) {
                    int baches =(int) Math.ceil((double) toPrintAll / shelfSize);
                    int currentEdition = edition.get(bookID);

                    for (int i = 0; i < baches; i++) {
                        currentEdition++;
                        int bacheSize = Math.min(shelfSize, toPrintAll - (i * shelfSize));

                        for (int j = 0; j <bacheSize; j++) {
                            Book newBook = print(bookID, currentEdition);
                            if (requestedBooks.size() < amount) {
                                requestedBooks.add(newBook);
                            } 
                            else {
                                inAVendingMachine.add(newBook);
                            }
                        }
                    }

                    edition.put(bookID, currentEdition);
                    
                    for (Map.Entry<String, Integer> entry : edition.entrySet()) {
                        System.out.println("Book ID: " + entry.getKey() + ", Edition: " + entry.getValue());
        }

                }

            } catch (IOException e) {
                return new ArrayList<>();
            }
        }
        return requestedBooks;
    }
} 