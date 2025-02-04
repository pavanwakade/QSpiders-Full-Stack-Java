package unidirectiona.ManyToOneRelatoion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Driver {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pavan");

		EntityManager em = emf.createEntityManager();

		EntityTransaction et = em.getTransaction();

		
		
		
		Product product = new Product();

		product.setId(101);

		product.setName("iphone");

		product.setPrice(100000);

		
		
		
		Review r1 = new Review();
		
		r1.setId(10);
		r1.setMsg("BAd");

		r1.setProduct(product);

		
		
		
		
		Review r2 = new Review();

		r2.setId(11);
		r2.setMsg("BAd");

		r2.setProduct(product);

		
		
		et.begin();

		em.persist(product);
		em.persist(r1);
		em.persist(r2);
		
		et.commit();
	}
}
