package Multithreading;
import java.util.Scanner;

class Account{
	private int bal;
	public Account(int bal) {
		this.bal=bal;
	}
	public boolean isSufficientBalance(int w) {
		if(bal>w) {
			return(true);
		}else {
			return(false);
		}
	}
	public void withdraw(int amt) {
		bal=bal-amt;
		System.out.println("Withdrawl amount is "+amt);
		System.out.println("Your current balance is "+bal);
	}
}
class Customer implements Runnable{
	private String name;
	private Account account;
	public Customer(Account account, String n) {
		this.account = account;
		this.name = n;
	}
	public void run() {
		
		synchronized(account) {
			Scanner kb = new Scanner(System.in);
			System.out.println(name+", please enter the amount to withdraw");
			int amt = kb.nextInt();
			if(account.isSufficientBalance(amt)) {
			account.withdraw(amt);
		}else {
			System.out.println("Insufficient Balance");
		}
		}
		
		
	}
}
public class synchronization {

	public static void main(String[] args) {
		Account a1 = new Account(1000);
		Customer c1=new Customer(a1, "Raushan"), c2=new Customer(a1, "Raj");
		Thread t1 = new Thread(c1);
		Thread t2 = new Thread(c2);
		t1.start();
		t2.start();

	}

}
