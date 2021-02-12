package nicebank;

public class AutomatedTeller implements Teller {
    private CashSlot cashSlot;

    public AutomatedTeller(CashSlot cashSlot) {
        this.cashSlot = cashSlot;
    }

    @Override
    public void withdrawFrom(Account account, int amount) {
        System.out.println("1=========================== <Debit and Dispense> [AutomatedTeller] > (@OVERRIDE > ITeller) > withdrawFrom(): " +
                " CashSlot contents before we dispense: " + cashSlot.getContents());
        account.debit(amount);
        cashSlot.dispense(amount);
        System.out.println("2=========================== <Debit and Dispense> [AutomatedTeller] > (@OVERRIDE > ITeller) > withdrawFrom() - Account: " +
                account + ", the amount of: " + amount + ", CashSlot contents amount: " + cashSlot.getContents());
    }
}
