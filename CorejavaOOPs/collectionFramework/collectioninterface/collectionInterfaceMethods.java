package collectionFramework.collectioninterface;

import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class CollectionInterfaceMethods {

	static Collection<String> coll = new ArrayList<String>();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		addINtoCollection();
		RemoveFromCollection(coll,"java");
	}

	public static Object addINtoCollection() {
		for (int i = 0; i <= 5; i++) {
			System.out.println("entr String");
			String obj = sc.nextLine();
			coll.add(obj);
		}
		return coll;
	}

	public static Object RemoveFromCollection(Collection<String> str,String ss) {
		
		for (String string : str) {
			if (str.contains(ss)) {
				str.remove(ss);
			}
		}
		return str;
		}
	}

