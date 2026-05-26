package codsoft;

import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

public class NumberGame{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int score = 0;
        char playAgain;

        System.out.println("===== NUMBER GUESSING GAME =====");

        do {
            int randomNumber = random.nextInt(100) + 1;
            int attempts = 0;
            int maxAttempts = 5;
            boolean guessedCorrectly = false;

            System.out.println("\nGuess the number between 1 and 100");
            System.out.println("You have " + maxAttempts + " attempts.");

            while (attempts < maxAttempts) {

                int userGuess = 0;

                try {
                    System.out.print("Enter your guess: ");
                    userGuess = sc.nextInt();

                    
                    if (userGuess < 1 || userGuess > 100) {
                        System.out.println(" Please enter a number between 1 and 100.");
                        continue;
                    }

                    attempts++;

                    if (userGuess == randomNumber) {
                        System.out.println(" Correct! You guessed the number.");
                        System.out.println("Attempts used: " + attempts);

                        score++;
                        guessedCorrectly = true;
                        break;

                    } else if (userGuess > randomNumber) {
                        System.out.println(" Too high!");

                    } else {
                        System.out.println(" Too low!");
                    }

                    System.out.println("Remaining attempts: " + (maxAttempts - attempts));

                } catch (InputMismatchException e) {
                    System.out.println(" Invalid input! Please enter only numbers.");
                    sc.next(); 
                }
            }

            if (!guessedCorrectly) {
                System.out.println("\n You lost!");
                System.out.println("The correct number was: " + randomNumber);
            }

            System.out.println("Current Score: " + score);
            while (true) {
                try {
                    System.out.print("\nDo you want to play again? (y/n): ");
                    playAgain = sc.next().charAt(0);

                    if (playAgain == 'y' || playAgain == 'Y' ||
                        playAgain == 'n' || playAgain == 'N') {
                        break;
                    } else {
                        System.out.println(" Please enter only y or n.");
                    }

                } catch (Exception e) {
                    System.out.println(" Invalid input!");
                    sc.nextLine();
                }
            }

        } while (playAgain == 'y' || playAgain == 'Y');

        System.out.println("\n Thanks for playing!");
        sc.close();
    }
}