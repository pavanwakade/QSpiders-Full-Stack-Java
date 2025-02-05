package Bidirectional_ManyToManyMapping;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
@Cacheable
public class Subjectss {

	@Id
	private int id;

	private String name;
	
	
	@ManyToMany(mappedBy = "sub",cascade = {CascadeType.MERGE,CascadeType.PERSIST})

//	@ManyToMany
	private List<Studentss> stu;

	public Subjectss() {
		super();
	}
	
	

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Studentss> getStu() {
		return stu;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStu(List<Studentss> stu) {
		this.stu = stu;
	}
	
	

	@Override
	public String toString() {
		return "Subjects [id=" + id + ", name=" + name + "]";
	}

}
