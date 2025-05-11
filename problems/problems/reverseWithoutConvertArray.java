class reverseWithoutConvertArray{
	public static void main(String[] args) {
	System.out.println(	reverse("pavan"));
	}

	public static String reverse(String str){
		String s="";
		for (int i=0;i<str.length() ;i++ ) {
			s+=str.charAt(str.length()-i-1);
		}
		return s;
	}
}