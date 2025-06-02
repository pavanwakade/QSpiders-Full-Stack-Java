



//! 1.	Reverse a String
// Write a function that takes a string as input and returns the string in reverse order.
// Input: “hello”
//? Expected Output: “olleh”

let q1String = "hello";
let reversestring = str => str.split("").reverse().join("");
// console.log(reversestring(q1String));


//! 2.	Check for Palindrome
// Check if a given string is a palindrome (reads the same forward and backward).
// Input: “madam”
//? Expected Output: true
let q2palindrom = "madam";
let q2Array = q2palindrom.split("");

// let checkpalindrom = (arr) => {

//     for (let i = 0; i < arr.length; i++) {
//         if (arr[i] !== (arr[arr.length - 1 - i])) {
//             return false;
//         }
//     }
//     return true;
// }

let checkPlaindrome=q2palindrom.split().reverse().join("")===q2palindrom;

// console.log(checkPlaindrome); //true

//! 3.	Count Vowels in a String
//Write a function to count the number of vowels in a string.
let Input= "javascript";
//? Expected Output: 3

let checkovels=(Inp)=>{
    // let e=Inp.split("");
    let count=0;
    Inp.split("").forEach(element => {
    if (element=='a' || element=='e' ||element=='i' ||element=='o' ||element=='u' ||element=='A' ||element=='E' ||element=='I' ||element=='O' ||element=='U') {
        count+=1;
    }
})
return count;
}
// console.log(checkovels(Input));



//! 4.	Find Factorial of a Number
// Find the factorial of a given number using recursion.
// Input: 5
// Expected Output: 120


let q4=5;
let element=1;
for (let i = 1; i <= q4; i++) {
     element = element* i;
     console.log(element);    
}
console.log(element);



// 5.	Find Largest Number in an Array

// Write a function to return the largest number in an array.
// Input: [4, 7, 1, 9, 3]
// Expected Output: 9



// 6.	Find Smallest Number in an Array

// Return the smallest number from a given array.
// Input: [4, 7, 1, 9, 3]
// Expected Output: 1



// 7.	Check If Number is Prime

// Check if a given number is a prime number.
// Input: 11
// Expected Output: true



// 8.	Sum of All Elements in Array

// Write a function to calculate the total sum of all elements in an array.
// Input: [5, 10, 15]
// Expected Output: 30



// 9.	Remove Duplicates from Array

// Remove all duplicate values from a given array and return a new array.
// Input: [1, 2, 2, 3, 4, 4]
// Expected Output: [1, 2, 3, 4]



// 10.	Generate Fibonacci Sequence

// Generate the Fibonacci sequence up to n terms.
// Input: 6
// Expected Output: [0, 1, 1, 2, 3, 5]



// 11.	Check If Two Strings Are Anagrams

// Check whether two given strings are anagrams of each other.
// Input: “listen”, “silent”
// Expected Output: true



// 12.	Find Second Largest Number

// Return the second largest number in an array.
// Input: [10, 5, 8, 20, 15]
// Expected Output: 15



// 13.	Swap Two Variables Without Temp

// Swap the values of two variables without using a temporary variable.
// Input: a = 10, b = 20
// Expected Output: a = 20, b = 10



// 14.	Capitalize First Letter of Each Word

// Capitalize the first letter of each word in a sentence.
// Input: “hello world”
// Expected Output: “Hello World”



// 15.	Count Number of Words in a String

// Count the number of words in a given sentence.
// Input: “JavaScript is fun”
// Expected Output: 3



// 16.	Reverse Each Word in a Sentence

// Reverse each word in a sentence but maintain their positions.
// Input: “hello world”
// Expected Output: “olleh dlrow”



// 17.	Check If Number is Even or Odd

// Check if a number is even or odd.
// Input: 9
// Expected Output: “Odd”



// 18.	Check If Array is Sorted

// Determine if a given array is sorted in ascending order.
// Input: [1, 2, 3, 4, 5]
// Expected Output: true



// 19.	Find GCD of Two Numbers

// Calculate the Greatest Common Divisor (GCD) of two integers.
// Input: 12, 18
// Expected Output: 6



// 20.	Find LCM of Two Numbers

// Calculate the Least Common Multiple (LCM) of two numbers.
// Input: 4, 6
// Expected Output: 12



// 21.	Count Occurrence of a Character

// Count how many times a specific character appears in a string.
// Input: “banana”, ‘a’
// Expected Output: 3



