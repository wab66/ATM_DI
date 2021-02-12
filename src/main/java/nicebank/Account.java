package nicebank;

import org.javalite.activejdbc.Model;

public class Account extends Model{

	private TransactionQueue queue = new TransactionQueue();

	public Account(){}

	public Account(int number) {
		//rc02d this.queue = queue;
		System.out.println("[Account] > Account() > 1. before setInteger()");
		setInteger("number", number);
		setString("balance", "0.00");
		System.out.println("[Account] > Account() > 2. after setInteger() -> Account Count: " +
				Account.count() + ", Account Table name : " + Account.getTableName() +
				", Account first record :" + Account.findFirst("number = ?", 1234));
	}

	public void credit(Money amount) {
		System.out.println("##################### [Account] > credit() > Amount to be credited (write to Message Queue (" + amount.toString() + ") +  to account (" + getNumber() + ")");
		queue.write("+" + amount.toString() + "," + getNumber());
	}

	public void debit(int amount) {
		Money money = new Money(amount, 0);
		queue.write("-" + money.toString() + "," + getNumber());
	}

	// Use DB
	public int getNumber() {
		return getInteger("number");
	}

	// Use DB
	public Money getAccountBalance() {
		System.out.println("[Account] > getAccountBalance() > about to do a 'refresh()'.  Current Balance before refresh: "+ getString("balance"));
		refresh();
		System.out.println("[Account] > getAccountBalance() > now get account balance");
		return new Money(getString("balance"));
	}

	// Use DB
	public void setAccountBalance(Money amount) {
		setString("balance", amount.toString().substring(1));
		saveIt();
	}
}