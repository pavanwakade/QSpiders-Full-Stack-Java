package Bidirectional_ManyToManyMapping;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class CRUD {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pavan");

	static EntityManager em = emf.createEntityManager();

	static EntityTransaction et = em.getTransaction();

	static Subjects sub = new Subjects();

	static Students stu = new Students();

	public static void main(String[] args) {

//		insertdata();
//		updateData();
//		fetchallstudent();
//		fetchallsubject();
		deleteData();

	}

	public static void insertdata() {

		Students stu1 = new Students();
		stu1.setId(1);
		stu1.setName("pavan");

		Students stu2 = new Students();
		stu2.setId(2);
		stu2.setName("Sonali");

		List<Students> students = new ArrayList<Students>();
		students.add(stu1);
		students.add(stu2);

		Subjects sub1 = new Subjects();
		sub1.setId(101);
		sub1.setName("java");

		Subjects sub2 = new Subjects();
		sub2.setId(102);
		sub2.setName("python");

		List<Subjects> subjects = new ArrayList<Subjects>();
		subjects.add(sub1);
		subjects.add(sub2);

		stu1.setSub(subjects);
		stu2.setSub(subjects);

		sub1.setStu(students);
		sub2.setStu(students);

		et.begin();

		em.persist(stu1);
		em.persist(stu2);

		em.persist(sub1);
		em.persist(sub2);
		et.commit();
	}

	public static void updateData() {

		sub = em.find(Subjects.class, 102);

		if (sub != null) {

			sub.setName("SQL");

			et.begin();
			em.merge(sub);
			et.commit();

			System.out.println("Record updated successfully.");
		} else {
			System.out.println("Subject with ID not found.");
		}
	}

	public static void deleteData() {
		Subjects sub = em.find(Subjects.class, 102);
		Students stu = em.find(Students.class, 2);

		if (sub != null && stu != null) {
			List<Subjects> sublist = stu.getSub();

			Iterator<Subjects> subiterator = sublist.iterator();

			while (subiterator.hasNext()) {
				Subjects subj = subiterator.next();
				
				if (subj.getId() == sub.getId()) {
					subiterator.remove();
					break;
				}
			}
			stu.setSub(sublist);

			List<Students> stulist = sub.getStu();

			Iterator<Students> stuiterator = stulist.iterator();

			while (stuiterator.hasNext()) {
				Students stude = stuiterator.next();
				if (stude.getId() == stu.getId()) {
					stuiterator.remove();
					break;
				}
			}
			sub.setStu(stulist);

		}

		et.begin();
		em.merge(sub);
		em.merge(stu);
		et.commit();
		System.out.println("Subject & student record deleted successfully.");
	}

	public static void fetchallstudent() {

		Query query = em.createQuery("select e from Students e");

		List<Students> stuList = query.getResultList();

		if (stuList != null) {

			for (Students students : stuList) {
//				System.out.println(students.getId());
//				System.out.println(students.getName());
//				System.out.println(students.getClass());
				System.out.println(students);
			}
		} else {
			System.out.println("Students is not presents");
		}
	}

	public static void fetchallsubject() {

		Query query = em.createQuery("select e from Subjects e");

		List<Subjects> subList = query.getResultList();

		if (subList != null) {

			for (Subjects subjects : subList) {

				System.out.println(subjects);
			}
		} else {
			System.out.println("subjects is not presents");
		}
	}

}
