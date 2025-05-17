import java.util.Arrays;
class ReverseString{

	public static void main(String[] args) {
		String str=
		reverse("hello world");
	}

	static public void reverse(String str){

		char []c=str.toCharArray();

		int end=c.length-1;

		char [] newreversed=new char[end+1];
		String ss="";

		for (int i=0;i<end ;i++ , end-- ) {

			newreversed[i]=c[end];
			ss+=c.charAt(end);

		}
		System.out.println(ss);
		System.out.println(Arrays.toString(newreversed));
	}
}