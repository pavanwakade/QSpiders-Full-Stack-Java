package Arrys;

public class EvenOddCount {
	public static void main(String[] args) {
		int Eount=0;
		int []a= {1,2,3,4,5,6,7,8,9};
		for (int i = 0; i < a.length; i++) {
			if (a[i]%2==0) {
				Eount++;
			}
		}
		System.out.println("Even No :"+Eount);
		System.out.println("Odd No :"+(a.length-Eount));
	}
}

