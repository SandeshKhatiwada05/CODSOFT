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

    }

    @Override
    public void deposit(float depositAmount) {

    }

    @Override
    public void checkingBalance() {

    }
}
