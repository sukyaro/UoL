/**
 * COMP122 CA1 Assignment (Brutus.java)
 *
 * Yaro Lebedysnkyi
 * Student ID: 201858746
 */


class Brutus {
    public static final double[] english = {
        0.0855, 0.0160, 0.0316, 0.0387, 0.1210, 0.0218, 0.0209, 0.0496, 0.0733,
        0.0022, 0.0081, 0.0421, 0.0253, 0.0717, 0.0747, 0.0207, 0.0010, 0.0633,
        0.0673, 0.0894, 0.0268, 0.0106, 0.0183, 0.0019, 0.0172, 0.0011
    };


    /**
    * Main method of the class
    *
    * <p> Checking that the right number of arguments have been passed to the terminal
    * 
    * <p> Looping and calling the method to rotate the characters in a string
    * and comparing the chi-squared score for every output to find the lowest value
    * 
    * @param args from the terminal
   */
    public static void main(String[] args) {
        if (args.length > 1) {
            System.out.println("Too many parameters!\nUsage: java Brutus \"cipher text\"");
            return;
        }

        if (args.length < 1) {
            System.out.println("Too few parameters!\nUsage: java Brutus \"cipher text\"");
            return;
        }
        

        String ourString = args[0];

        // Creating a string where the decrepted string is stored 
        // And setting the minimal ChiScore to infinity so it changes after the first itteration
        String decreptedSting = "";
        double minChiScore = Double.POSITIVE_INFINITY;
        
        // Looping 26 times so that we can check every possible shift
        for (int i = 0; i < 26; i++) {
            String newString = Caesar.rotate(i, ourString);
            double[] letterFreq = frequency(newString);
            double chiScore = chiSquared(letterFreq, english);
            
            if (chiScore < minChiScore) {
                minChiScore = chiScore;
                decreptedSting = newString;
            }
        }
        System.out.println(decreptedSting);
    }


    /**
    * Counting the number of every char instances in a string
    *
    * <p> Making the string lowercase
    * 
    * <p> Going through every single character adding their existence into the array
    * 
    * @param String the string to consider
    * @return an array with the number of every single char in the string
    */
    public static int[] count(String strLower) {
        strLower = strLower.toLowerCase();
        int[] counter = new int[26];

        // Using "char-substraction" to perform counting the letters
        for (int i = 0; i < strLower.length(); i++) {
            char currentChar = strLower.charAt(i);
            if (currentChar >= 'a' && currentChar <= 'z') {
                counter[currentChar - 'a']++;
            }
        }

        return counter;
    }
    
    /**
    * Calculating the actual length of the string (excluding the spaces and punctuation)
    * 
    * <p> Going through every symbol in the string and checking if it is a letter
    * if it is a letter then adding it to a counter
    * 
    * @param String the string to consider
    * @return an integer of the letters in the string
    */
    public static int countLength(String string) {
        int lengthCount = 0;

        for (int i = 0; i < string.length(); i++) {
            char newChar = string.charAt(i);
            if (Character.isLetter(newChar) == true) {
                lengthCount += 1;
            }
        }
    
        return lengthCount;
    }

    // Checking how often a char occurs in a string
    /**
    * Calculating the frequency of every letter in a string
    *
    * <p> Making the string lowercase
    * 
    * <p> Getting the length of the string (excluding the spaces and punctiation)
    * 
    * <p> Going through every single character and deviding the number of occurance
    * by the number of all letters in a string
    * 
    * @param String the string to consider
    * @return an array with the frequency of every single char in the string
    */
    public static double[] frequency(String ourStringLow) {
        ourStringLow = ourStringLow.toLowerCase();
        int charLength = countLength(ourStringLow);
        int[] counter = count(ourStringLow);
        double[] letterFreq = new double[26];

        for (int i = 0; i <= 25; i++) {        
            letterFreq[i] = (double) counter[i] / charLength;
        }

        return  letterFreq;
    }
    
    /**
    * Calculating the chi-square score of a rotated string using the formula
    *
    * <p> First, setting the score to 0
    * 
    * <p> Going through every single character and checking its frequency with the given array of sequences
    * 
    * @param double[] the frequency of every letter and the english frequency table
    * @return a double of the score of a string.
    */
    public static double chiSquared(double[] letterFreq, double[] englishFrequency) {
        double chiScore = 0;
        for (int i = 0; i <= 25; i++) {
            chiScore += Math.pow((letterFreq[i] + englishFrequency[i]), 2) / englishFrequency[i];
        }

        return chiScore; 
    }
}