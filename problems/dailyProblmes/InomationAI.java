package dailyProblmes;
/*
 * input -> output
 * 'A' -> 1    
 *  B ->  2,
 *  Z -> 26,
 *  AA-> 27,
 *  AB-> 28
**/

public class InomationAI {
	public static void main(String[] args) {
		String input = "abc";
		input = input.toUpperCase(); 

		int result = 0;
		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			int value = ch - 'A' + 1;
			result = result * 26 + value;
		}

		System.out.println(input + " -> " + result);
	}
}
