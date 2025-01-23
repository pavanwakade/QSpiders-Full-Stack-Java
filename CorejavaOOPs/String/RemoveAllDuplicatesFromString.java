package String;
import java.util.Scanner;
public class RemoveAllDuplicatesFromString {
	public static void main(String[] args) {
		int count=1;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter String");
		String s=sc.nextLine();
		for (int i = 0; i < s.length(); i++) {
			char a=s.charAt(i);
			for (int j = i+1; j < s.length(); j++) {
				if(a==j)
				{
					count++;
				}
//				a=count;
			}
		}
		System.out.println(s);
		
	}
}