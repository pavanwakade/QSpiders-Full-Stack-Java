package collection.collectioninterface;

import java.util.Arrays;

public class collectionInterfaceMethods {

	public static void main(String[] args) {
		String string = "hello pavan";

		String[] string2 = string.split("");

		System.out.println(Arrays.toString(string2).join("", string2));

		System.out.println(Arrays.toString(string2));
	}
}
