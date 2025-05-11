class findingDuplicateinString{

	public static void main(String[] args) {
		int []a={1,2,3,4,5,7,7,8,};
		findingDuplicate(a);
		
	}

	public static void findingDuplicate(int c[]){
		// char []c=str.toCharArray();
        int count=0;
		for (int i=0;i<c.length ;i++ ) {
			for(int j=i;j<c.length-1;j++){
				// if(c[i]==c[j]){
			System.out.println(c[i]+" " +c[j]);
				// }
			}
		}
		// System.out.println(count);
	}

}