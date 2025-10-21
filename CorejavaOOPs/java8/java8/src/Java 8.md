# Java 8 Comprehensive Study Notes

## Table of Contents
1. [Lambda Expressions](#lambda-expressions)
2. [Functional Interfaces](#functional-interfaces)
3. [Method References](#method-references)
4. [Stream API](#stream-api)
5. [Optional Class](#optional-class)
6. [Default Methods](#default-methods)
7. [Static Methods in Interfaces](#static-methods-in-interfaces)
8. [Date and Time API](#date-and-time-api)
9. [Collectors](#collectors)
10. [Parallel Streams](#parallel-streams)
11. [CompletableFuture](#completablefuture)

## Lambda Expressions

Lambda expressions are anonymous functions that can be used to create delegates or expression tree types. They provide a clear and concise way to represent one method interface using an expression.

### Syntax
```java
(parameters) -> expression
// or
(parameters) -> { statements; }
```

### Examples
```java
// Traditional approach
Runnable r1 = new Runnable() {
    @Override
    public void run() {
        System.out.println("Hello World");
    }
};

// Lambda expression
Runnable r2 = () -> System.out.println("Hello World");

// With parameters
Comparator<String> comp = (s1, s2) -> s1.compareTo(s2);

// Multiple statements
Function<Integer, Integer> square = (x) -> {
    int result = x * x;
    return result;
};
```

### Benefits
- Reduced boilerplate code
- Improved readability
- Functional programming support
- Better performance with lazy evaluation

## Functional Interfaces

A functional interface is an interface with exactly one abstract method. They can have multiple default and static methods.

### Built-in Functional Interfaces

#### Predicate<T>
Tests a condition and returns boolean.
```java
Predicate<String> isEmpty = String::isEmpty;
Predicate<Integer> isPositive = x -> x > 0;

// Combining predicates
Predicate<String> isNotEmpty = isEmpty.negate();
Predicate<String> isLongAndNotEmpty = isEmpty.negate().and(s -> s.length() > 5);
```

#### Function<T, R>
Takes one argument and returns a result.
```java
Function<String, Integer> stringLength = String::length;
Function<Integer, String> intToString = Object::toString;

// Function composition
Function<String, String> upperCase = String::toUpperCase;
Function<String, String> addPrefix = s -> "Prefix: " + s;
Function<String, String> composed = upperCase.andThen(addPrefix);
```

#### Consumer<T>
Takes one argument and returns nothing (void operation).
```java
Consumer<String> printer = System.out::println;
Consumer<List<String>> listPrinter = list -> list.forEach(System.out::println);

// Chaining consumers
Consumer<String> consumer1 = s -> System.out.println("First: " + s);
Consumer<String> consumer2 = s -> System.out.println("Second: " + s);
Consumer<String> combined = consumer1.andThen(consumer2);
```

#### Supplier<T>
Supplies a result without taking any arguments.
```java
Supplier<String> supplier = () -> "Hello World";
Supplier<Double> randomValue = Math::random;
Supplier<LocalDateTime> currentTime = LocalDateTime::now;
```

#### BiFunction<T, U, R>
Takes two arguments and returns a result.
```java
BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
BiFunction<String, String, String> concat = String::concat;
```

### Custom Functional Interface
```java
@FunctionalInterface
public interface Calculator {
    int calculate(int a, int b);
    
    // Default method allowed
    default int multiply(int a, int b) {
        return a * b;
    }
    
    // Static method allowed
    static int divide(int a, int b) {
        return a / b;
    }
}

// Usage
Calculator add = (a, b) -> a + b;
Calculator subtract = (a, b) -> a - b;
```

## Method References

Method references provide a way to refer to methods without invoking them. They're shorthand for lambda expressions.

### Types of Method References

#### Static Method Reference
```java
// Lambda
Function<String, Integer> parseInt1 = s -> Integer.parseInt(s);
// Method reference
Function<String, Integer> parseInt2 = Integer::parseInt;

List<String> numbers = Arrays.asList("1", "2", "3");
numbers.stream()
       .map(Integer::parseInt)
       .forEach(System.out::println);
```

#### Instance Method Reference
```java
String str = "Hello";
Supplier<String> upperCase1 = () -> str.toUpperCase();
Supplier<String> upperCase2 = str::toUpperCase;

List<String> words = Arrays.asList("apple", "banana", "cherry");
words.stream()
     .map(String::toUpperCase)
     .forEach(System.out::println);
```

#### Constructor Reference
```java
// Lambda
Supplier<List<String>> listSupplier1 = () -> new ArrayList<>();
// Constructor reference
Supplier<List<String>> listSupplier2 = ArrayList::new;

Function<String, StringBuilder> sbFunction = StringBuilder::new;
```

## Stream API

Streams provide a declarative approach to process collections of data. They support functional-style operations and can be processed sequentially or in parallel.

### Stream Creation
```java
// From collection
List<String> list = Arrays.asList("a", "b", "c");
Stream<String> stream1 = list.stream();

// From array
String[] array = {"a", "b", "c"};
Stream<String> stream2 = Arrays.stream(array);

// Using Stream.of()
Stream<String> stream3 = Stream.of("a", "b", "c");

// Infinite streams
Stream<Integer> infiniteStream = Stream.iterate(0, n -> n + 2);
Stream<Double> randomStream = Stream.generate(Math::random);

// Range streams
IntStream range = IntStream.range(1, 10);
IntStream rangeClosed = IntStream.rangeClosed(1, 10);
```

### Intermediate Operations

#### Filter
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
List<Integer> evenNumbers = numbers.stream()
                                  .filter(n -> n % 2 == 0)
                                  .collect(Collectors.toList());
```

#### Map
```java
List<String> words = Arrays.asList("hello", "world", "java");
List<String> upperCaseWords = words.stream()
                                  .map(String::toUpperCase)
                                  .collect(Collectors.toList());

List<Integer> lengths = words.stream()
                            .map(String::length)
                            .collect(Collectors.toList());
```

#### FlatMap
```java
List<List<String>> listOfLists = Arrays.asList(
    Arrays.asList("a", "b"),
    Arrays.asList("c", "d"),
    Arrays.asList("e", "f")
);

List<String> flatList = listOfLists.stream()
                                  .flatMap(List::stream)
                                  .collect(Collectors.toList());
```

#### Distinct, Sorted, Skip, Limit
```java
List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 4, 4, 5);
List<Integer> processed = numbers.stream()
                                .distinct()
                                .sorted()
                                .skip(2)
                                .limit(3)
                                .collect(Collectors.toList());
```

### Terminal Operations

#### ForEach
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
names.stream().forEach(System.out::println);
```

#### Collect
```java
List<String> words = Arrays.asList("apple", "banana", "cherry");

// To List
List<String> upperWords = words.stream()
                              .map(String::toUpperCase)
                              .collect(Collectors.toList());

// To Set
Set<String> wordSet = words.stream()
                          .collect(Collectors.toSet());

// To Map
Map<String, Integer> wordLengths = words.stream()
                                       .collect(Collectors.toMap(
                                           Function.identity(),
                                           String::length
                                       ));
```

#### Reduce
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

// Sum
Optional<Integer> sum1 = numbers.stream().reduce((a, b) -> a + b);
Optional<Integer> sum2 = numbers.stream().reduce(Integer::sum);
Integer sum3 = numbers.stream().reduce(0, Integer::sum);

// Max
Optional<Integer> max = numbers.stream().reduce(Integer::max);

// String concatenation
List<String> words = Arrays.asList("Hello", " ", "World");
String sentence = words.stream().reduce("", String::concat);
```

#### Match Operations
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

boolean allEven = numbers.stream().allMatch(n -> n % 2 == 0);
boolean anyEven = numbers.stream().anyMatch(n -> n % 2 == 0);
boolean noneNegative = numbers.stream().noneMatch(n -> n < 0);
```

#### Find Operations
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

Optional<String> first = names.stream().findFirst();
Optional<String> any = names.stream().findAny();
```

## Optional Class

Optional is a container object that may or may not contain a non-null value. It helps avoid NullPointerException.

### Creating Optional
```java
// Empty optional
Optional<String> empty = Optional.empty();

// Optional with value
Optional<String> optional1 = Optional.of("Hello");

// Optional that may contain null
String nullableString = getString(); // might return null
Optional<String> optional2 = Optional.ofNullable(nullableString);
```

### Using Optional
```java
Optional<String> optional = Optional.of("Hello World");

// Check if present
if (optional.isPresent()) {
    System.out.println(optional.get());
}

// Using ifPresent
optional.ifPresent(System.out::println);

// Using orElse
String value1 = optional.orElse("Default Value");

// Using orElseGet
String value2 = optional.orElseGet(() -> "Computed Default");

// Using orElseThrow
String value3 = optional.orElseThrow(() -> new RuntimeException("Value not present"));

// Chaining operations
Optional<String> result = optional
    .filter(s -> s.length() > 5)
    .map(String::toUpperCase)
    .map(s -> s + "!");
```

### Optional in Streams
```java
List<String> words = Arrays.asList("apple", null, "banana", null, "cherry");

List<String> nonNullWords = words.stream()
                                .filter(Objects::nonNull)
                                .collect(Collectors.toList());

// Or using Optional
List<String> processedWords = words.stream()
                                  .map(Optional::ofNullable)
                                  .filter(Optional::isPresent)
                                  .map(Optional::get)
                                  .collect(Collectors.toList());
```

## Default Methods

Default methods allow you to add new methods to interfaces without breaking existing implementations.

### Syntax
```java
public interface Vehicle {
    // Abstract method
    void start();
    
    // Default method
    default void stop() {
        System.out.println("Vehicle stopped");
    }
    
    default void honk() {
        System.out.println("Vehicle is honking");
    }
}

class Car implements Vehicle {
    @Override
    public void start() {
        System.out.println("Car started");
    }
    
    // Can override default method if needed
    @Override
    public void honk() {
        System.out.println("Car horn: Beep beep!");
    }
}
```

### Multiple Interface Inheritance
```java
interface A {
    default void method() {
        System.out.println("A's method");
    }
}

interface B {
    default void method() {
        System.out.println("B's method");
    }
}

class C implements A, B {
    // Must override to resolve conflict
    @Override
    public void method() {
        A.super.method(); // Call A's implementation
        B.super.method(); // Call B's implementation
    }
}
```

## Static Methods in Interfaces

Interfaces can now contain static methods that belong to the interface itself.

```java
public interface MathUtils {
    static int add(int a, int b) {
        return a + b;
    }
    
    static int multiply(int a, int b) {
        return a * b;
    }
    
    static double calculateCircleArea(double radius) {
        return Math.PI * radius * radius;
    }
}

// Usage
int sum = MathUtils.add(5, 3);
double area = MathUtils.calculateCircleArea(10);
```

## Date and Time API

Java 8 introduced a new Date and Time API that is immutable and thread-safe.

### Core Classes

#### LocalDate
```java
LocalDate today = LocalDate.now();
LocalDate specificDate = LocalDate.of(2023, Month.DECEMBER, 25);
LocalDate parsedDate = LocalDate.parse("2023-12-25");

// Operations
LocalDate tomorrow = today.plusDays(1);
LocalDate nextWeek = today.plusWeeks(1);
LocalDate nextMonth = today.plusMonths(1);
LocalDate nextYear = today.plusYears(1);

// Comparisons
boolean isBefore = today.isBefore(tomorrow);
boolean isAfter = today.isAfter(yesterday);
```

#### LocalTime
```java
LocalTime now = LocalTime.now();
LocalTime specificTime = LocalTime.of(14, 30, 0); // 2:30 PM
LocalTime parsedTime = LocalTime.parse("14:30:00");

// Operations
LocalTime later = now.plusHours(2);
LocalTime earlier = now.minusMinutes(30);
```

#### LocalDateTime
```java
LocalDateTime now = LocalDateTime.now();
LocalDateTime specific = LocalDateTime.of(2023, 12, 25, 14, 30);
LocalDateTime combined = LocalDate.now().atTime(LocalTime.now());

// Combining date and time
LocalDate date = LocalDate.of(2023, 12, 25);
LocalTime time = LocalTime.of(14, 30);
LocalDateTime dateTime = date.atTime(time);
```

#### ZonedDateTime
```java
ZonedDateTime nowInUTC = ZonedDateTime.now(ZoneId.of("UTC"));
ZonedDateTime nowInNY = ZonedDateTime.now(ZoneId.of("America/New_York"));

// Converting between time zones
ZonedDateTime utcTime = ZonedDateTime.now(ZoneId.of("UTC"));
ZonedDateTime nyTime = utcTime.withZoneSameInstant(ZoneId.of("America/New_York"));
```

#### Period and Duration
```java
// Period - for date-based amounts
LocalDate start = LocalDate.of(2023, 1, 1);
LocalDate end = LocalDate.of(2023, 12, 31);
Period period = Period.between(start, end);

// Duration - for time-based amounts
LocalTime startTime = LocalTime.of(9, 0);
LocalTime endTime = LocalTime.of(17, 30);
Duration duration = Duration.between(startTime, endTime);

// Using Duration
Duration oneHour = Duration.ofHours(1);
Duration thirtyMinutes = Duration.ofMinutes(30);
```

#### DateTimeFormatter
```java
LocalDateTime now = LocalDateTime.now();

// Predefined formatters
String formatted1 = now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
String formatted2 = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

// Custom patterns
DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy 'at' HH:mm");
String customFormatted = now.format(customFormatter);

// Parsing
LocalDateTime parsed = LocalDateTime.parse("25/12/2023 at 14:30", customFormatter);
```

## Collectors

Collectors provide various ways to collect stream elements into different data structures.

### Basic Collectors
```java
List<String> words = Arrays.asList("apple", "banana", "cherry", "date");

// To List
List<String> list = words.stream().collect(Collectors.toList());

// To Set
Set<String> set = words.stream().collect(Collectors.toSet());

// To specific collection type
LinkedList<String> linkedList = words.stream()
    .collect(Collectors.toCollection(LinkedList::new));
```

### Grouping
```java
List<String> words = Arrays.asList("apple", "banana", "cherry", "apricot", "blueberry");

// Group by first letter
Map<Character, List<String>> groupedByFirstLetter = words.stream()
    .collect(Collectors.groupingBy(s -> s.charAt(0)));

// Group by length
Map<Integer, List<String>> groupedByLength = words.stream()
    .collect(Collectors.groupingBy(String::length));

// Count by group
Map<Integer, Long> countByLength = words.stream()
    .collect(Collectors.groupingBy(String::length, Collectors.counting()));
```

### Partitioning
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

// Partition by even/odd
Map<Boolean, List<Integer>> partitioned = numbers.stream()
    .collect(Collectors.partitioningBy(n -> n % 2 == 0));
```

### Joining
```java
List<String> words = Arrays.asList("Hello", "World", "Java", "8");

// Simple joining
String joined1 = words.stream().collect(Collectors.joining());

// With delimiter
String joined2 = words.stream().collect(Collectors.joining(", "));

// With delimiter, prefix, and suffix
String joined3 = words.stream()
    .collect(Collectors.joining(", ", "[", "]"));
```

### Statistics Collectors
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

// Numeric statistics
IntSummaryStatistics stats = numbers.stream()
    .collect(Collectors.summarizingInt(Integer::intValue));

System.out.println("Count: " + stats.getCount());
System.out.println("Sum: " + stats.getSum());
System.out.println("Average: " + stats.getAverage());
System.out.println("Min: " + stats.getMin());
System.out.println("Max: " + stats.getMax());
```

### Custom Collectors
```java
// Creating a custom collector that collects to an immutable list
Collector<String, ?, List<String>> toImmutableList = Collector.of(
    ArrayList::new,           // supplier
    List::add,               // accumulator
    (list1, list2) -> {      // combiner
        list1.addAll(list2);
        return list1;
    },
    Collections::unmodifiableList // finisher
);
```

## Parallel Streams

Parallel streams can improve performance by utilizing multiple threads for stream operations.

### Creating Parallel Streams
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

// From collection
Stream<Integer> parallelStream1 = numbers.parallelStream();

// Convert sequential to parallel
Stream<Integer> parallelStream2 = numbers.stream().parallel();

// Check if parallel
boolean isParallel = parallelStream1.isParallel();
```

### When to Use Parallel Streams
```java
List<Integer> largeList = IntStream.range(1, 1000000)
                                  .boxed()
                                  .collect(Collectors.toList());

// CPU-intensive operation - good candidate for parallel processing
long sum = largeList.parallelStream()
                   .mapToLong(i -> i * i)
                   .sum();

// Simple operations - might not benefit from parallel processing
List<String> words = Arrays.asList("hello", "world", "java");
words.parallelStream().forEach(System.out::println); // Order not guaranteed
```

### Considerations
- Overhead of thread management
- Order of elements might not be preserved
- Shared mutable state can cause issues
- Not always faster than sequential streams

## CompletableFuture

CompletableFuture provides a way to write asynchronous, non-blocking code.

### Creating CompletableFuture
```java
// Completed future
CompletableFuture<String> completedFuture = CompletableFuture.completedFuture("Hello");

// Async computation
CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
    // Simulate long-running task
    try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
    return "Hello World";
});

// Async runnable (no return value)
CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
    System.out.println("Running asynchronously");
});
```

### Chaining Operations
```java
CompletableFuture<String> future = CompletableFuture
    .supplyAsync(() -> "Hello")
    .thenApply(s -> s + " World")
    .thenApply(String::toUpperCase)
    .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + "!"));

// Get result (blocking)
String result = future.get();
```

### Combining Futures
```java
CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "World");

// Combine two futures
CompletableFuture<String> combined = future1.thenCombine(future2, (s1, s2) -> s1 + " " + s2);

// Wait for all to complete
CompletableFuture<Void> allOf = CompletableFuture.allOf(future1, future2);

// Wait for any to complete
CompletableFuture<Object> anyOf = CompletableFuture.anyOf(future1, future2);
```

### Exception Handling
```java
CompletableFuture<String> future = CompletableFuture
    .supplyAsync(() -> {
        if (Math.random() > 0.5) {
            throw new RuntimeException("Something went wrong");
        }
        return "Success";
    })
    .exceptionally(throwable -> "Default Value")
    .thenApply(String::toUpperCase);

// Or using handle
CompletableFuture<String> future2 = CompletableFuture
    .supplyAsync(() -> "Hello")
    .handle((result, throwable) -> {
        if (throwable != null) {
            return "Error occurred";
        }
        return result.toUpperCase();
    });
```

## Best Practices and Tips

### Lambda Expression Best Practices
1. Keep lambda expressions short and readable
2. Use method references when possible
3. Avoid modifying variables from enclosing scope
4. Be careful with exception handling in lambdas

### Stream API Best Practices
1. Don't reuse streams
2. Use parallel streams judiciously
3. Avoid side effects in stream operations
4. Consider performance implications
5. Use appropriate collectors

### Optional Best Practices
1. Never call `get()` without checking `isPresent()`
2. Use `orElse()` for simple default values
3. Use `orElseGet()` for computed default values
4. Don't use Optional for fields or parameters
5. Use Optional as return type for methods that might not return a value

### General Java 8 Tips
1. Favor composition over inheritance with functional interfaces
2. Use the new Date/Time API instead of old Date and Calendar
3. Take advantage of default methods for interface evolution
4. Use CompletableFuture for asynchronous programming
5. Consider using parallel streams for CPU-intensive operations on large datasets