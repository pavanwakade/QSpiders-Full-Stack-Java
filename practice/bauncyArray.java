// package problems;

import java.util.Arrays;

class bauncyArray {
	public static void main(String[] args) {
		int[] a = { 1, 2, 3, 4, 5 };
		int[] b = { 10, 20, 30, 40, 50 };
		System.out.println(Arrays.toString(bouncy(a, b)));
	}

	public static int[] bouncy(int a[], int b[]) {
		int[] c = new int[a.length + b.length];
		int j = 0;
		for (int i = 0; i < c.length; i++) {
			if (i < a.length && i < b.length) {
				c[j++] = a[i];
				c[j++] = b[i];
			}
			if (i < a.length) {
				c[j++] = a[i];
			}

			if (i < b.length) {
				c[j++] = a[i];
			}
		}
		return c;
	}
}