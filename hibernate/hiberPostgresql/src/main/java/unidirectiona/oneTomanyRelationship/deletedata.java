package unidirectiona.oneTomanyRelationship;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class deletedata {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pavan");

		EntityManager em = emf.createEntityManager();

		EntityTransaction et = em.getTransaction();

		Account account = em.find(Account.class, 2);

		if (account != null) {

			et.begin();

			em.remove(account);

			System.out.println("Account deleted!!");

			et.commit();
		}

		else {
			System.out.println("Account Not Fount!!");
		}
	}

}
