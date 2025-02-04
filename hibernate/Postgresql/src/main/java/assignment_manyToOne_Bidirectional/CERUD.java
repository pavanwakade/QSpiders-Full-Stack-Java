package assignment_manyToOne_Bidirectional;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class CERUD {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pavan");

	static EntityManager em = emf.createEntityManager();

	static EntityTransaction et = em.getTransaction();

	static Company com = new Company();

	public static void main(String[] args) {

//		insert();
//		update();
//		delete();
//		fetch();
		fetchall();

	}

	public static void insert() {
		com.setId(101);
		com.setName("TCS");
		com.setLocation("pune");

		Employees em1 = new Employees();
		em1.setId(1);
		em1.setName("jivan");
		em1.setSal(1000);
		em1.setCom(com);

		Employees em2 = new Employees();
		em2.setId(2);
		em2.setName("Ravan");
		em2.setSal(1000);
		em2.setCom(com);

		List<Employees> empl = new ArrayList<Employees>();
		empl.add(em1);
		empl.add(em2);
		com.setEmp(empl);

		et.begin();
//		em.persist(com);
		em.persist(em1);
		em.persist(em2);
		et.commit();
	}

	public static void fetch() {

		Employees em1 = em.find(Employees.class, 2);

		if (em1 != null) {

			com = em1.getCom();

			System.out.println(em1);
			System.out.println(com);
		} else {
			System.out.println("Emp not present");
		}

	}

	public static void update() {

		Employees em1 = em.find(Employees.class, 1);

		if (em1 != null) {

			com = em1.getCom();

			em1.setName("pavan");
			et.begin();
			em.merge(em1);
			et.commit();
		} else {
			System.out.println("Emp not present");
		}

	}

	
	
	public static void fetchall() {

		Query query = em.createQuery("select e from Employees e");

		List<Employees> empList = query.getResultList();

		if (empList != null) {

			for (Employees employees : empList) {
				System.out.println(employees.getId());
				System.out.println(employees.getName());
				System.out.println(employees.getSal());
			}
		} else {
			System.out.println("Emp is not presents");
		}
	}

	
	
	public static void delete() {

		Employees em1 = em.find(Employees.class, 2);
		com = em.find(Company.class, 101);

		if (com != null && em1 != null) {

			et.begin();

//		em.remove(com);
			em.remove(em1);

			System.err.println(" emp deleted!!");

			et.commit();

		} else {
			System.out.println("Emp not present");
		}

	}
}
