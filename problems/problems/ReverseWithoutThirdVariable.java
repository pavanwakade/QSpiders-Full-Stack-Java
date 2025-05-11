class ReverseWithoutThirdVariable{
public static void main(String[] args) {
	System.out.println(reverse("pavan"));
}
public static String reverse(String str){
	char []s=str.toCharArray();
    String ss="";
	for (int i=0;i<s.length ; i++) {
		ss+=s[s.length-i-1];
	}
	return ss;
}
}