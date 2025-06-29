import java.util.Scanner;

public class RandomNumberGenerationGameLogic {
    public RandomNumberGenerationGameLogic() {
        System.out.println("Task 1: ");
        //First we generate a random number between 1 and 100
        int computerGuessed = 1 + (int) (Math.random() * 101);
        int humanGuessed = -1674; //to make sure user doesn't match the number

        System.out.println("Enter a number between 1 and 100");

        while (computerGuessed != humanGuessed) {
            Scanner sc = new Scanner(System.in);

            humanGuessed = sc.nextInt();
            //for incorrect guess
            if (humanGuessed < 1 || humanGuessed > 100) {
                System.out.println("Your guess must be between 1 and 100. Try again.");
                continue;
            }
            if (humanGuessed > computerGuessed) {
                System.out.println("Guess lower value");
            } else if (humanGuessed < computerGuessed) {
                System.out.println("Guess Higher value");
            }
        }

        System.out.println("Congrats you guessed the number " + computerGuessed + " correctly!");
    }
}
