package Arrys;

import java.util.Arrays;
public class RemoveDuplicate {
	public static void main(String[] args) {
		int []a= {1,2,3,4,3,4,5,7,1,2,4,5,6,7,};
		System.out.println(Arrays.toString(a));
        int count=0;
		
		for (int i = 0; i <a.length; i++) {
			for (int j = i+1; j < a.length; j++) {
				if (a[i]==a[j]) {
					a[i]=-1;
					count++;
				}
			}
		}
		
		int []b=new int[a.length-count];
		int k=0;
		for (int i = 0; i < a.length; i++) {
			if (a[i]!=-1) {
				b[k]=a[i]; 
				k++;
			}
		}	
		System.out.println(Arrays.toString(b));
	}
}