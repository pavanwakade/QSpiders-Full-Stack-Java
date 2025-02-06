package foodapp.Entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class HotelDao {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("foodapp");
	static EntityManager em = emf.createEntityManager();
	static EntityTransaction et = em.getTransaction();

	public static void main(String[] args) {

//		insertHotelAndUser();

//		fetchHotelAndUser();

//		fetchAll();
//
//		updateHotelFromUser();

//		removeHotelFromUser();
		giveUserToHotel();

	}

	public static void insertHotelAndUser() {
		Users user = new Users();
		user.setName("Ketan");

		Hotel hotel = new Hotel();
		hotel.setName("TAJ");

		List<Users> userList = new ArrayList<Users>();
		userList.add(user);

		List<Hotel> hoteList = new ArrayList<Hotel>();
		hoteList.add(hotel);

		hotel.setUsers(userList);
		user.setHotels(hoteList);

		et.begin();
		em.persist(user);
		et.commit();
	}

	public static void fetchHotelAndUser() {

		Users user = em.find(Users.class, 1);
		Hotel hotel = em.find(Hotel.class, 1);

		System.out.println("User: " + user.getName() + " " + "Uid: " + user.getId());
		System.out.println("Hotel: " + hotel.getName() + " " + "Hid: " + hotel.getId());
		
		System.out.println(".................................");

//		System.out.println(user);
//		System.out.println(hotel);

	}

	public static void fetchAll() {
		Query query = em.createQuery("select h from Hotel h");

		List<Hotel> hotels = query.getResultList();

		for (Hotel hotel : hotels) {

			System.out.println("........................");
//			System.out.println(hotel.getId());
//			System.out.println(hotel.getName());
			System.out.println(hotel);
			System.out.println("........................");

		}

		Query query2 = em.createQuery("select u from Users u");

		List<Users> users = query2.getResultList();

		for (Users user : users) {

			System.out.println("........................");
//			System.out.println(user.getId());
//			System.out.println(user.getName());
			
			System.out.println(user);
			System.out.println("........................");
		}
	}
	
	
	

	public static void updateHotelFromUser() {
		Users user = em.find(Users.class, 2);
		Hotel hotel = em.find(Hotel.class, 2);

		hotel.setName("Nisarg");

		et.begin();
		em.merge(hotel);
		et.commit();

	}
	
	

	public static void removeHotelFromUser() {
		Users user = em.find(Users.class, 1);
		Hotel hotel = em.find(Hotel.class, 1);

		if (user != null && hotel != null) {

			List<Hotel> hotelList = user.getHotels();
			Iterator<Hotel> hotelIterator = hotelList.iterator();

			while (hotelIterator.hasNext()) {
				Hotel h = hotelIterator.next();
				if (h.getId() == hotel.getId()) {
					hotelIterator.remove();
					break;
				}
			}
			user.setHotels(hotelList);

			List<Users> userList = hotel.getUsers();
			Iterator<Users> userIterator = userList.iterator();

			while (userIterator.hasNext()) {
				Users u = userIterator.next();
				if (u.getId() == user.getId()) {
					userIterator.remove();
					break;
				}
			}
			hotel.setUsers(userList);
		}

		et.begin();
		em.merge(user);
		et.commit();
	}
	
	public static void giveUserToHotel(){
		Users user=em.find(Users.class, 1);
		Hotel hotel=em.find(Hotel.class, 1);
		if (user != null && hotel != null) {
			List<Hotel> hotelList = user.getHotels();
			hotelList.add(hotel);
			user.setHotels(hotelList);
			List<Users> userlist=hotel.getUsers();
			userlist.add(user);
			hotel.setUsers(userlist);
			et.begin();
			em.merge(user);
			em.merge(hotel);
			et.commit();
			System.out.println("Hotel given to user");
		}
		else {
			System.out.println("user or hotel not find");
		}

	}
	
	
//	public static void removeUserFromHotel() {
//		Users user = em.find(Users.class, 1);
//		Hotel hotel = em.find(Hotel.class, 1);
//
//		if (user != null && hotel != null) {
//
//			List<Hotel> hotelList = user.getHotels();
//			Iterator<Hotel> hotelIterator = hotelList.iterator();
//
//			while (hotelIterator.hasNext()) {
//				Hotel h = hotelIterator.next();
//				if (h.getId() == hotel.getId()) {
//					hotelIterator.remove();
//					break;
//				}
//			}
//			user.setHotels(hotelList);
//
//			List<Users> userList = hotel.getUsers();
//			Iterator<Users> userIterator = userList.iterator();
//
//			while (userIterator.hasNext()) {
//				Users u = userIterator.next();
//				if (u.getId() == user.getId()) {
//					userIterator.remove();
//					break;
//				}
//			}
//			hotel.setUsers(userList);
//		}
//
//		et.begin();
//		em.merge(user);
//		et.commit();
//	}
}