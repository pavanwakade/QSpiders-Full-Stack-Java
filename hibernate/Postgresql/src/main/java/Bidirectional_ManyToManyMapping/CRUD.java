package Bidirectional_ManyToManyMapping;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class CRUD {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pavan");

	static EntityManager em = emf.createEntityManager();

	static EntityTransaction et = em.getTransaction();

	static Subjectss sub = new Subjectss();

	static Studentss stu = new Studentss();

	public static void main(String[] args) {
		
//		choice();
		
		
	}

	
	public static void choice() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Choice\n1.insert\n2.Update\n3.Show all student\n4.Show all Subject\n5.delete subject from student");
		int choice=sc.nextInt();
		switch (choice) {
		case 1:
		{
			insertdata();
			break;

		}
		
		case 2:
		{
			updateData();
			break;

		}
		
		case 3:
		{
			fetchallstudent();
			break;

		}
		
		case 4:
		{
			fetchallsubject();
			break;

		}
		
		case 5:
		{
			deleteData();
			break;

		}

		default:
			
			System.out.println();
			break;
		}
	}
	public static void insertdata() {

		Studentss stu1 = new Studentss();
		stu1.setId(1);
		stu1.setName("pavan");

		Studentss stu2 = new Studentss();
		stu2.setId(2);
		stu2.setName("Sonali");

		List<Studentss> Studentss = new ArrayList<Studentss>();
		Studentss.add(stu1);
		Studentss.add(stu2);

		Subjectss sub1 = new Subjectss();
		sub1.setId(101);
		sub1.setName("java");

		Subjectss sub2 = new Subjectss();
		sub2.setId(102);
		sub2.setName("python");

		List<Subjectss> Subjectss = new ArrayList<Subjectss>();
		Subjectss.add(sub1);
		Subjectss.add(sub2);

		stu1.setSub(Subjectss);
		stu2.setSub(Subjectss);

		sub1.setStu(Studentss);
		sub2.setStu(Studentss);

		et.begin();

		em.persist(stu1);
		em.persist(stu2);

		em.persist(sub1);
		em.persist(sub2);
		et.commit();
	}

	public static void updateData() {

		sub = em.find(Subjectss.class, 102);

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
		Subjectss sub = em.find(Subjectss.class, 102);
		Studentss stu = em.find(Studentss.class, 2);

		if (sub != null && stu != null) {
			List<Subjectss> sublist = stu.getSub();

			Iterator<Subjectss> subiterator = sublist.iterator();

			while (subiterator.hasNext()) {
				Subjectss subj = subiterator.next();

				if (subj.getId() == sub.getId()) {
					subiterator.remove();
					break;
				}
			}
			stu.setSub(sublist);

			List<Studentss> stulist = sub.getStu();

			Iterator<Studentss> stuiterator = stulist.iterator();

			while (stuiterator.hasNext()) {
				Studentss stude = stuiterator.next();
				if (stude.getId() == stu.getId()) {
					stuiterator.remove();
					break;
				}
			}
			sub.setStu(stulist);

			et.begin();
			em.merge(sub);
			em.merge(stu);
			et.commit();
			System.out.println("Subject & student record deleted successfully.");
		} else {
			System.out.println("student or subject not found");
		}
	}

	public static void fetchallstudent() {

		Query query = em.createQuery("select e from Studentss e");

		List<Studentss> stuList = query.getResultList();

		if (stuList != null) {

			for (Studentss Studentss : stuList) {
//				System.out.println(Studentss.getId());
//				System.out.println(Studentss.getName());
//				System.out.println(Studentss.getClass());
				System.out.println(Studentss);
			}
		} else {
			System.out.println("Studentss is not presents");
		}
	}

	public static void fetchallsubject() {

		Query query = em.createQuery("select e from Subjectss e");

		List<Subjectss> subList = query.getResultList();

		if (subList != null) {

			for (Subjectss Subjectss : subList) {

				System.out.println(Subjectss);
			}
		} else {
			System.out.println("Subjectss is not presents");
		}
	}

}
