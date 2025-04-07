package java8;

@FunctionalInterface
interface Calculatorr {
	int add(int a, int b);
}

public class LambdaExprestion1 {

	public static void main(String[] args) {

		Calculatorr c = (a, b) -> a + b;
		System.out.println(c.add(4, 6));
	}
}
