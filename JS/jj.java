import java.util.ArrayList;
import java.util.List;
class jj {
    public static void main(String[] args) {

        // List<String> names = Arrays.asList("John", "Jane", "Jack", "Joe");
        // List<String> longNames = names.stream().filter(name -> name.length() > 3).collect(Collectors.toList());
        // System.out.println("Names longer than 3 characters: " + longNames);



        // Creating an ArrayList
List<String> fruits = new ArrayList<>();
fruits.add("Apple");
fruits.add("Banana");
fruits.add("Apple");  // Duplicates allowed
System.out.println(fruits.get(0));   // Outputs: Banana
    }
}