package bidirectionalOneToOneMapping;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Updatedata {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pavan");

		EntityManager em = emf.createEntityManager();

		EntityTransaction et = em.getTransaction();

		Person person = em.find(Person.class, 1);

		Adhar adhar = person.getAdhar();

		adhar.setAddress("India");

		et.begin();
		em.merge(adhar);
		et.commit();
	}
}
