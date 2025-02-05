package Bidirectional_ManyToManyMapping;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
@Cacheable
public class Studentss {

	@Id
	private int id;
	private String name;
	
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})

	private List<Subjectss> sub;

	public Studentss() {
		super(); 
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Subjectss> getSub() {
		return sub;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSub(List<Subjectss> sub) {
		this.sub = sub;
	}

	@Override
	public String toString() {
		return "Students [id=" + id + ", name=" + name + "]";
	}
	
	
	
	
	
}
