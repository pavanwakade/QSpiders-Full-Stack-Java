package pattern;

public class NumberPattern {

	public static void main(String[] args) {

		int rows = 5; // Number of rows
		int num = 1;

		for (int i = 1; i <= rows; i++) {
			int temp = num;

			for (int j = 1; j <= i; j++) {
				System.out.print(temp + " ");
				temp += (rows - j); // Increment based on the row difference
			}

			System.out.println();
			num++;
		}
	}
}