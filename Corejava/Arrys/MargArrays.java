  package Arrys;

import java.util.Arrays;

public class MargArrays {

	public static void main(String[] args) {
		int []a= {1,2,3,4,5,6,7,8};
		int []b= {10,11,12,13,14,15,16,17};
		int []c=new int[a.length+b.length];
		for (int i = 0; i < c.length; i++) {
			if (i<a.length) {
				c[i]=a[i];
			}
			else {
				c[i]=b[i-a.length];
			}
			
		}
		System.out.println(Arrays.toString(c));
		
	}

}
