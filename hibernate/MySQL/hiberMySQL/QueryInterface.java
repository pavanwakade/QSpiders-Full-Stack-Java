package hiberMySQL;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import CRUDOperationOnMySQL.cardata;

public class QueryInterface {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pavan");

		EntityManager em = emf.createEntityManager();

		Query query = em.createQuery("select c from cardata c");

		List<cardata> carList = query.getResultList();

		for (cardata car : carList) {
			System.out.println("Car ID: " + car.getCarid());
			System.out.println("Car Name: " + car.getCarName());
			System.out.println("Car Price: " + car.getPrice());
		}

	}
}
