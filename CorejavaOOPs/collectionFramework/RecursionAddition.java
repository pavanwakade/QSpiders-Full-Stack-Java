package collectionFramework;

public class RecursionAddition {

    // Recursive method to find sum of squares
    public static int sumOfSquares(int n) {
        int u=n,start=1;
        if (n == u) {
            return u*u; // base case
        }
        return n * n + sumOfSquares(start + 1); // recursive call
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.println("Sum of squares up to " + n + " = " + sumOfSquares(n));
    }
}
