package CRUDOperationOnMySQL;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transaction;

public class carUpdate {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pavan");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		cardata cd = em.find(cardata.class, 101);
		if (cd != null) {
			
			cd.setCarName("TATA Safari");
			cd.setPrice(9900000);
			et.begin();
			em.merge(cd);
			et.commit();
			
		} else {
			System.out.println("data is not present");
		}
	}
}
