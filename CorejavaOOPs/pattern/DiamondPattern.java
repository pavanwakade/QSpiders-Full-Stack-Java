import java.util.Scanner;

public class DiamondPattern {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of rows (half of diamond height): ");
        int n = sc.nextInt();

        // Upper Half
        for (int i = 1; i <= n; i++) {
            // Print spaces
            for (int space = 1; space <= (n - i); space++) {
                System.out.print(" ");
            }
            // Print stars
            for (int star = 1; star <= (2 * i - 1); star++) {
                System.out.print("*");
            }
            System.out.println();
        }

        // Lower Half
        for (int i = n - 1; i >= 1; i--) {
            // Print spaces
            for (int space = 1; space <= (n - i); space++) {
                System.out.print(" ");
            }
            // Print stars
            for (int star = 1; star <= (2 * i - 1); star++) {
                System.out.print("*");
            }
            System.out.println();
        }

        sc.close();
    }
}
