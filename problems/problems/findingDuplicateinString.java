class findingDuplicateinString {
    public static void main(String[] args) {
        findingDuplicate("hello");
    }

    public static void findingDuplicate(String str) {
        char[] c = str.toCharArray();
        int duplicateCount = 0;
        // Array to mark characters as counted
        boolean[] counted = new boolean[c.length];
        
        for (int i = 0; i < c.length; i++) {
            // Skip if already counted as a duplicate
            if (counted[i]) continue;
            
            int charCount = 0;
            for (int j = i; j < c.length; j++) {
                if (c[i] == c[j]) {
                    charCount++;
                    if (j != i) counted[j] = true; // Mark duplicates as counted
                }
            }
            // If the character appears more than once, increment duplicateCount
            if (charCount > 1) {
                duplicateCount++;
                System.out.println("Duplicate found: '" + c[i] + "' appears " + charCount + " times");
            }
        }
        System.out.println("Total number of duplicate characters: " + duplicateCount);
    }
}