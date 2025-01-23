package conditionalOperator;
import java.util.Scanner;
class Test
{
public static void main(String []args)
	{
	Scanner sc=new Scanner(System.in);
	int a=sc.nextInt();
	int b=sc.nextInt();
	int c=sc.nextInt();
	int d=a<b ? (a<c ? a:c) : ( a<c ? b:c );
    System.out.println(d);
	d +=20;
	/* System.out.println(d);
	d -=20;
	 System.out.println(d);
	d /=20;
	 System.out.println(d);
	d %=9;
	 System.out.println(d);*/
	

}
}