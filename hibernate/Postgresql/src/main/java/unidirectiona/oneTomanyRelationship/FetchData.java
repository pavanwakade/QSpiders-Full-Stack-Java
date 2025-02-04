package unidirectiona.oneTomanyRelationship;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class FetchData {
	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pavan");

		EntityManager em = emf.createEntityManager();

		EntityTransaction et = em.getTransaction();

		Query query = em.createQuery("select a from Account a");

		List<Account> list = query.getResultList();

		for (Account account : list) {

			System.out.println(account);

			System.out.println("-----------------------------------------");
		}

	}
}
