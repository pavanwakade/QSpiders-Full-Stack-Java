package collection;

import java.util.*;

public class ShuffleString {
	public static void main(String[] args) {

		suffixString("pavan");

	}

	public static void suffixString(String s) {
		List<Character> cc = new ArrayList<Character>();

		for (char c : s.toCharArray()) {

			cc.add(c);
		}
		Collections.shuffle(cc);

		System.out.println(cc);
		String shu = "";
		for (char cccc : cc) {

			shu += cccc;

		}
		System.out.println(shu);
	}
}
