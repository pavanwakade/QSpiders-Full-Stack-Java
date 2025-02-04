package unidirectiona.OneToOneRelatoion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class UpdateData {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pavan");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		Persion person =em.find(Persion.class,11);
		Adharcard adhar = person.getAdhar();
		adhar.setAddress("India");
		et.begin();
		em.merge(adhar);
		et.commit();
	}
}
