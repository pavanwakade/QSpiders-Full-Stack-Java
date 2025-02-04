package unidirectiona.oneTomanyRelationship;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class insertdata {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pavan");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		Bank bank = new Bank();
		bank.setId(101);
		bank.setName("SBI");
		bank.setBranch("pune");

		Account ac1 = new Account();
		ac1.setId(1);
		ac1.setName("pavan");
		ac1.setBalance(100000);

		Account ac2 = new Account();
		ac2.setId(2);
		ac2.setName("Ravan");
		ac2.setBalance(200000);

		Account ac3 = new Account();
		ac3.setId(3);
		ac3.setName("Savan");
		ac3.setBalance(300000);

		List<Account> list = new ArrayList<Account>();
		list.add(ac1);
		list.add(ac2);
		list.add(ac3);

		bank.setAccount(list);

		et.begin();
		em.persist(bank);
		em.persist(ac1);
		em.persist(ac2);
		em.persist(ac3);
		et.commit();

	}

}
