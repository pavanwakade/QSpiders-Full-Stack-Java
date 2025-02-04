package unidirectiona.OneToOneRelatoion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Insert {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pavan");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		Persion person = new Persion();

		person.setId(11);
		person.setName("Pavan");
		person.setPhoneNO(8317277608l);

		Adharcard adhar = new Adharcard();
		adhar.setAid(111);
		adhar.setAddress("Latur");
		person.setAdhar(adhar);

		et.begin();
		em.persist(person);
		em.persist(adhar);
		et.commit();
	}
}
