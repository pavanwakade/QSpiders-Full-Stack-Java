package assignment_manyToOne_Bidirectional;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Company {

	@Id
	private int id;
	
	private String name;
	
	private String location;
	
	@OneToMany(mappedBy = "com")
	
	private List<Employees> emp;

	
	
	
	public Company() {
		super();
	}







	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", location=" + location + "]";
	}







	public int getId() {
		return id;
	}







	public String getName() {
		return name;
	}







	public String getLocation() {
		return location;
	}







	public List<Employees> getEmp() {
		return emp;
	}







	public void setId(int id) {
		this.id = id;
	}







	public void setName(String name) {
		this.name = name;
	}







	public void setLocation(String location) {
		this.location = location;
	}







	public void setEmp(List<Employees> emp) {
		this.emp = emp;
	}
	
	
	
	
}
