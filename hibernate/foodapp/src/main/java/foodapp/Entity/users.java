package foodapp.Entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "app_users")
public class users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@ManyToMany
	private List<Hotels> hotels;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Hotels> getHotels() {
		return hotels;
	}

	

	public void setName(String name) {
		this.name = name;
	}

	public void setHotels(List<Hotels> hotels) {
		this.hotels = hotels;
	}

	@Override
	public String toString() {
		return "users [id=" + id + ", name=" + name + "]";
	}
	
	
	
}
