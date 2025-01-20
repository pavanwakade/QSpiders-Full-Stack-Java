package inheritance.single;
/***
 * Parent class of Accounts saving .......
 */
public class Bankaccount {
	
	String bankname;
	String accountName ;
	String ifsc;
	String branch ;
	
	//default constructor
	public Bankaccount() {
		
	}

	public Bankaccount(String bankname, String accountName, String ifsc, String branch) {
		this.bankname = bankname;
		this.accountName = accountName;
		this.ifsc = ifsc;
		this.branch = branch;
	}
	
	public void displayBankAccount()
	{
		System.out.println("Bank Name       :"+bankname);
		System.out.println("account Name    :"+accountName);
		System.out.println("ifsc Code       :"+ifsc);
		System.out.println("branch Location :"+branch);
	}
	

}
