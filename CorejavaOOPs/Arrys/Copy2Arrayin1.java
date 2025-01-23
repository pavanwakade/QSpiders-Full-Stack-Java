package Arrys;

import java.util.Arrays;

public class Copy2Arrayin1 {

	public static void main(String[] args) {
		int[] a = { 1, 2, 3, 4, 5 };
		int[] b = { 6, 7, 8, 9, 1 };
		int[] c = new int[a.length + b.length];
		int k = 0;

		for (int i = 0; i < a.length; i++, k++) {
			c[k] = a[i];
		}
		for (int j = 0; j < b.length; j++, k++) {
			c[k] = b[j];
		}

		System.out.println(Arrays.toString(c));
		System.out.println(k);

	}

}
