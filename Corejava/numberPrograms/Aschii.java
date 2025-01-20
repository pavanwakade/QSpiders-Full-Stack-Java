package numberPrograms;
import java.util.Scanner;
public class Aschii {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the charter");
	char	ch=sc.next().charAt(0);
	int s=ch;
	System.out.println("Ascii value of "+ch +" is "+s);
	sc.close();
	}

}
