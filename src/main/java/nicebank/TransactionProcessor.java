package nicebank;

import org.javalite.activejdbc.Base;

public class TransactionProcessor {
    private TransactionQueue queue = new TransactionQueue();

    public void process() {
        if (!Base.hasConnection()){
            Base.open(
                    //"com.mysql.jdbc.Driver",
                    "com.mysql.cj.jdbc.Driver",
                    "jdbc:mysql://localhost/bank",
                    "teller", "password");
            //System.out.println("1************** [TransactionProcessor] > process() -> 1a. Base.open - has connection created?: " + Base.hasConnection() + " Connection: " + Base.connection());
            System.out.println("1************** [TransactionProcessor] > process() -> 1b. Base.open - Account Table Count: " +
                    Base.count("bank.accounts"));
        }
        else {
            //System.out.println("2************** [TransactionProcessor] > process() -> 2a. Base Already open - has connection created?: " + Base.hasConnection() + " Connection: " + Base.connection());
            System.out.println("2************** [TransactionProcessor] > process() -> 2b. Base.open - Account Table Count: " +
                    Base.count("bank.accounts"));
        }

        do {
            //System.out.println("**************[TransactionProcessor] > process() > do loop -> queue.read() > 4/4. ");
            String message = queue.read();

            if (message.length() > 0) {
                String[] parts = message.split(",");
                Money transactionAmount = new Money(parts[0]);
                System.out.println("!!!!!!!!!!!!!!!!!!!!! (FAILING - HERE) <[TransactionProcessor] > process() > 1. do loop -> Message found: " + message);
                Account account = Account.findFirst("number = ?", parts[1]);
                if (account == null) {
                    throw new RuntimeException("Account number not found: " + parts[1]);
                }
                System.out.println("!!!!!!!!!!!!!!!!!!!!! <[TransactionProcessor] > process() > 2. do loop -> Account: " + account + ", Amount: " +
                        transactionAmount);

                if (isCreditTransaction(message)){
                    System.out.println("!!!!!!!!!!!!!!!!!!!!! [TransactionProcessor] > process() > 3a. do loop -> Add to account bal: " + account.getAccountBalance() + ", amount: " + transactionAmount);
                    account.setAccountBalance(account.getAccountBalance().add(transactionAmount));
                } else {
                    System.out.println("!!!!!!!!!!!!!!!!!!!!! [TransactionProcessor] > process() > 3b. do loop -> Subtract from account bal: " + account.getAccountBalance() + ", amount: " + transactionAmount);
                    account.setAccountBalance(account.getAccountBalance().minus(transactionAmount));
                }
            }
        } while (true);
    }

    private boolean isCreditTransaction(String message) {
        return !message.startsWith("-");
    }

// rc02 - now using DB
//    public void process() {
//        do {
//            String message = queue.read();
//
//            // Deliberately slow it down - so feature will work
//            try {
//                Thread.sleep(4000);
//                System.out.println("+++++++++++++++++++++++++++++[TransactionProcessor] > [process]: Thread.sleep: 1000ms");
//            } catch (InterruptedException e) {
//
//            }
//
//            if (message.length() > 0) {
//                System.out.println("+++++++++++++++++++++++++++++[TransactionProcessor] > [process]: Message found: " + message);
//                Money balance = BalanceStore.getBalance();
//                System.out.println("+++++++++++++++++++++++++++++[TransactionProcessor] > [process]: BalanceStore.getBalance: " + balance);
//                Money transactionAmount = new Money(message);
//                System.out.println("+++++++++++++++++++++++++++++[TransactionProcessor] > [process]: Transaction amount: " + transactionAmount);
//
//                if (isCreditTransaction(message)){
//                    BalanceStore.setBalance(balance.add(transactionAmount));
//                    System.out.println("+++++++++++++++++++++++++++++[TransactionProcessor] > [process]: Add amount: " + transactionAmount + " to Balance amount: " + balance);
//                } else {
//                    BalanceStore.setBalance(balance.minus(transactionAmount));
//                    System.out.println("+++++++++++++++++++++++++++++[TransactionProcessor] > [process]: Deduct amount: " + transactionAmount + " from Balance amount: " + balance);
//                }
//            }
//        } while (true);
//    }
}
