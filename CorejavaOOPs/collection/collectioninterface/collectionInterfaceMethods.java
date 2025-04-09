package collection.collectioninterface;

import java.util.Arrays;

public class collectionInterfaceMethods {

	public static void main(String[] args) {
		String string = "hello pavan";

		System.out.println(string);
		
		String[] string2 = string.split("");

		System.out.println(Arrays.toString(string2));

		String a = String.join("", string2);
		
		System.out.println(a);
	}
}
