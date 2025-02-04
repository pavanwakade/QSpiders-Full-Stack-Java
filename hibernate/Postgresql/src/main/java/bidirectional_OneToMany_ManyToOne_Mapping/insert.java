package bidirectional_OneToMany_ManyToOne_Mapping;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class insert {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pavan");

		EntityManager em = emf.createEntityManager();

		EntityTransaction et = em.getTransaction();

		Banks bk = new Banks();

		bk.setId(101);
		bk.setName("SBI");
		bk.setAdd("pune");

		Accounts ac1 = new Accounts();
		ac1.setId(1);
		ac1.setName("jivan");
		ac1.setBalance(1000);
		ac1.setBank(bk);
		
		
		Accounts ac2 = new Accounts();
		ac2.setId(2);
		ac2.setName("savan");
		ac2.setBalance(2000);
		ac2.setBank(bk);
		
		List<Accounts> account=new ArrayList<Accounts>();
		account.add(ac1);
		account.add(ac2); 
		bk.setAccount(account);
		
		et.begin();
		em.persist(bk);
		em.persist(ac1);
		em.persist(ac2);
		et.commit();
	}

}
