package pattern;
class Pascal {
    public static void main(String[] args) {
        int n = 5;
        for (int i = 0; i < n; i++) {
            for (int j = n; j > i; j--) {
                System.out.print(" ");
            }
            int num = 1;
            for (int j = 0; j <= i; j++) {
                System.out.print(num + "  ");
                num = num * (i - j) / (j + 1);
            }
            System.out.println();
        }
    }
}