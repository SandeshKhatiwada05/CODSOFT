public class Bank {

    static float amount = 0;

    public static float amountAfterWithdrawing(float withdrawAmount){
        amount = amount - withdrawAmount;
        return amount;
    }

    public static float amountAfterDeposit(float depositAmount){
        amount = amount + depositAmount;
        return amount;
    }

    public static float checkAmount(){
        return amount;
    }

}

