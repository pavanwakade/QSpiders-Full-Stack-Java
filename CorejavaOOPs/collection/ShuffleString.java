package collection;
//
//import java.security.AllPermission;
//import java.util.*;
//
public class ShuffleString {
//	public static void main(String[] args) {
//
////		suffixString("pavan");
//
//	}
//
//	public static void suffixString(String s) {
//		List<Character> cc = new ArrayList<Character>();
//
//		for (char c : s.toCharArray()) {
//
//			cc.add(c);
//		}
//		Collections.shuffle(cc);
//
////		System.out.println(cc);
//		String shu = "";
//		for (char cccc : cc) {
//
//			shu += cccc;
//
//		}
//		System.out.println(shu);
//	}
//	
//	public static void allpossibleSuffix(String s) {
//		
//		
//		
//	}
//}






    public static void main(String[] args) {
        String input = "abcd";
        printPermutations("", input);
    }

    static void printPermutations(String prefix, String str) {
        if (str.length() == 0) {
            System.out.println(prefix);
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            // Fix one character and permute the rest
            String remaining = str.substring(0, i) + str.substring(i + 1);
            printPermutations(prefix + str.charAt(i), remaining);
        }
    }
}
