class ReverseString{

	public static void main(String[] args) {
	System.out.println(reverse("paban"));
	}
	public static String reverse(String str){
		char []ch=str.toCharArray();
		String s="";
		int j=ch.length-1;
		for (int i=0;i<ch.length;i++,j-- ) {
			s+=ch[j];
			// System.out.println(s);
		}
		return s;
	}
