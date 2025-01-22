package hiberPostgresql;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class hibInstallation {
	public static void main(String[] args) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("pavan");
		System.out.println(emf);
	}
}
