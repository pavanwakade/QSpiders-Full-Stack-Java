package Arrys;
import java.util.Arrays;

public class SwapArray1 {

	public static void main(String[] args) {
		int []a= {1,2,3,4,5,6,7,8};
		int []b= {10,11,12,13,14,15,16,17};
		System.out.println("a[]=" +Arrays.toString(a));
		System.out.println("b[]= "+Arrays.toString(b));
		int []c=new int[a.length];
		for (int i = 0; i < c.length; i++) {
			c[i]=a[i];
			a[i]=b[i];
			b[i]=c[i];
			
		}
		
		System.out.println();//for a empty line understand perpose
		
		System.out.println("a[]=" +Arrays.toString(a));
		System.out.println("b[]= "+Arrays.toString(b));
	}

}
