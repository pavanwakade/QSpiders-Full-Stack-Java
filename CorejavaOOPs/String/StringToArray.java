package String;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StringToArray {

	public static void main(String[] args) {

		String str = "hello pavan";

		char a[] = new char[str.length()];

		int p = a.length - 1;

		String s = "";

		for (int i = 0; i < str.length(); i++, p--) {
			a[p] = str.charAt(i);
		}
		System.out.println(Arrays.toString(a));

		for (int i = 0; i < a.length; i++) {
			s = s + a[i];
		}
		System.out.println(s);

		
		
		
		

		System.out.println();
		System.out.println("using collectionFramework");
		// Step 1: Split the string into characters
		String[] c = str.split("");

		System.out.println(Arrays.toString(c));

		// Step 2: Convert the array into a List
		List<String> list = Arrays.asList(c);

		// Step 3: Reverse the list in place
		Collections.reverse(list);

		System.out.println(list);
		
		// Step 4: Join the reversed list back into a string
		String reversed = String.join("", list);

		// Print the reversed string
		System.out.println(reversed);

	}
}
