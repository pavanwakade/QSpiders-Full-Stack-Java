/**
 * 
 */
package String.methods;

/**
 * 
 */
public class AllMethodsInOne {

	public static void main(String[] args) {

		String s = "Hello 56 good Morning myname is pavan i have my BE in Electronics and Telecommunications";
		String s1 = "Hello good Morning myname is pavan i have my BE in Electronics and Telecommunications";
		/**
		 * 1) Length() --> int it print length of String
		 */

		int length = s.length();

		System.out.println("  (Length method) length of string : " + length);

		/**
		 * 2)charAt(index) -->char it print the value of that index
		 */
		char c = s.charAt(0);

		System.out.println("charAt : " + c);

		/**
		 * 3)codePointAt(index) --> int it print ASCCI value of that index
		 */
		int codePointAt = s.codePointAt(0);

		System.out.println("codePointAt : " + codePointAt);

		/**
		 * 4)codePointBefour(index) -->int it ptint the ASCCI value of befour specific
		 * index
		 */
		int codePointBefour = s.codePointBefore(1);

		System.out.println("codePointBefore : " + codePointBefour);

		/**
		 * compareTo -->int two string to there ubicode/ASCCIi value it return 0 if
		 * string are equal else it return another int value if String not equel
		 */
		System.out.println(s.compareTo(s1));
		
		
		String String=s.concat(s1);
		
		System.out.println(String);

	}
}
