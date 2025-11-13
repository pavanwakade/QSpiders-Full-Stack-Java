package collectionFramework;

public class RecursionAddition {

    // Recursive method to find sum of squares
    public static int sumOfSquares(int n) {
        if (n == 1) {
            return 1;
        }
        System.out.println(n);
        return n * n + sumOfSquares(n - 1);
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.println("Sum of squares up to " + n + " = " + sumOfSquares(n));
    }
}
