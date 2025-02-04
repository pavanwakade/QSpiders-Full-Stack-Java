package unidirectiona.oneTomanyRelationship;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Bank {

	@Id
	private int id;

	private String name;

	private String branch;

	@OneToMany
	private List<Account> account;

	public Bank() {

	}

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

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public List<Account> getAccount() {
		return account;
	}

	public void setAccount(List<Account> account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Bank [id=" + id + ", name=" + name + ", branch=" + branch + "]";
	}

}
