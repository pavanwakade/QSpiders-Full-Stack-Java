package inheritance.single;

public class SavingAccount extends Bankaccount
{
	long accountNo ;
	String type ;
	double balance ;
	
	public SavingAccount()
	{}

	public SavingAccount(String bankname, String accountName, String ifsc, String branch,long accountNo, String type, double balance) 
	{
		this.accountNo = accountNo;
		this.type = type;
		this.balance = balance;
		this.bankname = bankname;
		this.accountName = accountName;
		this.ifsc = ifsc;
		this.branch = branch;
	}
	
	public void DisplaySavingAcc() 
	{
		displayBankAccount();
		System.out.println("Account Number  :"+accountNo);
		System.out.println("Account type    :"+type);
		System.out.println("Account balance :"+balance);
	}
	
	
	
}
