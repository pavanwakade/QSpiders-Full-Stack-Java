package unidirectiona.OneToOneRelatoion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class FetchData {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pavan");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		Persion person = em.find(Persion.class, 11);
		Adharcard adhar = person.getAdhar();

		if (adhar != null) {
			System.out.println(person.getId());
			System.out.println(person.getName());
			System.out.println(person.getPhoneNO());
			System.out.println(adhar.getAid());
			System.out.println(adhar.getAddress());
		} else {
			System.out.println("user not present");
		}
	}
}
