package Annotations_All;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Driver {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pavan");

		EntityManager em = emf.createEntityManager();

		EntityTransaction et = em.getTransaction();

		Teacher te = new Teacher();

		te.setName("pavan");
		te.setPhone(1234567890);

		et.begin();
		em.persist(te);
		et.commit();
	}
}
