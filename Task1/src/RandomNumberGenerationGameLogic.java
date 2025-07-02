import java.util.Scanner;

public class RandomNumberGenerationGameLogic {
    public RandomNumberGenerationGameLogic() {
        System.out.println("Task 1: ");

        int round = 0;
        int option;

        Scanner sc = new Scanner(System.in); // Declare scanner once

        do {
            System.out.println("Click 1 to Play and 0 to Exit");
            option = sc.nextInt();

            if (option == 1) {
                int computerGuessed = 1 + (int) (Math.random() * 100);
                int humanGuessed = -1;
                int attempts = 0;
                int maxAttempts = 9;

                System.out.println("Enter a number between 1 and 100");
                System.out.println("You have " + maxAttempts + " attempts to guess the number.");

                while (computerGuessed != humanGuessed && attempts < maxAttempts) {
                    humanGuessed = sc.nextInt();

                    if (humanGuessed < 1 || humanGuessed > 100) {
                        System.out.println("Your guess must be between 1 and 100. Try again.");
                        continue;
                    }

                    attempts++;

                    if (humanGuessed > computerGuessed) {
                        System.out.println("Guess lower value");
                    } else if (humanGuessed < computerGuessed) {
                        System.out.println("Guess higher value");
                    }

                    if (humanGuessed == computerGuessed) {
                        System.out.println("Congrats! You guessed the number " + computerGuessed + " correctly!");
                        round++;
                        break;
                    }

                    if (attempts < maxAttempts) {
                        System.out.println("Attempts left: " + (maxAttempts - attempts));
                    }
                }

                if (humanGuessed != computerGuessed) {
                    System.out.println("Sorry! You've used all " + maxAttempts + " attempts.");
                    System.out.println("The correct number was: " + computerGuessed);
                }

            } else if (option == 0) {
                System.out.println("Thanks for playing!");
                System.out.println("Your Score (Rounds Won): " + round);
                break;
            } else {
                System.out.println("Choose a valid option (1 to Play, 0 to Exit).");
            }

        } while (true);

        sc.close();
    }

    public static void main(String[] args) {
        new RandomNumberGenerationGameLogic();
    }
}
