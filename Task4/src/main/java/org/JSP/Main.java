package org.JSP;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        do{
            System.out.println("Choose an option");
            System.out.println("\n1. See lists of currency codes\n2. Currency Exchange\n3. Exit");
            Scanner sc = new Scanner(System.in);
            int chooser = sc.nextInt();
            System.out.println("Option: ");

            switch (chooser){
                case 1:
                    CurrencyLists.lists();
                    break;
                case 2:
                    new CurrencyConverter();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid Option");
            }
        }while (true);
    }
}