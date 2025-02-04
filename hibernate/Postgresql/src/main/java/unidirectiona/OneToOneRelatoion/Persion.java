package unidirectiona.OneToOneRelatoion;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;



@Entity
public class Persion {

	@Id
	private int id;
	private String name;
	private long phoneNO;
	
	@OneToOne
	private Adharcard adhar;

	
	public Persion() {
		super();
	}


	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public long getPhoneNO() {
		return phoneNO;
	}


	public Adharcard getAdhar() {
		return adhar;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setPhoneNO(long phoneNO) {
		this.phoneNO = phoneNO;
	}


	public void setAdhar(Adharcard adhar) {
		this.adhar = adhar;
	}


	@Override
	public String toString() {
		return "Persion [id=" + id + ", name=" + name + ", phoneNO=" + phoneNO + "]";
	}

	
	
	
	
	
		
	}

	

	
	

