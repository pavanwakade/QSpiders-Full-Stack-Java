package problems;

class findingDuplicateinString {

	public static void main(String[] args) {
		int[] a = { 1, 2, 3, 4, 5, 7, 7, 8,7,7,7 };
		findingDuplicate(a);

	}

	public static void findingDuplicate(int c[]) {
		// char []c=str.toCharArray();
		int count = 1;
		for (int i = 0; i < c.length; i++) {
			for (int j = i + 1; j < c.length - 1; j++) {
				if (c[i] == c[j]) {
				System.out.println(c[i]+" "+	count++);
				}
			}
		}
		System.out.println(count);
	}
}