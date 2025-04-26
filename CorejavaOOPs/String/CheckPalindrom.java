/**
 * 
 */
package String;

import java.util.Arrays;

/**
 * 
 */
public class CheckPalindrom {
	public static void main(String[] args) {

//		int a = 12156789;

//		String s = String.valueOf(a);
		String s= "madam";

//		char[] c = new char[s.length()];
//
//		for (int i = 0; i < s.length(); i++) {
//			c[i] = s.charAt(i);
//		}

		char c[] = s.toCharArray();
		
//		String []c=s.split("");

//		System.out.println(Arrays.toString(c));

		for (int i = 0, j = c.length - 1; i < j; i++, j--) {

			if (c[i] == c[j]) {
				i++;
				j--;
			} else {
				System.out.println("not palindrom");
				return;
			}
		}

		System.out.println("palindrom");
	}
}
