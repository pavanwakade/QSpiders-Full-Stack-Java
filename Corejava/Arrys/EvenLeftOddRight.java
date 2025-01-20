package Arrys;

import java.util.Arrays;

public class EvenLeftOddRight {
	public static void main(String[] args) {

		int[] a = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int[] b = new int[a.length];

		
		int k = 0;
		int j = a.length - 1;

		for (int i = 0; i < a.length; i++) {
			if (a[i] % 2 == 0) {
				b[k] = a[i];
				k++;
			} else {
				b[j] = a[i];
				j--;
			}
		}
		
		
		System.out.println(Arrays.toString(b));
	}
}