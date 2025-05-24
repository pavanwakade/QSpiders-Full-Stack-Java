package problems;

import java.util.Arrays;

public class BauncyArrays {
	public static void main(String[] args) {
		int[] a = { 1, 2, 3, 4, 5 };
		int[] b = { 10, 20, 30, 40, 50, 60, 70 };
		System.out.println(Arrays.toString(bouncy(a, b)));
		mearg(a, b);
	}

	public static int[] bouncy(int a[], int b[]) {
		int[] c = new int[a.length + b.length]; // 12
		int j = 0;
		for (int i = 0; i < c.length; i++) {
			if (i < a.length && i < b.length) {
				c[j++] = a[i];
				c[j++] = b[i];
			} else if (i < a.length) {
				c[j++] = a[i];
			} else if (i < b.length) {
				c[j++] = b[i];
			}
		}
		return c;
	}

	public static void mearg(int a[], int b[]) {
		int[] c = new int[a.length + b.length]; // 12
		int j = 0;
		for (int i = 0; i < a.length; i++) {
			c[j] = a[i];
			j++;
		}
		for (int k = 0; k < b.length; k++) {
			c[j] = b[k];
			j++;
		}
		System.out.println(Arrays.toString(c));
	}
}