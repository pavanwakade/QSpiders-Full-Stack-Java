import java.util.Arrays;
class Arrays1{
	public static void main(String[] args) {
		

		String str="ama";
		char []c=str.toCharArray();
		int j=c.length-1;
		for (int i=0;i<c.length ;i++,j-- ) {
			if (c[i]!=c[j]) {
				System.out.println("not palindrom");
				break;
			}
		}
		System.out.println("palindrom");
	}
}