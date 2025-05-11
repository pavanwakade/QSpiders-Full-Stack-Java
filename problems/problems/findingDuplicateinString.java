class findingDuplicateinString{

	public static void main(String[] args) {
		findingDuplicate("hello");
		
	}

	public static void findingDuplicate(String str){
		char []c=str.toCharArray();
        int count=0;
		for (int i=0;i<c.length ;i++ ) {
			for (int j=i;j<c.length ;j++ ) {
				if (c[i]==c[j]) {
					count++;
				}
			}
			
		}
		System.out.println(count);
	}

}