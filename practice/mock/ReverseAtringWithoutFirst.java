class ReverseAtringWithoutFirst{

	public static void main(String[] args) {
		
		String str="i love coading";

		String newChar=str.split("");
        char ch[]=new char[newChar.length];
		for (int i=0;i<newChar.length ;i++ ) {

			ch[i]=newChar[newChar.length-1-i];

			
		}

		String ss=new String(ch);
		System.out.println(ss);
	}
}