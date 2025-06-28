import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * a class for parsing structured text files containing program descriptions and test results
 * the structured text file should follow a specific format with fields separated by hyphens
 * each program description is separated by a blank line
 */
public class IOLabPartOne1 {

    /**
     * default constructor
     */
    public IOLabPartOne1() {
        //Don't touch, used for testing your code
    }
    
    /**
     * constructor that takes command line arguments and processes the input file
     * demonstrates the functionality by parsing "programs.txt" and printing the results
     *
     * @param args Command line arguments (not currently used)
     * @throws IOException If there's an error reading the input file
     */
    public IOLabPartOne1(String[] args) throws IOException {
        //You can uncomment the code below to test if your function is correct
        //Just run the code and then compare the output in the terminal to programs.txt
        File file = new File("programs.txt");
        ArrayList<OurData> data = parseStructuredTextFile(file.toPath());
        for (int i = 0; i < data.size(); i++) {
            System.out.println("Program Description " + (i+1));
            data.get(i).printData();
            System.out.println();
        }
    }
    
    /**
     * main method that creates an instance of IOLabPartOne with the given arguments
     *
     * @param args command line arguments
     * @throws IOException if there's an error reading the input file
     */
    public static void main(String[] args) throws IOException {
        new IOLabPartOne1(args);
    }

    /**
     * parses a structured text file containing program descriptions and test results
	 * 
     * @param path the path to the structured text file to parse
     * @return an ArrayList of OurData objects containing the parsed data
     * @throws IOException if there's an error reading the input file
     */
    public ArrayList<OurData> parseStructuredTextFile(Path path) throws IOException {
        String[] fieldNames = {"problem", "program", "program_length", "passed_tests", "total_tests", "correct"};
        ArrayList<OurData> ourDataObjects = new ArrayList<>();
    
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String str;
            OurData currentData = null;
    
            while ((str = br.readLine()) != null) {
                if (str.isBlank()) {
                    if (currentData != null) {
                        ourDataObjects.add(currentData);
                        currentData = null;
                    }
                }
    
                if (str.startsWith("Program_Description")) {
                    currentData = new OurData(fieldNames);
                }
    
                String[] parts = str.split("-", 2);

                // if blank line, then there is only 1 part which creates index out of bounds so just skip
                if (parts.length < 2) continue;
    
                String fieldName = parts[0].trim();
                String value = parts[1].trim();
    
                if (fieldName.equals("problem") || fieldName.equals("program")) {
                    currentData.setField(fieldName, value);
                } else if (
                       fieldName.equals("total_tests")
                    || fieldName.equals("passed_tests")
                    || fieldName.equals("program_length")) {
                        currentData.setField(fieldName, Integer.parseInt(value));
                } else if (fieldName.equals("correct")) {
                    currentData.setField(fieldName, Boolean.parseBoolean(value));
                }
            }
            
            if (currentData != null) {
                ourDataObjects.add(currentData);
            }
        }
    
        return ourDataObjects;
    }
}
