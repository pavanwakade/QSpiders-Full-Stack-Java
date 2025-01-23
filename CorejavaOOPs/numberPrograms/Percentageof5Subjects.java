package numberPrograms;

import java.util.Scanner;

public class Percentageof5Subjects {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
System.out.println("enter your first subject marks");
int a=sc.nextInt();

System.out.println("enter your second subject marks");
int b=sc.nextInt();

System.out.println("enter your third subjecct marks");
int c=sc.nextInt();

System.out.println("enter your forth subject marks");
int d=sc.nextInt();

System.out.println("enter your fifth subjecct marks");
int e=sc.nextInt();

int total=(a+b+c+d+e);
System.out.println("total of all subject = "+total);
int percentage=(total*100)/(5*100);
System.out.println("parcentage of 5 subjects = "+percentage+"%");

	}
}
