package java8;

import java.util.Iterator;

//
//@FunctionalInterface
//interface calculate {
//	int a(int a, int b);
//    static double a(double a,double b) {
//		return a+b;
//	} 
//}
//
//public class Calculator {
//	public static void main(String[] args) {
//
//		calculate add = (a, b) -> a + b;
//		calculate sub = (a, b) -> a - b;
//		calculate mul = (a, b) -> a * b;
//		calculate div = (a, b) -> a == 0 || b == 0 ? 0 : a / b;
//
//		System.out.println("addition      : " + add.a(10, 1));
//		System.out.println("substraction  : " + sub.a(4, 5));
//		System.out.println("Multiplication: " + mul.a(4, 5));
//		System.out.println("division      : " + div.a(4, 5));
//		System.out.println("division      : " + div.a(4, 0));
//		
//	}
//}


@FunctionalInterface
interface CalInterface {
	double calculate(double... nums);
}

public class Calculator {
	public static void main(String[] args) {
		// Lambda: sum of all numbers
		CalInterface add = (double... nums) -> {
			double sum = 0;
			for (double n : nums) {
				sum += n;
			}
			return sum;
		};

		System.out.println(add.calculate(10, 20)); // 30.0
		System.out.println(add.calculate(1, 2, 3, 4, 5, 1, 2, 3, 4, 5));// 30.0
		System.out.println(add.calculate()); // 0.0
	}
}
