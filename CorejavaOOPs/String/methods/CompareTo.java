/**
 * 
 */
package String.methods;

/**
 * 
 */
public class CompareTo {

	public static void main(String[] args) {

		String s = "pavan wakade";

		String s1 = "pavan wakade";

		String s2 = "name is pavan";

		System.out.println(s.compareTo(s1));

		System.out.println(s1.compareTo(s2));
		// return type is int
		// it compare by every charactor by sequence
//		if all sequenced charactor  are equal thhan return 0 or return diff of ASCCI values 
	}
}
