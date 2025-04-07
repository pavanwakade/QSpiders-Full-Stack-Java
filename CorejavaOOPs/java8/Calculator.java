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

		System.out.println(add.a(1, 1));
		System.out.println(sub.a(4, 5));
	}
}
