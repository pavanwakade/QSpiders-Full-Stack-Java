import java.util.Arrays;
class reverseString{
	public static void main(String[] args) {
      System.out.println(reverse("pavan"));

  }

  public static String reverse(String str){
  	char []a=str.toCharArray();

    // System.out.println(Arrays.toString(a));

    String s="";

  	int i=a.lenght-1;
    System.out.println(i);

    for (int j = 0; j < a.length; j++, i--){
  		 s+=a[i];
  	}
  	// System.out.println(s);
    return s;
  }
}