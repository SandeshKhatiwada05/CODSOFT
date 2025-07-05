import java.util.Scanner;

public class ATM implements ATMInterface{

    //Common for withdraw, deposit and checking balance
    public float amount(){
        System.out.print("Enter the Amount: ");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();
        return amount;
    }

    @Override
    public void withdrawing(float withdrawAmount) {
        System.out.println("Withdrawing amount....");
        if(Bank.amount>=withdrawAmount) {
            Bank.amountAfterWithdrawing(withdrawAmount);
            System.out.println("Withdraw success, new amount " + Bank.checkAmount());
        }
        else {
            System.out.println("Withdraw Failed");
            System.out.println("Your balance           "+ Bank.amount);
            System.out.println("Your withdraw Request: "+ withdrawAmount);
        }
        randomFormattedSeperator();
    }

    @Override
    public void deposit(float depositAmount) {
        System.out.println("Depositing amount....");
        Bank.amountAfterDeposit(depositAmount);
        System.out.println("Deposit success, new amount "+ Bank.checkAmount());
        randomFormattedSeperator();
    }

    @Override
    public void checkingBalance() {
        System.out.println("Checking Balance....");
        float amnt = Bank.checkAmount();
        System.out.println("Your Balance is "+amnt);
        randomFormattedSeperator();
    }

    public static void randomFormattedSeperator(){
        System.out.println("=========================");
    }
}
