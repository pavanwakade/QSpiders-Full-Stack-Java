package bidirectionalOneToOneMapping;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import unidirectiona.OneToOneRelatoion.Persion;

@Entity
public class Adhar {

	@Id
	private int id;
	
	private String address;
	
	@OneToOne(mappedBy = "adhar")
	private Person person;
	
	

	public Adhar() {
		super();
	}



	public int getId() {
		return id;
	}



	public String getAddress() {
		return address;
	}



	public Person getPerson() {
		return person;
	}



	public void setId(int id) {
		this.id = id;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public void setPerson(Person person) {
		this.person = person;
	}



	@Override
	public String toString() {
		return "Adhar [id=" + id + ", address=" + address + "]";
	}



	
	
	
}
