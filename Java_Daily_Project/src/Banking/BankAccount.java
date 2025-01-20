package Banking;

public interface BankAccount {

	void createAccount(long accountNumber, String name, String ifsc, String branch, String pin);

	void deposit(long accno, String name, double amt) throws NegativeBalanceException, InvalidCredentialException;

	void getBalance(long accno, String name, String pin) throws InvalidCredentialException;

	void withdraw(long accno, String name, double amt, String pin, String ifsc)
			throws NegativeBalanceException, InvalidCredentialException, InsufficientBalanceException;

	void getAccountHolderName(long accountNumber) throws InvalidCredentialException;

	void getAccountNumber(String name, String pin) throws InvalidCredentialException;

}