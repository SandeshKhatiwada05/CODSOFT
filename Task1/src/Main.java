import java.util.Scanner;
import java.util.random.*;
public class Main {
    public static void main(String[] args) {
        System.out.println("Task 1: ");
        //First we generate a random number between 0 and 100
        int computerGuessed = (int)(Math.random()*101);
        int humanGuessed=-1674; //to make sure user doesn't match the number

        System.out.println("Enter a number between 1 and 100");

        while(computerGuessed!=humanGuessed){
            Scanner sc = new Scanner(System.in);
            humanGuessed = sc.nextInt();
            if(humanGuessed>computerGuessed){
                System.out.println("Guess lower value");
            }
            else if (humanGuessed<computerGuessed){
                System.out.println("Guess Higher value");
            }
        }

        System.out.println("Congrats you guessed the number "+ computerGuessed +" correctly!");
    }
}