## 1. Basic Methods

### `length()`
- **Description:** Returns the number of characters in the string.
- **Example:**
```java
String str = "Hello World";
System.out.println(str.length()); // 11
```

### `charAt(int index)`
- **Description:** Returns the character at the specified index.
- **Example:**
```java
String str = "Java";
System.out.println(str.charAt(2)); // 'v'
```

### `toCharArray()`
- **Description:** Converts string into an array of characters.
- **Example:**
```java
String str = "Hi";
char[] chars = str.toCharArray();
System.out.println(chars[0]); // 'H'
```

### `equals(String another)`
- **Description:** Compares two strings for content equality.
- **Example:**
```java
System.out.println("abc".equals("abc")); // true
```

### `equalsIgnoreCase(String another)`
- **Description:** Compares two strings, ignoring case.
- **Example:**
```java
System.out.println("ABC".equalsIgnoreCase("abc")); // true
```

---

## 2. Substring and Searching

### `substring(int beginIndex)`
- **Description:** Returns substring from specified index to end.
- **Example:**
```java
String str = "Hello World";
System.out.println(str.substring(6)); // World
```

### `substring(int beginIndex, int endIndex)`
- **Description:** Returns substring from `beginIndex` to `endIndex-1`.
- **Example:**
```java
System.out.println("Java Programming".substring(5, 16)); // Programming
```

### `indexOf(String str)`
- **Description:** Returns index of first occurrence of the substring.
- **Example:**
```java
System.out.println("banana".indexOf("na")); // 2
```

### `indexOf(String str, int fromIndex)`
- **Description:** Finds index starting from given position.
- **Example:**
```java
System.out.println("banana".indexOf("na", 3)); // 4
```

### `lastIndexOf(String str)`
- **Description:** Returns index of last occurrence.
- **Example:**
```java
System.out.println("banana".lastIndexOf("na")); // 4
```

### `contains(CharSequence s)`
- **Description:** Checks if substring exists.
- **Example:**
```java
System.out.println("apple pie".contains("pie")); // true
```

---

## 3. String Comparison

### `compareTo(String another)`
- **Description:** Lexicographically compares two strings.
- **Example:**
```java
System.out.println("a".compareTo("b")); // -1
```

### `compareToIgnoreCase(String another)`
- **Description:** Case-insensitive comparison.
- **Example:**
```java
System.out.println("abc".compareToIgnoreCase("ABC")); // 0
```

### `matches(String regex)`
- **Description:** Tests if string matches the given regex.
- **Example:**
```java
System.out.println("abc123".matches("[a-z]+[0-9]+")); // true
```

---

## 4. Modification and Replacement

### `replace(char oldChar, char newChar)`
- **Description:** Replaces all occurrences of a character.
- **Example:**
```java
System.out.println("banana".replace('a', 'o')); // bonono
```

### `replace(CharSequence target, CharSequence replacement)`
- **Description:** Replaces sequence with another.
- **Example:**
```java
System.out.println("Hello World".replace("World", "Java")); // Hello Java
```

### `replaceAll(String regex, String replacement)`
- **Description:** Replaces all regex-matching substrings.
- **Example:**
```java
System.out.println("1-2-3".replaceAll("-", ":")); // 1:2:3
```

### `replaceFirst(String regex, String replacement)`
- **Description:** Replaces first substring matching regex.
- **Example:**
```java
System.out.println("abcabc".replaceFirst("a", "z")); // zbcabc
```

### `toLowerCase()` / `toUpperCase()`
- **Description:** Converts to lower/uppercase.
- **Example:**
```java
System.out.println("Java".toUpperCase()); // JAVA
```

### `trim()`
- **Description:** Removes leading and trailing whitespace.
- **Example:**
```java
System.out.println(" Hello ".trim()); // "Hello"
```

### `strip()` *(Java 11+)*
- **Description:** Unicode-aware version of `trim()`.
- **Example:**
```java
System.out.println("  Hello  ".strip()); // "Hello"
```

---

## 5. Splitting and Joining

### `split(String regex)`
- **Description:** Splits string using given delimiter.
- **Example:**
```java
String[] parts = "a,b,c".split(",");
System.out.println(parts[1]); // b
```

### `split(String regex, int limit)`
- **Description:** Same as above, but limits the number of results.
- **Example:**
```java
String[] parts = "a,b,c".split(",", 2);
System.out.println(parts[1]); // b,c
```

### `join(CharSequence delimiter, CharSequence... elements)` *(Java 8+)*
- **Description:** Joins strings using delimiter.
- **Example:**
```java
String joined = String.join("-", "2025", "04", "08");
System.out.println(joined); // 2025-04-08
```

---

## 6. Testing

### `startsWith(String prefix)` / `endsWith(String suffix)`
- **Description:** Checks string prefix/suffix.
- **Example:**
```java
System.out.println("filename.txt".endsWith(".txt")); // true
```

### `isEmpty()`
- **Description:** Checks if string is empty.
- **Example:**
```java
System.out.println("".isEmpty()); // true
```

### `isBlank()` *(Java 11+)*
- **Description:** Checks if string is empty or whitespace only.
- **Example:**
```java
System.out.println("  ".isBlank()); // true
```

---

## 7. Formatting and Conversion

### `format(String format, Object... args)`
- **Description:** Returns formatted string.
- **Example:**
```java
String name = "Pavan";
System.out.println(String.format("Hello, %s", name)); // Hello, Pavan
```

### `valueOf(type)`
- **Description:** Converts various data types to string.
- **Example:**
```java
System.out.println(String.valueOf(123)); // "123"
```

### `intern()`
- **Description:** Returns canonical representation from string pool.
- **Example:**
```java
String s1 = new String("Java").intern();
String s2 = "Java";
System.out.println(s1 == s2); // true
```

### `getBytes()`
- **Description:** Returns byte array of string.
- **Example:**
```java
byte[] bytes = "Hello".getBytes();
System.out.println(bytes[0]); // 72 (ASCII of 'H')
```

### `hashCode()`
- **Description:** Returns hash code of string.
- **Example:**
```java
System.out.println("Hello".hashCode());
```

### `codePointAt(int index)`
- **Description:** Returns Unicode code point at given index.
- **Example:**
```java
System.out.println("A".codePointAt(0)); // 65
```

---
