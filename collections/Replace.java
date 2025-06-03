class Replace{
	public static void main(String[] args) {
		String s="JavaaaaOOoooo";

		String ss=s.replace('a','z');
		System.out.println(ss);

        char c []=s.toCharArray();
		for (int i=0;i<s.length() ;i++ ) {
			
			if (c.toCharAt(i)=='a') {
				
				c[i]='z';
			}
		}
	}
}