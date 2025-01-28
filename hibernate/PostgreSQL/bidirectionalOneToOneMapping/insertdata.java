package bidirectionalOneToOneMapping;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class insertdata {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pavan");

		EntityManager em = emf.createEntityManager();

		EntityTransaction et = em.getTransaction();

		Person person = new Person();

		Adhar adhar = new Adhar();

		person.setId(1);
		person.setName("pavan");
		person.setPhone(12346);
		person.setAdhar(adhar);

		adhar.setId(101);
		adhar.setAddress("pune");
		adhar.setPerson(person);

		person.setId(2);
		person.setName("Ravan");
		person.setPhone(123467);
		person.setAdhar(adhar);

		adhar.setId(102);
		adhar.setAddress("pune");
		adhar.setPerson(person);

		et.begin();
		em.persist(person);

		em.persist(adhar);

		System.out.println("Datainserted sucessfully!");

		et.commit();
	}

}
