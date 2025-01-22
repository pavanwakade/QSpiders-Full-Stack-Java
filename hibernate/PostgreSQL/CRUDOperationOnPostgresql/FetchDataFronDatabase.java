package CRUDOperationOnPostgresql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FetchDataFronDatabase {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pavan");
		EntityManager em = emf.createEntityManager();

		cardata cd = em.find(cardata.class, 101);
		if (cd != null) {
			System.out.println(cd.getCarid());
			System.out.println(cd.getCarName());
			System.out.println(cd.getPrice());
			
			
			
//			System.out.println(cd);
		} else {
			System.out.println("data is not present");
		}
	}
}
