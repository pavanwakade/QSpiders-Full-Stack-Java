package Arrys;

public class FindMinValueIeInArray {

	public static void main(String[] args) {
		int[] a = { 1, 2, 4, 5, 6, 0, 79, 9 };
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < a.length; i++) {
			if (a[i] < min) {
				min = a[i];
			}
		}
		System.out.println("min value in array :" + min);
	}
}
