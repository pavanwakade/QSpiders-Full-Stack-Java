package String;

import java.util.Scanner;

public class CountWordsinString {
	public static void main(String[] args) {
		System.out.println("Enter the String :");
		Scanner sc=new Scanner(System.in);
		String s=sc.nextLine();
//		String s="i love";
		int count=0;
		for (int i = 0; i < s.length(); i++) {
			char a=s.charAt(i);
			if (a==' ') {
				count++;
			}
		}
		System.out.println("total words in String : "+(count+1));
	}
}
