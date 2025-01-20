package ArrayList;

import java.util.ArrayList;
import java.util.Comparator;

public class UserDB {

	public static void main(String[] args) {
		ArrayList<User> al = new ArrayList<>();
		al.add(new User(1, "pavan ", 2000));
		al.add(new User(2, "Shashi", 3000));
		al.add(new User(3, "kedar ", 10000));
		al.add(new User(4, "Nvnath", 5000));
		al.add(new User(5, "Shubhm", 1000));
		al.add(new User(6, "Ranjit", 500));
		al.add(new User(7, "vishal", 6577));
		al.add(new User(8, "Yogesh", 7440));
		al.add(new User(9, "Sandip", 949));
		al.add(new User(10, "Akash ", 4370));
		al.add(new User(11, "ganesh", 55609));
		al.add(new User(12, "Sonali", 833359));

//		String Attribute;
//		switch (Attribute){
//			case "id": {
//				System.out.println(sortById());
//			}
	}
//		 System.out.println(sortById())

//   public static Comparator<User> sortName() {
//		
//		Comparator<User> us= new Comparator<User>() {
//			public int compare(User u1,User u2) {
//				if (u1.name>u2.name) {
//					return 1;
//				}
//				else if (u1.name<u2.name) {
//					return -1;
//				}
//				return 0;
//			}
//		};
//		return us;
//	}

	public static Comparator<User> sortById() {

		Comparator<User> us = new Comparator<User>() {
			public int compare(User u1, User u2) {
				if (u1.id > u2.id) {
					return 1;
				} else if (u1.id < u2.id) {
					 return -1;
				}
				return 0;
			}
		};
		return us;
	}

	public static Comparator<User> SortByAmount() {
		Comparator<User> us = new Comparator<User>() {
			public int compare(User u1, User u2) {
				if (u1.amount > u2.amount) {
					return 1;
				} else if (u1.amount < u2.amount) {
					return -1;
				}
				return 0;
			}
		};
		return us;
	}

	public static void printDB(ArrayList<User> al) {
		for (User User : al) {
			System.out.println(User);

		}
	}
}
