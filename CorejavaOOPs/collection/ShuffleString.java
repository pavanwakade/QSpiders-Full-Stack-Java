package collection;

import java.util.*;

public class ShuffleString {
	public static void main(String[] args) {

		String s = "abcd";
		List<Character> cc = new ArrayList<Character>();

		for (char c : s.toCharArray()) {

			cc.add(c);
		}
		Collections.shuffle(cc);

	}
}
