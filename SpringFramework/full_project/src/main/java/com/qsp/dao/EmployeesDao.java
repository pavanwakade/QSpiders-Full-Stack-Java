package com.qsp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeesDao {

	@Autowired
	private EntityManager em;

	public void saveEmp(Employeees employee) {

		EntityTransaction et = em.getTransaction();

		et.begin();

		em.persist(employee);

		et.commit();

	}
	
	public Employeees findByID(int id) {
		
		return em.find(Employeees.class, id);
	}
	
	
	public void deleteByID(int id) {
		
		EntityTransaction et=em.getTransaction();
		
		Employeees employee=findByID(id);
		
		if (employee !=null) {
			
			et.begin();
			
			em.remove(employee);
			
			et.commit();
		}
	}
	
	
	
	
	
	
	public void updateEmployee( Employeees employee) {
		
		EntityTransaction et=em.getTransaction();
		
		et.begin();
		
		em.merge(employee);
		et.commit();
		
	}
	
	
	public List<Employeees> findEmployees(){
		
		Query query=em.createQuery("select e from Employeees e");
		return query.getResultList();
	}
	
	
	
	
//public Employeees findBNameAndPhone(String name,long phone) {
//	
//	Query query=em.createQuery("select e from  Employeees e where e.name=:nm and e.phone=:ph");
//	
//	query.setParameter("na", name);
//	
//	query.setParameter("ph", phone);
//		
//		return (Employeees) query.getSingleResult();
//	}

	
	//or we can use
	

public Employeees verifybynamephone(String name,long phone) {
	
	Query query=em.createQuery("select e from  Employeees e where e.name=:?1 and e.phone=:?2");
	
	query.setParameter(1, name);
	
	query.setParameter(2, phone);
	
	List<Employeees> result=query.getResultList();
	
	if (result.isEmpty()) {
		return null;
	}
		
		return result.get(0);
	}



}
