import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
class ReverseString{

	public static void main(String[] args) {
		String str="hello world";

		String [] strArray=str.split(" ");

		List<Integer> strss=new ArrayList(Arrays.asList(strArray));
		Collections.reverse(strss);
		System.out.println(strss);

		StringBuilder builde=new StringBuilder(str).reverse();
		System.out.println(builde);

		reverse(str);


		
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
		System.out.println(Arrays.toString(newreversed));
	}
}