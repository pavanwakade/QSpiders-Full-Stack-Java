package Arrys;

import java.util.Arrays;
import java.util.Scanner;

public class MakeallElementEven {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Size of Array");
		int size=sc.nextInt();
		int []a=new int[size];
		System.out.println("Enter the Array"+" ["+size+"]");
		for (int i = 0; i < size; i++) {
			a[i] = sc.nextInt();
			if (a[i]%2!=0) {
				a[i]=a[i]+1;	
			}
		}
		System.out.println("After make all element even : "+Arrays.toString(a));
	}
}
