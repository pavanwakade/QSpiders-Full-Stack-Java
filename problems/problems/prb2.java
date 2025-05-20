package problems;

import java.util.Set;
import java.util.TreeSet;

public class prb2 {

	public static void main(String[] args) {
		int[] a = { 1, 3, 3, 5, 3, 5 };
		int count = 0;
//		List<Integer> list = new ArrayList<Integer>(Arrays.asList(a));
		Set<Integer> treeSet = new TreeSet<Integer>();

		// Add elements from array to the TreeSet
		for (int value : a) {
			treeSet.add(value);
		}

		for (Integer integer : treeSet) {
			count++;
		}
		System.out.println(count);
	}
}

//
//
//package problems;
//
//import java.util.Arrays;
//
//class bauncyArray {
//	public static void main(String[] args) {
//		int[] a = { 1, 2, 3, 4, 5 };
//		int[] b = { 10, 20, 30, 40, 50 };
//		System.out.println(Arrays.toString(bouncy(a, b)));
//	}
//
//	public static int[] bouncy(int a[], int b[]) {
//		int[] c = new int[a.length + b.length];
//		int j = 0;
//		for (int i = 0; i<c.length; i++) {
//			if (i < a.length && i < b.length) {
//				c[j++] = a[i];
//				c[j++] = b[i];
//			}
//			if (i < a.length) {
//				c[j++] = a[i];
//			}
//
//			if (i < b.length) {
//				c[j++] = a[i];
//			}
//		}
//		return c;
//	}
//}