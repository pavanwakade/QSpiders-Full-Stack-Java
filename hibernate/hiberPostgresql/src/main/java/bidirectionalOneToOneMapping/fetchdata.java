package bidirectionalOneToOneMapping;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class fetchdata {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pavan");

		EntityManager em = emf.createEntityManager();

		EntityTransaction et = em.getTransaction();

		Person persion = em.find(Person.class, 1);

		if (persion != null) {

			Adhar adhar = persion.getAdhar();

			System.out.println(persion);

			System.out.println(adhar);
		} else {
			System.out.println("persion is not persion");
		}
	}
}
