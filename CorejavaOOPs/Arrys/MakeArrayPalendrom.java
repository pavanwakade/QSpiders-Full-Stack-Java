package Arrys;

import java.util.Arrays;

public class MakeArrayPalendrom {
	public static void main(String[] args) {
		int []a= {1,2,3,4,5,6};
		int i=0; int j=a.length-1;
		while (i<j) {
			if (a[i]!=a[j]) {
				a[i]=a[j];
			}
			else {
				i++;
				j--;
			}
		}
		System.out.println(Arrays.toString(a));
	}
}
