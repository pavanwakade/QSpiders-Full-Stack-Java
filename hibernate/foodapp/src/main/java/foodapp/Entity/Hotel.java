package foodapp.Entity;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="hotel_table")
@Cacheable
public class Hotel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="hotel_name")
	private String name;
	
	@ManyToMany(mappedBy = "hotels",cascade = {CascadeType.MERGE,CascadeType.PERSIST})
	private List<Users> users;


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

	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}

	public Hotel() {
		super();
	}

	@Override
	public String toString() {
		return "Hotel [id=" + id + ", name=" + name + ", users=" + users + "]";
	}

	
}