import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import java.util.Collections;
class BubbleSort{


public static  void bubblesort(int a[]){
		for (int i=0;i<a.length;i++ ) {
			for (int j=0;j<a.length-1-i ;j++ ) {
				if (a[j]>a[j+1]) {
					int temp=a[j];
					a[j]=a[j+1];
					a[j+1]=temp;
				}
			}
		}
	}

	public static void main(String[] args) {
		int [] a={1,4,5,6,9,2,5,3,0};
		System.out.println(Arrays.toString(a));
		bubblesort(a);
	    System.out.println(Arrays.toString(a));



	    Arrays.sort(a);
	    System.out.println(Arrays.toString(a));


	    List<Integer> list=new ArrayList<>();
	    for (int i :a ) {
	    	list.add(i);
	    }

	    Collections.sort(list);
	    	    Collections.reverse(list);
	    System.out.println(list);
	}
}