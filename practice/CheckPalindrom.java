class CheckPalindrom{
	public static void main(String[] args) {
		
		System.out.println(checkpalindrom("pavan"));
	}

	public static boolean checkpalindrom(String str){
		char []c=str.toCharArray();
		int j=c.length-1;
		for (int i=0;i<c.length ;i++,j-- ) {
			if (c[i]!=c[j]) {
				System.out.println("not palindrom");
				return false;
			}
		}
		return true;
	}
}