package String;

import java.util.Arrays;

public class StringToArray {

    public static void main(String[] args) {

        // Input String
        String str = "hello pavan";

        // Character array to store reversed characters
        char a[] = new char[str.length()];

        // Pointer to the last index of the array
        int p = a.length - 1;

        // String to store the reversed result
        StringBuilder sb = new StringBuilder();

        // Loop to reverse the string
        for (int i = 0; i < str.length(); i++, p--) {
            a[p] = str.charAt(i); // Assign characters to the array in reverse order
            sb.append(a[p]);      // Append reversed characters to the StringBuilder
        }

        // Convert StringBuilder to String
        String s = sb.toString();

        // Output the reversed array and string
        System.out.println(Arrays.toString(a)); // Print reversed character array
        System.out.println(s);                  // Print reversed string
    }
}