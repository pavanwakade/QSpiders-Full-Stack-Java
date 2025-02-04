package unidirectional.ManyToManyRelatoion;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Subject {

	@Id
	private int id;
	
	private String subname;

	public Subject() {
		super();
	}

	public int getId() {
		return id;
	}

	public String getSubname() {
		return subname;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSubname(String subname) {
		this.subname = subname;
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", subname=" + subname + "]";
	}
	
	
	
}
