package collectionFramework.collectioninterface;

import java.util.*;

public class CollectionInterfaceMethodsCRUD {

	static Collection<String> coll = new ArrayList<>();
	static Scanner sc = new Scanner(System.in);
	static boolean opt = true;

	public static void main(String[] args) {
		while (opt) {
			choice();
		}
	}

	public static void choice() {
		System.out.println("\nChoose an option:");
		System.out.println("1. Insert");
		System.out.println("2. Read");
		System.out.println("3. Update");
		System.out.println("4. Delete");
		System.out.println("5. Stop");
		System.out.print("Your choice: ");

		int inp = sc.nextInt();
		sc.nextLine(); 

		switch (inp) {
		case 1: {
			addINtoCollection();
			break;
		}
		case 2: {
			System.out.println("Data: " + coll);
			break;
		}
		case 3: {
			System.out.print("Enter old value to update: ");
			String oldValue = sc.nextLine();
			System.out.print("Enter new value: ");
			String newValue = sc.nextLine();
			UpdateCollection(coll, oldValue, newValue);
			break;
		}
		case 4: {
			System.out.print("Enter value to delete: ");
			String value = sc.nextLine();
			RemoveFromCollection(coll, value);
			break;
		}
		case 5: {
			System.out.println("Stopping operations...");
			opt = false;
			break;
		}
		default: {
			System.err.println("Unexpected value: " + inp);
			break;
		}
		}
	}

	public static Collection<String> addINtoCollection() {
		for (int i = 0; i < 3; i++) {
			System.out.print("Enter String: ");
			String obj = sc.nextLine();
			coll.add(obj);
		}
		return coll;
	}

	public static Collection<String> RemoveFromCollection(Collection<String> str, String ss) {
		Iterator<String> it = str.iterator();
		boolean found = false;
		while (it.hasNext()) {
			if (it.next().equalsIgnoreCase(ss)) {
				it.remove();
				found = true;
			}
		}
		if (found)
			System.out.println("Removed: " + ss);
		else
			System.out.println("Value not found to remove.");
		return str;
	}

	public static Collection<String> UpdateCollection(Collection<String> str, String ss, String nn) {
		List<String> list = (List<String>) str;
		ListIterator<String> it = list.listIterator();
		boolean updated = false;

		while (it.hasNext()) {
			if (it.next().equalsIgnoreCase(ss)) {
				it.set(nn);
				updated = true;
			}
		}

		if (updated)
			System.out.println("Updated '" + ss + "' to '" + nn + "'");
		else
			System.out.println("Value not found to update.");
		return str;
	}
}
