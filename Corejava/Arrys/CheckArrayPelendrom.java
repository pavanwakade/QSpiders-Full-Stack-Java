package Arrys;

import java.util.Scanner;

public class CheckArrayPelendrom {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in); 
		System.out.println("Enter size of Array"); 
		int size=sc.nextInt();		
		System.out.println("Enter "+size+" Array Element .."); 
		
		int[] a = new int[size]; 
		for (int i = 0; i < size; i++) {
			a[i] = sc.nextInt(); 
			} 
		
		int i=0,j=a.length-1;
			while (i<j) {
				if (a[i]!=a[j]) {
					System.out.println("Array is not palendrom");
					break;
				}
				else {
				i++;
				j--;
				}
				if (i>=j) {
					System.out.println("Array is palendrom");
				}
			}
		}
	}
