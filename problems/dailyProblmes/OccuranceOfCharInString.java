package dailyProblmes;

import java.util.Arrays;

/**
 * 
 */
public class OccuranceOfCharInString {

	public static void main(String[] args) {

		String s = "hello pavan";

//		char[] sChar =new char[s.length()];
//		
//		for (int i = 0; i < s.length(); i++) {
//			sChar[i]=s.charAt(i);
//			
//		}
		char[] sChar=s.toCharArray();

		for (int i = 0; i < sChar.length; i++) {

			if (sChar[i] == ' ') {
				continue;
			}

			boolean isDuplicate = false;
			for (int j = 0; j < i; j++) {
				if (sChar[i] == sChar[j]) {
					isDuplicate = true;
					break;
				}
			}
			if (!isDuplicate) {
				int c = 0;
				for (int k = 0; k < sChar.length; k++) {

					if (sChar[i] == sChar[k]) {
						c++;
					}
				}
				System.out.println(sChar[i] + "=" + c);
			}

		}

//		System.out.println(Arrays.toString(sChar));

		
	}

}
