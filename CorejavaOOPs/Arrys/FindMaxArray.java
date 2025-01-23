package Arrys;

import java.util.Arrays;
import java.util.Scanner;

public class FindMaxArray {

	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter size of Array");
		int size=sc.nextInt();
		int []a=new int [size];
		System.out.println("Enter Array Element");
		for (int i = 0; i < size; i++) {
			a[i]=sc.nextInt();
		}
		System.out.println("Array :"+Arrays.toString(a));
		
//		int max=0;
		int max=Integer.MIN_VALUE;
	for (int i = 0; i < a.length; i++) {
		if (a[i]>max) {
			max=a[i];
		}
	}	
	System.out.println("max value in Array :"+max);
	}
}
