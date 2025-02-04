package assignment_manyToOne_Bidirectional;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Employees {

	@Id
	private int id;
	
	private String name;
	
	private double sal;
	@JoinColumn
	@ManyToOne
	private Company com;
	public Employees() {
		super();
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public double getSal() {
		return sal;
	}
	public Company getCom() {
		return com;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSal(double sal) {
		this.sal = sal;
	}
	public void setCom(Company com) {
		this.com = com;
	}
	@Override
	public String toString() {
		return "Employees [id=" + id + ", name=" + name + ", sal=" + sal + "]";
	}
	
	
	
	
	
}