// 22.	Merge Two Arrays

// Write a function that merges two arrays into one.
// Input: [1, 2], [3, 4]
// Expected Output: [1, 2, 3, 4]



// 23.	Flatten a Nested Array

// Flatten a deeply nested array into a single-level array.
// Input: [1, [2, [3, [4]]]]
// Expected Output: [1, 2, 3, 4]



// 24.	Find Missing Number

// Find the missing number in an array containing numbers from 1 to n.
// Input: [1, 2, 4, 5]
// Expected Output: 3



// 25.	Remove Falsy Values

// Remove all falsy values from an array (false, null, 0, “”, undefined, NaN).
// Input: [0, 1, false, 2, ‘’, 3]
// Expected Output: [1, 2, 3]



// 26.	Convert String to Number

// Convert a string value into a number.
// Input: “123”
// Expected Output: 123



// 27.	Convert Number to String

// Convert a number into a string.
// Input: 456
// Expected Output: “456”



// 28.	Get Unique Values from Array

// Return only the unique values from an array.
// Input: [1, 2, 2, 3, 4, 4]
// Expected Output: [1, 2, 3, 4]



// 29.	Frequency of Elements in Array

// Return an object showing how many times each element occurs.
// Input: [1, 2, 2, 3]
// Expected Output: {1: 1, 2: 2, 3: 1}



// 30.	Intersection of Two Arrays

// Return the common elements of two arrays.
// Input: [1, 2, 3], [2, 3, 4]
// Expected Output: [2, 3]



// 31.	Union of Two Arrays

// Return a new array that combines elements from both arrays without duplicates.
// Input: [1, 2], [2, 3]
// Expected Output: [1, 2, 3]



// 32.	Sum of Digits in a Number

// Calculate the sum of digits in a number.
// Input: 1234
// Expected Output: 10



// 33.	Reverse a Number

// Reverse the digits of a number.
// Input: 1234
// Expected Output: 4321



// 34.	Count Even and Odd Digits

// Count the number of even and odd digits in a number.
// Input: 123456
// Expected Output: Even: 3, Odd: 3



// 35.	Celsius to Fahrenheit Conversion

// Convert a temperature from Celsius to Fahrenheit.
// Input: 0
// Expected Output: 32



// 36.	Check If String Is Numeric

// Check if a given string can be converted to a number.
// Input: “123”
// Expected Output: true



// 37.	First Non-Repeating Character

// Find the first non-repeating character in a string.
// Input: “aabbcdde”
// Expected Output: “c”



// 38.	Find Duplicates in Array

// Identify all elements that appear more than once.
// Input: [1, 2, 2, 3, 3, 4]
// Expected Output: [2, 3]



// 39.	Generate Random Number Between Range

// Generate a random number between two values (inclusive).
// Input: 1, 10
// Expected Output: Random number between 1 and 10



// 40.	Check If Year Is Leap Year

// Determine if a year is a leap year.
// Input: 2024
// Expected Output: true



// 41.	Count Uppercase and Lowercase Letters

// Count the number of uppercase and lowercase characters in a string.
// Input: “HelloWorld”
// Expected Output: Uppercase: 2, Lowercase: 8



// 42.	Find Average of Array Elements

// Calculate the average of all numbers in an array.
// Input: [10, 20, 30, 40]
// Expected Output: 25



// 43.	Check If All Array Elements Are Unique

// Verify if all elements in an array are unique.
// Input: [1, 2, 3, 4]
// Expected Output: true



// 44.	Print Star Pattern

// Print the following pattern for n = 3:

// *
// **



// 45.	Find Median of Array

// Return the median value of an array.
// Input: [5, 3, 1, 4, 2]
// Expected Output: 3



// 46.	Find Mode of Array

// Find the element(s) that appear most frequently.
// Input: [1, 2, 2, 3, 3, 3, 4]
// Expected Output: 3



// 47.	Sum of Even Numbers in Array

// Calculate the sum of all even numbers in an array.
// Input: [1, 2, 3, 4]
// Expected Output: 6



// 48.	Sum of Odd Numbers in Array

// Calculate the sum of all odd numbers in an array.
// Input: [1, 2, 3, 4]
// Expected Output: 4



// 49.	Convert Time to 24-Hour Format

// Convert time from 12-hour format to 24-hour format.
// Input: “02:30 PM”
// Expected Output: “14:30”



// 50.	Check if Array Contains Specific Value