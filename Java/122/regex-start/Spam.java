import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Spam {
    public static void main(String[] args) throws IOException {
        // First read in the file
        String spam = getSpam();    

        Matcher matcher = null;
            
        // Part 6
        if (args[0].equals("0")) {
            // Define our pattern and matches
            matcher = matchFrom(spam);
            while(matcher.find()) {
                System.out.println(spam.substring(matcher.start(), matcher.end()));
            }
        }
        

        // Part 7
        if (args[0].equals("1")) {
            // Define our pattern and matches
            matcher = matchEmails(spam);

            // Loop through our matches
            while(matcher.find()) {
                System.out.println(spam.substring(matcher.start(), matcher.end()));
            }
        }
        
        // Part 8
        if (args[0].equals("2")) {
            // Define our pattern and matches
            matcher = matchSenders(spam);

            SortedSet<String> uniqueEmails = new TreeSet<>();

            // Loop through our matches
            while(matcher.find()) {
                uniqueEmails.add(matcher.group(1));
            }

            for (String email : uniqueEmails) {
                System.out.println(email);
            }
        }


    }

    public static String getSpam() throws IOException {
        File file = new File("./Spam.txt");
        String fileContent = Files.readString(file.toPath());

        return fileContent;
    }

    // Part 6
    public static Matcher matchFrom(String input) {
        Pattern pattern = Pattern.compile("From:.*"); // CHANGE ME

        // Instantiate our pattern matcher object
        Matcher matcher = pattern.matcher(input);

        return matcher;
    }

    // Part 7
    public static Matcher matchEmails(String input) {
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}"); // CHANGE ME

        // Instantiate our pattern matcher object
        Matcher matcher = pattern.matcher(input);

        return matcher;
    }

    // Part 8
    public static Matcher matchSenders(String input) {
        Pattern pattern = Pattern.compile("From:.*?<([^<>]+)>"); // CHANGE ME

        // Instantiate our pattern matcher object
        Matcher matcher = pattern.matcher(input);

        return matcher;
    }
}
