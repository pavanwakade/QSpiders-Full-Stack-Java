package collectionFramework.collectioninterface;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;
import java.util.Scanner;

public class CollectionInterfaceMethods {

	static Collection<String> coll = new ArrayList<String>();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		addINtoCollection();
//		System.out.println(RemoveFromCollection(coll, "java"));
		UpdateCollection(coll, "java" , "Rava");
	}

	public static Object addINtoCollection() {
		for (int i = 0; i <= 5; i++) {
			System.out.println("entr String");
			String obj = sc.nextLine();
			coll.add(obj);
		}
//		System.out.println(coll);
		return coll;
	}

	public static Object RemoveFromCollection(Collection<String> str, String ss) {

		Iterator<String> it = str.iterator();

		while (it.hasNext()) {
			if (it.next().equalsIgnoreCase(ss)) {

				it.remove();
			}
		}
//		System.out.println(coll);
		return str;
	}
	
	public static Object UpdateCollection(Collection<String>str,String ss,String nn) {
		
		ListIterator<String>it=(ListIterator<String>) str.iterator();
		
		while(it.hasNext()) {
			if (it.next().equalsIgnoreCase(ss)) {
				it.set(nn);
			}
		}
		System.out.println(coll);
		
		return str;
		
	}
	
	
}
