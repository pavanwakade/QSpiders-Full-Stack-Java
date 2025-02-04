package hiberPostgresql;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;

@Cacheable
@Entity
public class SecontLevelCatche {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String  name;

	public SecontLevelCatche() {
		super();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SecontLevelCatche [id=" + id + ", name=" + name + "]";
	}
	
	
	public static void main(String[] args) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("pavan");
		
		EntityManager em = emf.createEntityManager();
		EntityManager em1 = emf.createEntityManager();
		
		EntityTransaction et=em.getTransaction();
		
		SecontLevelCatche c=new SecontLevelCatche();
		
//		c.setName("pavan");
		em.find(SecontLevelCatche.class, 1);
		em.find(SecontLevelCatche.class, 1);
		em.find(SecontLevelCatche.class, 1);
		
		em1.find(SecontLevelCatche.class, 1);
		em1.find(SecontLevelCatche.class, 1);
		em1.find(SecontLevelCatche.class, 1);
		
//		et.begin();
//		em.persist(c);
//		
//		et.commit();
		
	}
}
