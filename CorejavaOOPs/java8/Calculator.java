package java8;

@FunctionalInterface
interface calculate {
	double a(double a, double b);
}

public class Calculator {

	public static void main(String[] args) {

		calculate add = (a, b) -> a + b;
		calculate sub = (a, b) -> a - b;
		calculate mul = (a, b) -> a * b;
		calculate div = (a, b) -> a == 0 || b == 0 ? 0 : a / b;

		System.out.println("addition      : " + add.a(10, 1));
		System.out.println("substraction  : " + sub.a(4, 5));
		System.out.println("Multiplication: " + mul.a(4, 5));
		System.out.println("division      : " + div.a(4, 5));
		System.out.println("division      : " + div.a(4, 0));
	}
}
