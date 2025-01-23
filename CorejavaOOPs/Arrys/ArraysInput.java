package Arrys;

import java.util.Arrays;
import java.util.Scanner;

public class ArraysInput {

	public static void main(String[] args) {
		input(7);

	}

	public static int[] input(int size) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter " + size + " Array Element ..");
		int[] a = new int[size];
		for (int i = 0; i < size; i++) {
			a[i] = sc.nextInt();
		}
//	System.out.println(Arrays.toString(a));
		return a;

	}

}
