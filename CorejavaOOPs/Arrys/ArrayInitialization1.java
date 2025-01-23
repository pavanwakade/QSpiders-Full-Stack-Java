package Arrys;

public class ArrayInitialization1 {
	public static void main(String[] args) {
		int[] a = { 1, 2, 3, 4, 5, 6, 7, 8, 9, };
		System.out.println(a[0]);
		System.out.println(a[1]);
		System.out.println(a[2]);
		System.out.println(a[3]);
		System.out.println(a[4]);
		System.out.println(a[5]);
		System.out.println(a[6]);
		System.out.println(a[7]);
		System.out.println(a[8]);

		System.out.println("-----------");

		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}

		System.out.println("Length of Array is : " + a.length);// lengtfor checking length
	}

}
