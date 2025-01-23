package pattern;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 8;

        for (int i = 0; i < n; i++) {
            // P
            for (int j = 0; j < n; j++) {
                if (j == 0
                    || (i == 0 && j < n / 2)
                    || (i == n / 2 && j < n / 2)
                    || (j == n / 2 && i != n / 2)
                ) {
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }

            // A
            for (int j = 0; j < n; j++) {
                if (j == 0
                    || j == n / 2
                    || i == n / 2
                    || i == 0 && j < n / 2
                ) {
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }

            // V
           
            for (int j = 0; j < n; j++) {
                // Print the left and right edges of the "V"
if (j == i || j == n - i - 1)                 
				{
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }
            


            // A
            for (int j = 0; j < n; j++) {
                if (j == 0
                    || j == n / 2
                    || i == n / 2
                    || i == 0 && j < n / 2
                ) {
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }

            // N
            for (int j = 0; j < n; j++) {
                if (j == 0
                    || j == n - 1
                    || i == j
                ) {
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }

            System.out.println();
        }
    }
}
