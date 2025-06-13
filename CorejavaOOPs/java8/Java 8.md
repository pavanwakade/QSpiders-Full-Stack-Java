# Java 8

Here's a Java 8 tutorial with examples in both traditional and lambda style:

## 1. Introduction to Java 8

Java 8 was a major update released in March 2014 with significant features like lambda expressions, the Stream API, and functional interfaces.

## 2. Lambda Expressions

Lambda expressions let you write concise code for implementing functional interfaces (interfaces with a single abstract method).

### Traditional vs Lambda Syntax

**Traditional Way:**
```java
// Creating a Runnable using anonymous class
Runnable runnable = new Runnable() {
    @Override
    public void run() {
        System.out.println("Hello from traditional way");
    }
};
```

**Lambda Way:**
```java
// Creating a Runnable using lambda
Runnable runnable = () -> System.out.println("Hello from lambda");
```

### Lambda Expression Format
```
(parameters) -> expression
```
or
```
(parameters) -> { statements; }
```

## 3. Functional Interfaces

Interfaces with exactly one abstract method. Java 8 introduced `@FunctionalInterface` annotation.

**Example:**
```java
@FunctionalInterface
interface Calculator {
    int calculate(int x, int y);
}

// Traditional implementation
Calculator addition = new Calculator() {
    @Override
    public int calculate(int x, int y) {
        return x + y;
    }
};

// Lambda implementation
Calculator addition = (x, y) -> x + y;

// Using it
System.out.println(addition.calculate(5, 3)); // Outputs: 8
```

## 4. Common Functional Interfaces

Java 8 provides predefined functional interfaces in `java.util.function` package:

### 4.1 Predicate

**Traditional:**
```java
List<String> names = Arrays.asList("John", "Jane", "Jack", "Joe");
List<String> longNames = new ArrayList<>();

for(String name : names) {
    if(name.length() > 3) {
        longNames.add(name);
    }
}
```

**Lambda:**
```java
List<String> names = Arrays.asList("John", "Jane", "Jack", "Joe");
List<String> longNames = names.stream()
                             .filter(name -> name.length() > 3)
                             .collect(Collectors.toList());
```

### 4.2 Consumer

**Traditional:**
```java
List<String> names = Arrays.asList("John", "Jane", "Jack");
for(String name : names) {
    System.out.println(name);
}
```

**Lambda:**
```java
List<String> names = Arrays.asList("John", "Jane", "Jack");
names.forEach(name -> System.out.println(name));
// Even shorter: names.forEach(System.out::println);
```

### 4.3 Function

**Traditional:**
```java
List<String> names = Arrays.asList("John", "Jane", "Jack");
List<Integer> nameLengths = new ArrayList<>();
for(String name : names) {
    nameLengths.add(name.length());
}
```

**Lambda:**
```java
List<String> names = Arrays.asList("John", "Jane", "Jack");
List<Integer> nameLengths = names.stream()
                               .map(name -> name.length())
                               .collect(Collectors.toList());
// Or: .map(String::length)
```

## 5. Method References

Method references are shortcuts for lambda expressions calling a single method:

```java
List<String> names = Arrays.asList("John", "Jane", "Jack");

// Lambda expression
names.forEach(name -> System.out.println(name));

// Method reference equivalent
names.forEach(System.out::println);
```

Types of method references:
- Static method: `ClassName::staticMethod`
- Instance method of object: `objectRef::instanceMethod`
- Instance method of type: `ClassName::instanceMethod`
- Constructor: `ClassName::new`

## 6. Stream API

Streams allow you to process collections of objects in a declarative way.

**Traditional:**
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
List<Integer> evenSquares = new ArrayList<>();
for(Integer n : numbers) {
    if(n % 2 == 0) {
        evenSquares.add(n * n);
    }
}
```

**Stream API:**
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
List<Integer> evenSquares = numbers.stream()
                                .filter(n -> n % 2 == 0)
                                .map(n -> n * n)
                                .collect(Collectors.toList());
```

Common stream operations:
- `filter()` - select elements based on a condition
- `map()` - transform elements
- `collect()` - convert stream back to collectionFramework
- `reduce()` - combine elements
- `sorted()` - sort elements
- `limit()` - limit stream size

## 7. Optional Class

Handles null values elegantly to avoid NullPointerException:

**Traditional:**
```java
String str = getString();
if(str != null) {
    return str.toUpperCase();
} else {
    return "NONE";
}
```

**Optional:**
```java
return Optional.ofNullable(getString())
             .map(String::toUpperCase)
             .orElse("NONE");
```

## 8. Default Methods in Interfaces

Java 8 allows interfaces to have default method implementations:

```java
interface Vehicle {
    void accelerate();
    
    default void honk() {
        System.out.println("Beep beep!");
    }
}

class Car implements Vehicle {
    @Override
    public void accelerate() {
        System.out.println("Car is accelerating");
    }
    // No need to implement honk() as it has a default implementation
}
```

## 9. Date and Time API

Java 8 introduced a new date and time API in the `java.time` package:

```java
// Current date
LocalDate today = LocalDate.now();

// Specific date
LocalDate birthday = LocalDate.of(1990, Month.JANUARY, 1);

// Current time
LocalTime now = LocalTime.now();

// Date and time
LocalDateTime dateTime = LocalDateTime.now();

// Adding/subtracting time
LocalDate nextWeek = today.plusWeeks(1);
```

I hope these tutorial notes help you understand Java 8's key features! Would you like more examples of any particular concept?
