package Arrys;

public class displayOddFromArray {
	public static void main(String[] args) {
		int []a= {1,2,3,4,5,6,7,8,9};
		for (int i = 0; i < a.length; i++) {
			if (i%2==0) {
				System.out.println(a[i]);
				
			}
		}
	}
}
