package java8;
import java.util.*;
import java.util.stream.*;

public class LambdaStreamExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Pavan", "Ankit", "Prakash", "Neha");

        names.stream()
            .filter(name -> name.startsWith("Pavan"))  // Predicate<String>
            .forEach(name -> System.out.println("Name: " + name));  // Consumer<String>
    }
}
