package bidirectionalOneToOneMapping;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Person {
	@Id
	private int id;
	
	private String name;
	
	private long phone;
	
	@JoinColumn(name = "adhar_id")
	@OneToOne
	private Adhar adhar;
	
	
	public Person() {
		super();
	}


	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public long getPhone() {
		return phone;
	}


	public Adhar getAdhar() {
		return adhar;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setPhone(long phone) {
		this.phone = phone;
	}


	public void setAdhar(Adhar adhar) {
		this.adhar = adhar;
	}


	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", phone=" + phone + "]";
	}


	
	
	
	
}
