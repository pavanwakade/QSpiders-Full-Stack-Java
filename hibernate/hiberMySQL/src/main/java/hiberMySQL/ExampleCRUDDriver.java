package hiberMySQL;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ExampleCRUDDriver {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pavan");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		ExampleCRUD ex = new ExampleCRUD();
		ex.setId(10);
		ex.setName("Tejus");

		et.begin();
		em.persist(ex);

		System.out.println("Saved");
		et.commit();
	}

}
