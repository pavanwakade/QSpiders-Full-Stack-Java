public class p {
    public static void main(String[] args) {
        int n = 7; // height of each letter

        for (int i = 0; i < n; i++) {

            // Letter P
            for (int j = 0; j < n; j++) {
                if (j == 0 || (i == 0 && j < n - 1) || (i == n / 2 && j < n - 1) || (j == n - 1 && i > 0 && i < n / 2)) {
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }

            System.out.print("   "); // Space between letters

            // Letter A
            for (int j = 0; j < n; j++) {
                if ((j == 0 || j == n - 1) && i != 0 || i == 0 && j > 0 && j < n - 1 || i == n / 2) {
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }

            System.out.print("   ");

            // Letter V
            for (int j = 0; j < n; j++) {
                if ((j == i && i <= n / 2) || (j == n - 1 - i && i <= n / 2) || (i > n / 2 && (j == n / 2 - 1 || j == n / 2))) {
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }

            System.out.print("   ");

            // Letter A (again)
            for (int j = 0; j < n; j++) {
                if ((j == 0 || j == n - 1) && i != 0 || i == 0 && j > 0 && j < n - 1 || i == n / 2) {
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }

            System.out.print("   ");

            // Letter N
            for (int j = 0; j < n; j++) {
                if (j == 0 || j == n - 1 || i == j) {
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }

            System.out.println(); // Move to next row
        }
    }
}
