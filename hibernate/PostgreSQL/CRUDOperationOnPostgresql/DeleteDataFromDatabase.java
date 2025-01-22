package CRUDOperationOnPostgresql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DeleteDataFromDatabase {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pavan");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		cardata cd = em.find(cardata.class, 101);

		if (cd != null) {

			et.begin();
			em.remove(cd);
			et.commit();
			System.out.println("Removed");
		} else {
			System.out.println("data is not present");
		}
	}
}
