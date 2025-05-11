package problems;

public class findingDuplicateinString {

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 7, 7, 8};
        findDuplicates(a);
    }

    public static void findDuplicates(int[] arr) {
        int count = 1; // Count of duplicate elements
        boolean hasDuplicates = false;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) { // Compare with all subsequent elements
                if (arr[i] == arr[j]) {
                    count++;
                    System.out.println("Duplicate found: " + arr[i]);
                    hasDuplicates = true;
                }
            }
        }

        System.out.println("Total duplicate pairs: " + count);
        if (!hasDuplicates) {
            System.out.println("No duplicates found.");
        }
    }
}