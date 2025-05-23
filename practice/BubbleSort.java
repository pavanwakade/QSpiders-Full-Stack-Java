import java.util.Arrays;
class BubbleSort{
	public static void main(String[] args) {
		int [] a={1,4,5,6};
		System.out.println(Arrays.toString(a));
		bubblesort(a);
	    System.out.println(Arrays.toString(a));

	}

	// public Bubblesort(){}

	public static  void bubblesort(int a[]){

		for (int i=0;i<a.length-1 ;i++ ) {
			for (int j=0;j<a.length-1-i ;j++ ) {
				// System.out.print("a "+a[i]+"  ");
				// System.out.print("b  "+a[j]+"  ");

				if (a[i]>a[j]) {
					int temp=a[i];
					a[i]=a[j];
					a[j]=temp;
				}
			}
		}
	}
}