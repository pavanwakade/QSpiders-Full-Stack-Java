package unidirectional.ManyToManyRelatoion;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class fetchdata {

	public static void main(String[] args) {
		
EntityManagerFactory emf=Persistence.createEntityManagerFactory("pavan");
		
		EntityManager em=emf.createEntityManager();
		
		
		
		Student student=em.find(Student.class, 101);
		
		List<Subject> slist=student.getSubject();

		
		if (slist!=null) {
			
//			System.out.println(student.getId());
//			System.out.println(student.getSname());
//			System.out.println(student.getSubject());
			
			
			System.out.println(student);
			System.out.println("--------------------------------");
			
			for (Subject subject : slist) {
//				System.out.println(subject.getId());
//				System.out.println(subject.getSubname());
				
				System.out.println(subject);
			}
			
		}

	}

}
