package relationship.OneToOneRelatoion;

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
	private Adharcard card;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Adharcard getCard() {
		return card;
	}

	public void setCard(Adharcard card) {
		this.card = card;
	}

	public long getPhoneNO() {
		return phoneNO;
	}

	public void setPhoneNO(long phoneNO) {
		this.phoneNO = phoneNO;
	}

	public Persion() {
		super();
	}

	
}
