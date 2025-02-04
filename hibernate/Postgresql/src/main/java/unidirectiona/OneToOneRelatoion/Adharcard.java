package unidirectiona.OneToOneRelatoion;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Adharcard {

	@Id
	private int aid;
	private String Address;
	
	public Adharcard() {
		super();
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	

	
	
	
	
}
