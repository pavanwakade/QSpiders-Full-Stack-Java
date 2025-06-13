package collectionFramework.collectioninterface;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CollectionInterfaceMethodsCRUD {

	static Collection<String> coll = new ArrayList<String>();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
//		addINtoCollection();
//		UpdateCollection(coll, "sql", "MySQL");
//		RemoveFromCollection(coll, "pavan");
//		System.out.println(coll);

		Collection<String> ss = new ArrayList<>();
		ss.add("pavan");
		ss.add("ravan");

//			coll.removeAll(ss);
//			System.out.println(coll);
//			System.out.println(coll.isEmpty());
//			System.out.println(coll.size());
//			System.out.println(coll.contains("pavan"));
		
//		toArray (specific type)
//		List<Integer> names = new ArrayList<>();
//		for (int ch = 1; ch <= 9; ch++) {
//			names.add(ch);
//		}
//		System.out.println(names);
//		Integer[] nameObjects = names.toArray(new Integer[0]);
//		System.out.println("arrays  :" + Arrays.toString(nameObjects));
		
		
		
		
//		toArray (object)
		
		List<Object> str = new ArrayList<>();
		
		str.add("pavan");
		str.add("ravan");
		str.add(true);
		str.add(1);
		str.add(null);
		
		Object [] obj=str.toArray();
		System.out.println(  Arrays.toString(obj)  );
		
		
		
		

	}

	public static Collection<String> addINtoCollection() {
		for (int i = 0; i <= 3; i++) {
			System.out.println("entr String");
			String obj = sc.nextLine();
			coll.add(obj);
		}
		System.out.println("insert   :" + coll);
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
		return str;
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

		return str;

	}

}
