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
            System.out.println("Enter your guess:");
            String guess = sc.next();
            char[] guessArr = guess.toCharArray();

            if (guess.equalsIgnoreCase("quit")) {
                break;
            }

            System.out.println("Enter the results (G for green, Y for yellow, - for none)");
            String results = sc.next();
            char[] resultsArr = guess.toCharArray();

            if (results.equalsIgnoreCase("quit")) {
                break;
            }

            System.out.println("Here are your possible guesses:");
            attempts++;
        }
        sc.close();
    }
}
