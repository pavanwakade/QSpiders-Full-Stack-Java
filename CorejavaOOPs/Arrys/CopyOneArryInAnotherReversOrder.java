package Arrys;

import java.util.Arrays;
import java.util.Scanner;

public class CopyOneArryInAnotherReversOrder {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a Size of Array");
        int size = sc.nextInt();
        int[] a = new int[size];
        int[] b = new int[size];
        System.out.println("Enter the Array");
        for (int i = 0; i < size; i++) {
            a[i] = sc.nextInt();            
        }
        System.out.println("Original Arrays " + Arrays.toString(a));
        
        for (int i = a.length-1,j=0; i >=0; i--,j++) {
            b[j] = a[i];
        }
        System.out.println("Reversed Arrays " + Arrays.toString(b));
    }
}
