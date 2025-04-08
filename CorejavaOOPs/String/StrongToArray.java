package String;

import java.util.Arrays;

public class StrongToArray {

	public static void main(String[] args) {

		String str = "hello pavan";

		char a[] = new char[str.length()];

		int p = a.length - 1;

		for (int i = 0; i < str.length(); i++, p--) {

			a[p] = str.charAt(i);
		}
		System.out.println(Arrays.toString(a));
		
		
//		char c[] = new char[a.length];
//		int count = 0;
//		for (int j = a.length - 1; j >= 0; j--, count++) {
//
//			c[count] = a[j];
//		}
//		System.out.println(Arrays.toString(c));

		

		String[] c = str.split("");
		System.out.println(Arrays.toString(c));

	}
}
