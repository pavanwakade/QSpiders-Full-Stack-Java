package Banking;

import java.util.Objects;

public final class Accounts implements BankAccount {

	private long accountNumber;
	private String name;
	private double balance = 0.0;
	private String ifsc;
	private String branch;
	private String pin;

	public Accounts() {
		super();
	}

	/**
	 * @param accountNumber
	 * @param name
	 * @param balance
	 * @param ifsc
	 * @param branch
	 * @param pin
	 */
//	public Accounts(long accountNumber, String name, String ifsc, String branch, String pin) {
//		super();
//		this.accountNumber = accountNumber;
//		this.name = name;
////		this.balance = balance;
//		this.ifsc = ifsc;
//		this.branch = branch;
//		this.pin = pin;
//		System.out.println("Account Created SusessFully");
//	}

	@Override
	public void createAccount(long accountNumber, String name, String ifsc, String branch, String pin) {

		this.accountNumber = accountNumber;
		this.name = name;
		this.ifsc = ifsc;
		this.branch = branch;
		this.pin = pin;
		System.out.println("Account Created SusessFully");
	}

	@Override
	public void deposit(long accno, String name, double amt)
			throws NegativeBalanceException, InvalidCredentialException {
		if (amt > 0) {
			if (accno == this.accountNumber && name.equals(this.name)) {
				balance = balance + amt;
				System.out.println("Dear " + name + " yor Account is Creadited  " + amt);
			} else {
				throw new InvalidCredentialException("Invalid credentials. Please try again.");
			}
		} else {
			throw new NegativeBalanceException("Amount must be positive");
		}
	}

	@Override
	public void getBalance(long accno, String name, String pin) throws InvalidCredentialException {
		if (accno == this.accountNumber && name.equals(this.name) && pin.equals(this.pin)) {
			System.out.println("Your Account balance is " + balance);
		} else {
			throw new InvalidCredentialException("Invalid credentials. Please try again.");
		}
	}

	@Override
	public void withdraw(long accno, String name, double amt, String pin, String ifsc)
			throws NegativeBalanceException, InvalidCredentialException, InsufficientBalanceException {

		if (accno == this.accountNumber && pin.equals(this.pin) && name.equals(this.name) && ifsc.equals(this.ifsc)) {
			if (amt > 0) {
				if (balance - amt >= 0) {
					balance = balance - amt;
					System.out.println(amt + " Debited from your Account");
				} else {
					throw new InsufficientBalanceException("Insufficient balance. Please deposit funds.");
				}
			} else {
				throw new NegativeBalanceException("Amount must be positive");
			}
		} else {
			throw new InvalidCredentialException("Invalid credentials. Please try again.");
		}
	}

	@Override
	public void getAccountHolderName(long accountNumber) throws InvalidCredentialException {
		if (accountNumber == this.accountNumber) {
			System.out.println("Account Holder name is: " + this.name);
		} else {
			throw new InvalidCredentialException("Invalid credentials. Please try again.");
		}
	}

	@Override
	public void getAccountNumber(String name, String pin) throws InvalidCredentialException {
		if (name.equals(this.name) && pin.equals(this.pin)) {
			System.out.println("Account Number :" + this.accountNumber);

		} else {
			throw new InvalidCredentialException("Invalid credentials. Please try again.");
		}
	}
}
