package unidirectional.ManyToManyRelatoion;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class insert {

	
	public static void main(String[] args) {
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("pavan");
		
		EntityManager em=emf.createEntityManager();
		
		EntityTransaction et=em.getTransaction();
		
		Subject sub1=new Subject();
		sub1.setId(1);
		sub1.setSubname("java");
		
		
		Subject sub2=new Subject();
		sub2.setId(2);
		sub2.setSubname("SQL");
		
		
		List<Subject> slist=new ArrayList<Subject>();
		slist.add(sub1);
		slist.add(sub2);
		
		
		Student s1=new Student();
		s1.setId(101);
		s1.setSname("Sonli");
		s1.setSubject(slist);
		
		
		Student s2=new Student();
		s2.setId(102);
		s2.setSname("Pavan");
		s2.setSubject(slist);
		
		
		et.begin();
		em.persist(s1);
		em.persist(s2);
		
		em.persist(sub1);
		em.persist(sub2);
		et.commit();
		
		
	}
}
