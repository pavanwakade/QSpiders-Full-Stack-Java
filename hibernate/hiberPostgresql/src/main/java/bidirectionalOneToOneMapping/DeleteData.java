package bidirectionalOneToOneMapping;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DeleteData {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pavan");

		EntityManager em = emf.createEntityManager();

		EntityTransaction et = em.getTransaction();

		Person person = em.find(Person.class, 5);
		Adhar account = em.find(Adhar.class, 101);

		if (person != null && account != null) {

			et.begin();

			em.remove(person);
			
			em.remove(account);

			System.out.println("Account deleted!!");

			et.commit();

		}

		else {
			System.err.println(" user Or Adhar Not Fount Account Not Fount!!");
		}

	}

}
