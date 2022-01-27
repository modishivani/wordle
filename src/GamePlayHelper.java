import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class GamePlayHelper {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Welcome to the game helper!");
        System.out.println("Type quit anytime to quit.");
        runGameHelper();
        System.out.println("Thanks for playing!");
    }

    private static void runGameHelper() throws FileNotFoundException {
        int attempts = 0;
        Scanner sc = new Scanner(System.in);
        while (attempts != 6) {
            attempts++;
            System.out.println("Attempt number " + attempts);
            System.out.println("Enter your guess:");
            String guess = sc.next();
            char[] guessArr = guess.toCharArray();

            if (guess.equalsIgnoreCase("quit")) {
                break;
            }

            System.out.println("Enter the results (G for green, Y for yellow, - for none)");
            System.out.println("Ex: GYY-G");
            String results = sc.next();
            char[] resultsArr = results.toCharArray();

            if (results.equalsIgnoreCase("quit")) {
                break;
            }

            if (results.equalsIgnoreCase("GGGGG")) {
                System.out.println("You won!");
                break;
            }

            System.out.println("Here are your possible guesses:");
            System.out.println(generateGuesses(guessArr, resultsArr));

            System.out.println();
            System.out.println();
        }
        sc.close();
    }

    private static ArrayList<String> generateGuesses(char[] guesses, char[] results) throws FileNotFoundException {
        // gather green letters
        TreeMap<Character, Integer> greens = new TreeMap<>();
        for (int i = 0; i < results.length; i++) {
            if (results[i] == 'G' || results[i] == 'g') {
                greens.put(guesses[i], i);
            }
        }

        TreeMap<Character, Integer> yellows = new TreeMap<>();
        for (int i = 0; i < results.length; i++) {
            if (results[i] == 'Y' || results[i] == 'y') {
                yellows.put(guesses[i], i);
            }
        }


        ArrayList<String> allWords = new ArrayList<>();
        Scanner sc = new Scanner(new File("words/guesses.txt"));
        
        while (sc.hasNext()) {
            allWords.add(sc.next());
        }

        ArrayList<String> validGuesses = new ArrayList<>();
        for (String w: allWords) {
            if (checkValid(greens, yellows, w)) {
                validGuesses.add(w);
            }
        }
    
        return validGuesses;
    }

    private static boolean checkValid(Map<Character, Integer> greens, Map<Character, Integer> yellows, String w) {
        for (Character c: greens.keySet()) {
            if (w.charAt(greens.get(c)) != c) {
                return false;
            }
        }

        for (Character y: yellows.keySet()) {

            //not valid if the yellow letter is not there or if the yellow letter is at the same place as previously
            if (w.indexOf(y) == -1 || w.indexOf(y) == yellows.get(y)) { //index of will return -1 if the character is not found
                return false;
            }
        }

        return true;
    }
}

