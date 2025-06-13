package collectionFramework.collectioninterface;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class CollectionInterfaceMethodsCRUD {

	static Collection<String> coll = new ArrayList<String>();
	static Scanner sc = new Scanner(System.in);
	static boolean opt=true;
	public static void main(String[] args) {
//		addINtoCollection();
		
		while (opt) {
			choice();
		}
	}

	public static void choice() {

		System.out.println("Enter currect option");
		System.out.println("1 read \n 2 insert \n 3 update /n 4 delete \n 5 stop \n currect option");
		int inp = sc.nextInt();

		switch (inp) {
		case 1: {
			System.out.println(coll);
			break;
		}
		case 2: {
			addINtoCollection();
			break;
		}

		case 3: {
			System.out.println("Enter old value which you need to change");
			String oldvalue = sc.nextLine();

			System.out.println("Enter new value");
			String newValue = sc.nextLine();
			UpdateCollection(coll, oldvalue, newValue);
			break;
		}
		case 4: {
			System.out.println("Enter  value which you need to delete");
			String oldvalue = sc.nextLine();
			RemoveFromCollection(coll, oldvalue);
			break;
		}
		case 5: {
			System.out.println("stoping operations .......");
			opt=false;
			break;
		}
		default: {

			System.err.println("Unexpected value: " + inp);
		}
		}

	}

	public static Collection<String> addINtoCollection() {
		for (int i = 0; i <= 3; i++) {
			sc.next();
			System.out.println("entr String");
			String obj = sc.nextLine();
			coll.add(obj);
		}
//		System.out.println("insert   :" + coll);
		return coll;
	}

	public static Collection<String> RemoveFromCollection(Collection<String> str, String ss) {

		Iterator<String> it = str.iterator();

		while (it.hasNext()) {
			if (it.next().equalsIgnoreCase(ss)) {

				it.remove();
			}
		}
		System.out.println("Remove   :" + coll);
		return coll;
	}

	public static Collection<String> UpdateCollection(Collection<String> str, String ss, String nn) {

		List<String> list = (List<String>) str;
		ListIterator<String> it = list.listIterator();

		while (it.hasNext()) {
			if (it.next().equalsIgnoreCase(ss)) {

				it.set(nn);
			}
		}
		System.out.println("updated  :" + coll);

		return coll;

	}
}