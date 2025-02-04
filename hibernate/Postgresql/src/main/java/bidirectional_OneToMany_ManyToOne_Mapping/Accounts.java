package bidirectional_OneToMany_ManyToOne_Mapping;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Accounts {

	@Id
	private int id;
	
	private String name; 
	
	private double balance;
	
	@ManyToOne
	private Banks bank;

	public Accounts() {
		super();
	}

	

	@Override
	public String toString() {
		return "Accounts [id=" + id + ", name=" + name + ", balance=" + balance + "]";
	}



	public int getId() {
		return id;
	}



	public String getName() {
		return name;
	}



	public double getBalance() {
		return balance;
	}



	public Banks getBank() {
		return bank;
	}



	public void setId(int id) {
		this.id = id;
	}



	public void setName(String name) {
		this.name = name;
	}



	public void setBalance(double balance) {
		this.balance = balance;
	}



	public void setBank(Banks bank) {
		this.bank = bank;
	}




	
	
	
	
	
	
}
