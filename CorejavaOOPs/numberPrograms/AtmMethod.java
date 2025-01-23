package numberPrograms;

import java.util.Scanner;

public class AtmMethod {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter first Number!!!");
        double num1 = s.nextInt();
        System.out.println("Enter Second Number!!!");
        double num2 = s.nextInt();
        int result = (int) add(num1, num2);
        System.out.println("Addition : " + result);

    }

    public static double add(double num1, double num2) {
        return num1 + num2;
    }
}