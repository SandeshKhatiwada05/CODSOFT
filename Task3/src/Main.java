import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Sandesh's ATM");

        System.out.println("What operation would you like to perform?");
        do {
            System.out.println("1. Withdraw\n2. Deposit\n3. Check Balance\n4. Exit\n");
            Scanner sc = new Scanner(System.in);
            int option = sc.nextInt();
            ATM atm = new ATM();

            switch (option) {
                case 1:
                    atm.withdrawing(atm.amount());
                    break;
                case 2:
                    atm.deposit(atm.amount());
                    break;
                case 3:
                    atm.checkingBalance();
                    break;
                case 4:
                    System.out.println("Exiting....");
                    System.exit(0);

                default:
                    System.out.println("Invalid Option");
            }
        }while (true);
    }
}