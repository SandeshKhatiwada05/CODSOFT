package org.JSP;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            printMenu();

            System.out.print("Option >> ");
            int chooser = -1;

            // Handle invalid input gracefully
            if(sc.hasNextInt()) {
                chooser = sc.nextInt();
                sc.nextLine(); // consume leftover newline
            } else {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine(); // clear invalid input
                continue;
            }

            System.out.println();

            switch (chooser) {
                case 1:
                    CurrencyLists.lists();
                    break;
                case 2:
                    new CurrencyConverter(sc); // pass scanner to reuse
                    break;
                case 3:
                    System.out.println("Thank you for using SANDESH's Currency Converter!");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Option. Please choose 1, 2, or 3.");
            }

            System.out.println("\n------------------------------\n");
        }
    }

    private static void printMenu() {
        System.out.println("========== Currency Converter ==========");
        System.out.println("1. See list of currency codes");
        System.out.println("2. Currency Exchange");
        System.out.println("3. Exit");
    }
}
