package hiberPostgresql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EntityTransactionExaple {

	public static void main(String[] args) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("pavan");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		et.begin();
		System.out.println("hello ");
		et.commit();
	}

}
