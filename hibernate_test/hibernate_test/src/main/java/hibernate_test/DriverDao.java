package hibernate_test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DriverDao {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("foodapp");
	static EntityManager em = emf.createEntityManager();
	static EntityTransaction et = em.getTransaction();

	static Scanner sc = new Scanner(System.in);


	public static void main(String[] args) {
		

//		 insertTrainerAndUser();
//		findById();
//		fetchallstudent();
//		updateStudentName();
		
//		assignTrainerToStudent();
//		deleteTrainerFromStudent();

	}
	
	
	
	public static void deleteTrainerFromStudent() {
		System.out.println("Enter Id Of Student!!");
		int id = sc.nextInt();
		
		System.out.println("Enter Treiner id");

		int tid=sc.nextInt();

			Students stu = em.find(Students.class, id);
			Trainer tr = em.find(Trainer.class, tid);

			if (stu != null && tr != null) {

				List<Trainer> trlList = stu.getTrainer();
				Iterator<Trainer> trIterator = trlList.iterator();

				while (trIterator.hasNext()) {
					Trainer tt = trIterator.next();
					if (tt.getId() == tr.getId()) {
						trIterator.remove();
						break;
					}
				}
				stu.setTrainer(trlList);
  
				List<Students> stuList = tr.getStu();
				Iterator<Students> stuIterator = stuList.iterator();

				while (stuIterator.hasNext()) {
					Students su = stuIterator.next();
					if (su.getId() == stu.getId()) {
						stuIterator.remove();
						break;
					}
				}
				tr.setStu(stuList);
			}

			et.begin();
			em.merge(stu);
			et.commit();
		}
	

	public static void assignTrainerToStudent() {
		System.out.println("Enter Id Of Student!!");
		int id = sc.nextInt();
		System.out.println("Enter Treiner id");

		int tid=sc.nextInt();
		Students stu = em.find(Students.class, id);

		Trainer tr = em.find(Trainer.class, tid);

		if (stu != null && tr != null) {
			List<Trainer> trList = stu.getTrainer();
			trList.add(tr);
			stu.setTrainer(trList);

			List<Students> stulist = tr.getStu();
			stulist.add(stu);
			tr.setStu(stulist);
			et.begin();
			em.merge(stu);
			em.merge(tr);
			et.commit();
			System.out.println("Trainer given to user");
		} else {
			System.out.println("Trainer or Student not find");
		}

	}

	public static void updateStudentName() {
		System.out.println("Enter Id Of Student!!");
		int id = sc.nextInt();
		
		Students stu = em.find(Students.class, id);

		if (stu != null) {

			em.find(Students.class, id);
			System.out.println("Enter name");
			String name = sc.next();
			stu.setName(name);

			et.begin();
			em.merge(stu);
			et.commit();

		}
	}

	public static void fetchallstudent() {

		Query query = em.createQuery("select e from Students e");

		List<Students> stuList = query.getResultList();

		if (stuList != null) {

			for (Students Studentss : stuList) {

				System.out.println(Studentss);
			}
		} else {
			System.out.println("Studentss is not presents");
		}
	}

	public static void findById() {
		System.out.println("Enter Id Of Student!!");
		int id = sc.nextInt();

		Students stu = em.find(Students.class, id);

		Trainer tr = em.find(Trainer.class, id);

		if (stu != null && tr != null) {

			System.out.println(stu);

			System.out.println(tr);
		}
	}

	public static void insertTrainerAndUser() {
		Students stu = new Students();
		stu.setName("Pavan");

		Trainer trainer = new Trainer();
		trainer.setName("sndeep sir");
		


		List<Students> studentsList = new ArrayList<Students>();
		studentsList.add(stu);

		List<Trainer> Trainerist = new ArrayList<Trainer>();
		Trainerist.add(trainer);

		trainer.setStu(studentsList);
		stu.setTrainer(Trainerist);

		et.begin();
		em.persist(stu);
		et.commit();
	}

}
