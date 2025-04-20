package other;

public class paythagoras {

    public static void main(String[] args) {
        int a = 50;
        int c = 0;

        for (int i = 0; i < a; i++) {

            for (int j = 0; j < a; j++) {

                for (int k = 0; k < a; k++) {

                    if ((i != 0 && j != 0 && k != 0 && (i * i) + (j * j) == (k * k))) {

                        c++;
                        System.out.println(c + " : " + i + " + " + j + " = " + k);
                    }
                }
            }
        }
    }
}