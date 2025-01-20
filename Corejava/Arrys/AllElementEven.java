package Arrys;

public class AllElementEven {

	public static void main(String[] args) {
		int []a= {1,2,3,4,5,6,7,8};
		for (int i = 0; i < a.length; i++) {
			if (a[i]%2!=0) {
				a[i]=a[i]+1;
			}
			System.out.print(a[i] +" ");
		}
	}
}
