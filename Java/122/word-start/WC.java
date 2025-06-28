
import java.util.*;

public class WC {
    public static void main(String[] args) {
        if (args[0].equals("-w")) {
            System.out.println(wordCount(args[1]));
        }

        if (args[0].equals("-m")) {
            System.out.println(charCount(args[1]));
        }

        if (args[0].equals("-l")) {
            System.out.println(lineCount(args[1]));
        }

        if (args[0].equals("-s")) {
            System.out.println(lexDiversity(args[1]));
        }

        if (args[0].equals("-b")) {
            System.out.println(bugOfWords(args[1]));
        }

        if (args[0].equals("-v")) {
            int[][] vectors = docVectors(args[1], args[2]);
            System.out.println(Arrays.toString(vectors[0]));
            System.out.println(Arrays.toString(vectors[1]));
        }

        if (args[0].equals("-d")) {
            System.out.println(euclideanDist(args[1], args[2]));
        }
    }

    private static int wordCount(String input) {
        String[] words = input.split("\\s");
        return words.length;
    }


    private static int charCount(String input) {
        return input.length();
    }


    private static int lineCount(String input) {
        String[] lines = input.split("\\R");
        return lines.length;
    }


    private static int distWords (String input) { 
        String[] allWords = input.split("\\s");
        HashSet<String> distinctWords = new HashSet<>();
        for (int i = 0; i < allWords.length; i++) {
            distinctWords.add(allWords[i]);
        }
        return distinctWords.size();
    }


    private static double lexDiversity(String input) {
        double percent = (double) distWords(input) / wordCount(input);
        return percent;
    }


    private static String bugOfWords(String input) {
        List<String> words = new ArrayList<>(Arrays.asList(input.toLowerCase().split("\\s")));
        int index = 0;
        String output = "";
        LinkedHashMap <String, Integer> wordCounter = new LinkedHashMap<>();
        Collections.sort(words);

        for (String word : words) {
            wordCounter.put(word, wordCounter.getOrDefault(word, 0) + 1);
        }

        int[] bugCounter = new int[wordCounter.size()];
        for (int count : wordCounter.values()) {
            bugCounter[index] = count;
            index++;
        }

        output = Arrays.toString(bugCounter);
        return output;
    }


    private static int[][] docVectors(String str1, String str2) {
        List<String> str3 = new ArrayList<>(Arrays.asList((str1 + " " + str2).toLowerCase().split("\\s")));
        String[] array1 = str1.toLowerCase().split("\\s"), array2 = str2.toLowerCase().split("\\s");

        LinkedHashMap <String, Integer> wordCounter1 = new LinkedHashMap<>();
        LinkedHashMap <String, Integer> wordCounter2 = new LinkedHashMap<>();
        Collections.sort(str3);

        for (String word : str3) {
            wordCounter1.put(word, 0);
            wordCounter2.put(word, 0);
        }

        for (String word : array1) {
            wordCounter1.put(word, wordCounter1.getOrDefault(word, 0) + 1);
        }

        for (String word : array2) {
            wordCounter2.put(word, wordCounter2.getOrDefault(word, 0) + 1);
        }

        int[] vector1 = wordCounter1.values().stream().mapToInt(Integer::intValue).toArray();
        int[] vector2 = wordCounter2.values().stream().mapToInt(Integer::intValue).toArray();

        return new int[][]{vector1, vector2};
    }


    private static double euclideanDist(String str1, String str2) {
        int[][] vectors = docVectors(str1, str2);
        int[] vector1 = vectors[0];
        int[] vector2 = vectors[1];
        double result = 0;

        for(int i = 0; i < vector1.length; i++) {
            result += Math.pow(vector1[i] - vector2[i], 2);
        }

        return Math.sqrt(result);
    }
}