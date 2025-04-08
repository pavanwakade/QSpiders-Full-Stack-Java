package String;

import java.util.Arrays;

public class StrongToArray {

	public static void main(String[] args) {

		String str = "hello pavan";

		char a[] = new char[str.length()];

		for (int i = 0; i < str.length(); i++) {

			a[i] = str.charAt(i);
		}
		char c[]=new char[a.length];
		
//		for(int j=a.length;j>0;j--) {
//			
//			c[j]=a[j];
//		}
		
		
		System.out.println(Arrays.toString(a));

//		String[] c = str.split("");
//		System.out.println(Arrays.toString(c));

	}
}
