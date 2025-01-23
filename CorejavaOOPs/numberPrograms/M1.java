package numberPrograms;

import java.util.Scanner;
public class M1 {
    public static Scanner sc = new Scanner(System.in);

    public static void a() {
        System.out.println("pavan");
    }

    public static void b() {
        System.out.println("wakade");
    }

    public static void c(int a, int b) {
        System.out.println("Enter a number!!");
        a = sc.nextInt();
        System.out.println("Enter a number!!");
        b = sc.nextInt();
        int sum = a + b;
        System.out.println(sum);
    }

    public static void main(String[] args) {

        a();
        b();
        c(3, 4);
    }
}