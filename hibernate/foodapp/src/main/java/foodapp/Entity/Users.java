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
@Table(name="user_table")
@Cacheable
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name ="user_name")
	private String name;
	
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private List<Hotel> hotels;

	

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

	public List<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}

	public Users() {
		super();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", hotels=" + hotels + "]";
	}
	
	
	
	
	
	
	
	
}