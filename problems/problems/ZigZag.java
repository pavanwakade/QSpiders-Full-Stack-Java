/**
 * 
 */
package problems;

import java.util.Arrays;

/**
 * 
 */
public class ZigZag {

	public static void main(String[] args) {
		int[] a = { 1, 2, 3, 4, 5 };
		int[] b = { 6, 7, 8, 9, 10,4,5,6 };

		int[] c = new int[a.length + b.length];

		int index = 0;

		for (int i = 0; i < c.length; i++) {

			if (i < a.length && i < b.length) {

				c[index++] = a[i];
				c[index++] = b[i++];
			}

			if (i < a.length) {

				c[index++] = a[i];

			}

			if (i < b.length) {

				c[index++] = b[i];
			}
		}
		System.out.println(Arrays.toString(c));
	}
}
