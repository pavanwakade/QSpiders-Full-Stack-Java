package String;
public class OccurrenceChecker {
    public static boolean checkOccurrences(String s) {
        // Split the string by commas
        String[] parts = s.split(",");
        
        // Store the previous length to compare
        int prevLength = 0;
        
        // Print the occurrence map
        System.out.print("{");
        boolean first = true;
        
        // Check each part
        for (String part : parts) {
            int currentLength = part.length();
            
            // Print occurrence
            if (!first) {
                System.out.print(",");
            }
            System.out.print(part.charAt(0) + "=" + currentLength);
            first = false;
            
            // Check if current length is less than or equal to previous length
            if (currentLength <= prevLength) {
                System.out.println("}");
                return false;
            }
            
            // Check if all characters in current part are same
            char firstChar = part.charAt(0);
            for (int i = 1; i < currentLength; i++) {
                if (part.charAt(i) != firstChar) {
                    System.out.println("}");
                    return false;
                }
            }
            
            prevLength = currentLength;
        }
        
        System.out.println("}");
        return true;
    }

    public static void main(String[] args) {
        // Test case 1
        String s1 = "a,bb,ccc,ddd";
        System.out.println("Input: " + s1);
        boolean result1 = checkOccurrences(s1);
        System.out.println(result1);
        
        System.out.println();
        
        // Test case 2
        String s2 = "a,bb,ccc,ddd,e";
        System.out.println("Input: " + s2);
        boolean result2 = checkOccurrences(s2);
        System.out.println(result2);
    }
}