package Banking;

public class BankAccountDriver {

	public static void main(String[] args) {

		try {
			Accounts ac = new Accounts();

			ac.createAccount(123456l, "pavan", "ABC123", "pune", "pavan@123");

			ac.getBalance(123456l, "pavan", "pavan@123");

			ac.deposit(123456, "pavan", 10000);

			ac.getBalance(123456l, "pavan", "pavan@123");

			ac.getAccountHolderName(123456l);

			ac.getAccountNumber("pavan", "pavan@123");

			ac.withdraw(123456l, "pavan", 1000, "pavan@123", "ABC123");

			ac.getBalance(123456l, "pavan", "pavan@123");

		} catch (InvalidCredentialException e) {

			System.out.println(" invalid Credential ");

		}

		catch (InsufficientBalanceException In) {

			System.out.println(" Insufficient Balance Please Deposit ");

		}

		catch (NegativeBalanceException Ng) {

			System.out.println(" Balance can not be Negetive ");

		}
	}
}
