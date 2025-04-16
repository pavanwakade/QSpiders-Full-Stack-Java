/**
 * 
 */
package String;

/**
 * String reverse using only Arrays not Any inbuilt methods
 */
public class ReverseStringUsingArray {

	static String s = "pavan";

	public static String reverse(String str) {
		int temp = str.length() - 1;
		char[] c = new char[str.length()];

		for (int i = 0; i < str.length(); i++, temp--) {

			c[temp] = str.charAt(i);

		}

//		String ss = new String(c);
		String ss = "";
		for (int j = 0; j < c.length; j++) {
			ss = ss + c[j];
		}
		return ss;
	}

	public static void main(String[] args) {

		String reversed = reverse(s);

		System.out.println(reversed);
	}
}
