class UPLO{
	public static void main(String[] args) {
		String str="hello From pavan";  
		String ss="";


		//more memory
		String s[]=str.split("");

		for (int i=0;i<s.length ;i++ ) {
			if (i%2==0) {
				ss+=s[i].toUpperCase();
			}
			else{
				ss+=s[i].toLowerCase();
			}

		}
		System.out.println(ss);

       ss="";


//low memory
for (int i =0;i<str.length();i++ ) {
	String ch=str.substring(i,i+1);
	if (i%2==0) {
		ss+=ch.toUpperCase();
	}
	else{
		ss+=ch.toLowerCase();
	}
}
System.out.println(ss);

	}
}