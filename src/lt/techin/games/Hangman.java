package lt.techin.games;

import java.util.Random;
import java.util.Scanner;

public class Hangman {

    public static void main(String[] args) {
        String[] words = {"polymorphism", "inheritance", "encapsulation", "interface", "abstract", "collection", "method", "programming"};
        Scanner scanner = new Scanner(System.in);
        boolean wantsToPlay = true;

        while (wantsToPlay) {
            String word = prepareWord(words);
            boolean isFinished = false;
            int guessedTotal = 0;
            int missed = 0;
            char[] wordCopy = new char[word.length()];

            printWordCopyEncoded(word, wordCopy);

            while (!isFinished) {
                boolean ifMissed = true;
                char guess = getGuess(scanner);

                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == guess && wordCopy[i] == '*') {
                        guessedTotal++;
                        wordCopy[i] = word.charAt(i);
                        ifMissed = false;
                    }
                }
                if (ifMissed) {
                    missed++;
                }

                isFinished = checkIfGuessed(word, isFinished, guessedTotal, missed, wordCopy);
            }

            wantsToPlay = checkIfShouldContinue(scanner);
        }
    }

    private static boolean checkIfShouldContinue(Scanner scanner) {
        System.out.println("Do you want to play again? Enter Y for yes, N for no.");
        String input = scanner.nextLine();
        if (input.equals("N") || input.equals("n")) {
            System.out.println("See you next time!");
            return false;
        } return true;
    }

    private static boolean checkIfGuessed(String word, boolean isFinished, int guessedTotal, int missed, char[] wordCopy) {
        if (guessedTotal == word.length()) {
            isFinished = true;
            System.out.println("\nGood job! The word was * " + word + " *.");
            System.out.println("You missed or entered already guessed letter " + missed + " time(s).");
        } else {
            System.out.print("Guess the word: ");
            for (char guessedLetter : wordCopy) {
                System.out.print(guessedLetter);
            }
        }
        return isFinished;
    }

    private static void printWordCopyEncoded(String word, char[] wordCopy) {
        for (int i = 0; i < word.length(); i++) {
            wordCopy[i] = '*';
        }
    }

    private static char getGuess(Scanner scanner) {
        System.out.println("\nPlease enter a letter (use only lower case letters):");
        String input = scanner.nextLine();
        char guess = input.charAt(0);
        return guess;
    }

    private static String prepareWord(String[] words) {
        Random random = new Random();
        int index = random.nextInt(words.length);
        String word = words[index];
        System.out.print("Guess the word: ");
        for (int i = 0; i < word.length(); i++) {
            System.out.print("*");
        }
        return word;
    }
}
