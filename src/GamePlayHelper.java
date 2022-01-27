import java.util.ArrayList;
import java.util.Scanner;

import javax.sound.sampled.SourceDataLine;

public class GamePlayHelper {
    public static void main(String[] args) {
        System.out.println("Welcome to the game helper!");
        System.out.println("Type quit anytime to quit.");
        runGameHelper();
        System.out.println("Thanks for playing!");
    }

    private static void runGameHelper() {
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
            char[] resultsArr = guess.toCharArray();

            if (results.equalsIgnoreCase("quit")) {
                break;
            }

            System.out.println("Here are your possible guesses:");
            generateGuesses(guessArr, resultsArr);

            System.out.println();
            System.out.println();
        }
        sc.close();
    }

    private static void generateGuesses(char[] guesses, char[] results) {
        // gather green letters
        ArrayList<Character> greens = new ArrayList<>();
        for (char result : results) {
            if (result == 'G') {
                greens.add(result);
            }
        }
    }
}
