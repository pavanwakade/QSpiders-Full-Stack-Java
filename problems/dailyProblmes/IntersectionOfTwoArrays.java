/**
 * 
 */
package dailyProblmes;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 */
public class IntersectionOfTwoArrays {
	public static void main(String[] args) {
		int[] a = { 1, 2, 3, 4, 5, 6 };
		int[] b = { 6, 7, 8, 9, 4, 5, 6 };
		int[] c = { 4, 5, 6 };

		Set<Integer> set = new HashSet<>();

		for (int i = 0; i < a.length; i++) {

			for (int j = 0; j < b.length; j++) {
				for (int k = 0; k < c.length; k++) {

					if (a[i] == b[j] && a[i] == c[k]) {
						set.add(a[i]);
					}
				}
			}

		}
		System.out.println(set);
	}
}
