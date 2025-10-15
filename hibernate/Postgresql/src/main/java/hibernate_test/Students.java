package hibernate_test;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Students {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name ="student_name")
	private String name;

	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private List<Trainer> trainer;

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

	public List<Trainer> getTrainer() {
		return trainer;
	}

	public void setTrainer(List<Trainer> trainer) {
		this.trainer = trainer;
	}

	@Override
	public String toString() {
		return "Students [id=" + id + ", name=" + name + "]";
	}





}
