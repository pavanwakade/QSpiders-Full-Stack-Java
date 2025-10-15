package java8.java8.src.LambdaExpression;

import java.util.Scanner;

@FunctionalInterface
interface lambda {
    int mainInterface(int a, int b);
}

public class Calculator {

    public static void main(String[] args) {

        lambda add = (a, b) -> a + b;

        lambda sub = (a, b) -> a - b;

        lambda mul = (a, b) -> a * b;

        lambda div = (a, b) -> a / b;


        System.out.println(mul.mainInterface(2, 4));























//        Scanner sc = new Scanner(System.in);
//
//        System.out.println("Enter Choice");
//        System.out.println("1 -> Addition");
//        System.out.println("2 -> Substriction");
//        System.out.println("3 -> Multiplication");
//        System.out.println("4 -> Division");
//
//        int input = sc.nextInt();
//        System.out.println("Enter first");
//        int a = sc.nextInt();
//        System.out.println("Enter Second");
//        int b = sc.nextInt();
//
//        switch (input) {
//            case 1:
//                System.out.println("Addition -> " + add.mainInterface(a, b));
//                break;
//
//            case 2:
//                System.out.println("Substriction -> " + sub.mainInterface(a, b));
//                break;
//
//
//            case 3:
//                System.out.println("Multiplication -> " + mul.mainInterface(a, b));
//                break;
//
//
//            case 4:
//                System.out.println("Division -> " + div.mainInterface(a, b));
//                break;
//        }
    }
}