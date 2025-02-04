package unidirectional.ManyToManyRelatoion;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Student {

	@Id
	private int id;
	
	private String sname;
	
	@ManyToMany
	private List<Subject> subject;

	public Student() {
		super();
	}

	public int getId() {
		return id;
	}

	public String getSname() {
		return sname;
	}

	public List<Subject> getSubject() {
		return subject;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public void setSubject(List<Subject> subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", sname=" + sname + "]";
	}
	
	
	
	
}
