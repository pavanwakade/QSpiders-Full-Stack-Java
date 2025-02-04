package bidirectional_OneToMany_ManyToOne_Mapping;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Banks {

	@Id
	private int id;
	
	private String name;
	
	private String add;
	
	
	@OneToMany(mappedBy = "bank")
	private List<Accounts> account;

	public Banks() {
		super();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAdd() {
		return add;
	}

	public List<Accounts> getAccount() {
		return account;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	public void setAccount(List<Accounts> account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Banks [id=" + id + ", name=" + name + ", add=" + add + "]";
	}

	
	
	
	
}
