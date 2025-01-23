package String;

public class LongestPelindrom {

	public static void main(String[] args) {

		String s = "abaobdefa";
//		String s = "abadeffed";
		System.out.println(longestPalSub(s));

	}

	public static String longestPalSub(String s) {
		String temp = putSpace(s);
		int max = 0;
		int x = 0, y = 0, p = 0, q = 0;

		for (int i = 0; i < temp.length() - 1; i++) {
			p = i - 1;
			q = i + 1;
			while ((p >= 0 && q < temp.length()) && temp.charAt(p) == temp.charAt(q)) {
				p--;
				q++;
			}
			if (i - p > 1) {
				if ((q - p) + 1 > max) {
					max = q - p + 1;
					x = p;
					y = q;
				}
			}
		}
		String ans = "";
		for (int i = x + 1; i < y; i++) {
			if (temp.charAt(i) != ' ') {
				ans = ans + temp.charAt(i);
			}
		}
		return ans;
	}

	public static String putSpace(String s) {
		String ans = "";

		for (int i = 0; i < s.length() - 1; i++) {
			ans = ans + s.charAt(i) + " ";
		}
		ans = ans + s.charAt(s.length() - 1);
		return ans;
	}
}