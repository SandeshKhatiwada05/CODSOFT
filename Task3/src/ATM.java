import java.util.Scanner;

public class ATM implements ATMInterface{

    //Common for withdraw, deposit and checking balance
    public float amount(){
        System.out.println("Enter the Amount");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();
        return amount;
    }

    @Override
    public void withdrawing(float withdrawAmount) {
        System.out.println("Withdrawing amount....");
        Bank.amountAfterWithdrawing(withdrawAmount);
    }

    @Override
    public void deposit(float depositAmount) {
        System.out.println("Depositing amount....");
        Bank.amountAfterDeposit(depositAmount);
    }

    @Override
    public void checkingBalance() {
        System.out.println("Checking Balance....");
        Bank.checkAmount();

    }
}
