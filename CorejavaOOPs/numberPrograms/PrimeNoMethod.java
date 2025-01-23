package numberPrograms;

import java.util.Scanner;

public class PrimeNoMethod {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);   
        System.out.println("Enter a Number!!!!");
        int n = sc.nextInt();
        checkPrime(n);
    }

    public static int checkPrime(int a) {
        int deno = 2;
        for (; deno <a; deno++) {
            if (a%deno==0) {
                break;
            }
        }
        if (a == deno) {
            System.out.println(a + " Is Prime Number!!!!");
        } else {
            System.out.println(a + " Is Not Prime Number!!!!");
        }
		return deno;
    }
}
