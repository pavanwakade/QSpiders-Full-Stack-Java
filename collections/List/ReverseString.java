import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
class ReverseString{

	public static void main(String[] args) {
		String str="hello world";
		int arr[]={1,2,3,4,5};

				reverse(str);
	System.out.println(Arrays.toString(numberReversed(arr)));


		StringBuilder builde=new StringBuilder(str).reverse();
		System.out.println(builde);

		String [] strArray=str.split(" ");
		List<Integer> strss=new ArrayList(Arrays.asList(strArray));
		Collections.reverse(strss);
		System.out.println(strss);

		

	}
		public static int [] numberReversed(int [] num){
		int newarr[]=new int[num.length];
		for (int i=0;i<num.length ;i++ ) {

			newarr[i]=num[num.length-1-i];
		}
		System.out.println(Arrays.toString(newarr));

		return newarr;
	}

	

	static public void reverse(String str){

		char []c=str.toCharArray();

		int end=c.length-1;

		char [] newreversed=new char[c.length];
		String ss="";

		for (int i=0;i<c.length ;i++ ) {

			newreversed[i]=c[end-i];
			ss+=c[end-i];

		}
		System.out.println(ss);
		System.out.println(new String(newreversed));
	}
}