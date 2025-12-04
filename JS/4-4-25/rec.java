class rec {

    // input 1,2,3,4   
    // output=10  using recurtion
    public static void main(String[] args) {
        int num = 4;
        System.out.println(sum(num, 4));
    }

    private static char[] sum(int num, int i) {
        if (i == 0) {
            return new char[]{(char) num};
        }
        num = num + i;
        return sum(num, i - 1);
    }

    
}