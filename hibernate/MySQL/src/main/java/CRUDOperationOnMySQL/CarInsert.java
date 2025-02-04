package CRUDOperationOnMySQL;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class CarInsert {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pavan");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		Scanner sc = new Scanner(System.in);

		System.out.print("carid : ");
		int id = sc.nextInt();

		sc.nextLine();
		
		System.out.print("carNAme : ");
		String carName = sc.nextLine();

		System.out.print("carPrice : ");
		double carPrice = sc.nextDouble();

		cardata cd = new cardata();
		cd.setCarid(id);
		cd.setCarName(carName);
		cd.setPrice(carPrice);

		et.begin();

		em.persist(cd);

		et.commit();
	}

}
