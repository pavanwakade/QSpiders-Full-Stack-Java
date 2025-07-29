package dailyProblmes;

/**
 * 
 */
public class OccuranceOfCharInString {

	public static void main(String[] args) {

		checkOccurancde("hello");
	}

	public static void checkOccurancde(String str) {

		char[] ch = str.toCharArray();

		for (int i = 0; i < ch.length; i++) {

			boolean isDuplicate = false;
			for (int j = 0; j < ch.length; j++) {

				if (ch[i] == ch[j]) {
					isDuplicate = true;
					break;
				}
			}
			if (isDuplicate) {
				int Count = 0;
				for (int k = 0; k < ch.length; k++) {
					if (ch[i] == ch[k]) {
						Count++;
					}
				}
				System.out.println(ch[i] + "-->" + Count);
			}
		}
	}
}