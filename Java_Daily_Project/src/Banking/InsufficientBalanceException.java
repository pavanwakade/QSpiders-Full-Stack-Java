package Banking;

public class InsufficientBalanceException extends Exception {

	/**
	 * this is an Exception for Insufficient Balance
	 */
	public InsufficientBalanceException() {
		super();
	}

	public InsufficientBalanceException(String message) {
		super(message);
	}
}
