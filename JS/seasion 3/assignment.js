



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

let checkPlaindrome = q2palindrom.split().reverse().join("") === q2palindrom;

// console.log(checkPlaindrome); //true

//! 3.	Count Vowels in a String
//Write a function to count the number of vowels in a string.
let Input = "javascript";
//? Expected Output: 3

let checkovels = (Inp) => {
    // let e=Inp.split("");
    let count = 0;
    Inp.split("").forEach(element => {
        if (element == 'a' || element == 'e' || element == 'i' || element == 'o' || element == 'u' || element == 'A' || element == 'E' || element == 'I' || element == 'O' || element == 'U') {
            count += 1;
        }
    })
    return count;
}
// console.log(checkovels(Input));



//! 4.	Find Factorial of a Number
// Find the factorial of a given number using recursion.
// Input: 5
// Expected Output: 120


let q4 = 5;
if (q4 > 0) {
    let element = 1;
    for (let i = 1; i <= q4; i++) {
        element = element * i;
        //  console.log(element);    
    }
    // console.log(element);
}


//! 5.	Find Largest Number in an Array

// Write a function to return the largest number in an array.
// Input: [4, 7, 1, 9, 3]
//? Expected Output: 9

let arr = [4, 7, 1, 9, 3];
arr.sort((a, b) => a - b);
let largestnum = arr[arr.length - 1];
// console.log(largestnum);

//! 6.	Find Smallest Number in an Array
// Return the smallest number from a given array.
// Input: [4, 7, 1, 9, 3]
//? Expected Output: 1

let arr1 = [4, 7, 1, 9, 3];
arr1.sort((a, b) => b - a);
let smallestinArray = arr[0];
// console.log(smallestinArray);


//! 7.	Check If Number is Prime
// Check if a given number is a prime number.
// Input: 11
// Expected Output: true


let num = 11;
let cnt = 0;
for (let i = 2; i <= num; i++) {
    if (num % i === 0) {
        cnt += 1;
    }
}
// cnt > 1 ? console.log("not prime") : console.log("prime");


//! 8.	Sum of All Elements in Array

// Write a function to calculate the total sum of all elements in an array.
// Input: [5, 10, 15]
// Expected Output: 30

let num1 = [50, 100, 150];
let sum = 0;
for (let i = 0; i < num1.length; i++) {
    sum += num1[i];
}
// console.log(sum);


// 9.	Remove Duplicates from Array

// !Remove all duplicate values from a given array and return a new array.
// Input: [1, 2, 2, 3, 4, 4]
// Expected Output: [1, 2, 3, 4]

let newArray = [1, 2, 2, 3, 4, 4];
let newUniqueArray = [];

newArray.forEach(element => {
    if (!newUniqueArray.includes(element)) {
        newUniqueArray.push(element)
    }
});
// console.log(newUniqueArray);



//! 10.	Generate Fibonacci Sequence

// Generate the Fibonacci sequence up to n terms.
// let numr: 6
// Expected Output: [0, 1, 1, 2, 3, 5]

let q10num = 6;
let febnum = [];
for (let i = 0; i < q10num; i++) {
    i === 0 ? febnum.push(0) : i === 1 ? febnum.push(1) : febnum.push(febnum[i - 1] + febnum[i - 2])
}
// console.log(febnum);


//! 11.	Check If Two Strings Are Anagrams

// Check whether two given strings are anagrams of each other.
// Input: “listen”, “silent”
// Expected Output: true

let string1 = "listen";
let string2 = "silent";
let sorted1 = string1.split("").sort().join("");
let sorted2 = string2.split("").sort().join("");
// console.log(sorted1);
// console.log(sorted2);
// sorted1===sorted2?console.log("anagram"):console.log("NOt Anagram");




//! 12.	Find Second Largest Number

// Return the second largest number in an array.
// Input: [10, 5, 8, 20, 15]
// Expected Output: 15

let q12 = [10, 5, 8, 20, 15];
q12.sort((a, b) => a - b)
let secondLargest = q12[q12.length - 2];
// console.log(secondLargest);



//! 13.	Swap Two Variables Without Temp

// Swap the values of two variables without using a temporary variable.
// Input: a = 10, b = 20
// Expected Output: a = 20, b = 10

let a = 10;
let b = 20;
// console.log("a : " + a);
// console.log("b : " + b);

a = a + b;
b = a - b;
a = a - b;

// console.log("after swap");

// console.log("a : " + a);
// console.log("b : " + b);





//! 14.	Capitalize First Letter of Each Word

// Capitalize the first letter of each word in a sentence.
// Input: “hello world”
// Expected Output: “Hello World”

function capitalizeWords(sentence) {
    return sentence.split(" ").map(word => word.charAt(0).toUpperCase() + word.slice(1)).join(" ");
}

let input = "hello world";
let output = capitalizeWords(input);
// console.log(output);  // Output: "Hello World"



//! 15.	Count Number of Words in a String
// Count the number of words in a given sentence.
// Input: “JavaScript is fun”
// Expected Output: 3

let countsentecnce = "JavaScript is fun ";
let sentencetoarraycount = countsentecnce.trim().split(' ').length;
// console.log(sentencetoarraycount);


//! 16.	Reverse Each Word in a Sentence
// Reverse each word in a sentence but maintain their positions.
// Input: “hello world”
// Expected Output: “olleh dlrow”

let striing = "hello world";
let rev = striing.split(" ").map((el) => el.split("").reverse().join("")).join(" ");
// console.log(rev);



//! 17.	Check If Number is Even or Odd

// Check if a number is even or odd.
// Input: 9
// Expected Output: “Odd”
let checkenodd = 9;
// let checkEvenorOdd = checkenodd % 2 === 0 ? console.log("Even") : console.log("odd");




// !18.	Check If Array is Sorted

// Determine if a given array is sorted in ascending order.
// Input: [1, 2, 3, 4, 5]
// Expected Output: true

let SortOrNot = [1, 2, 6, 3, 4, 5];

let checkAccendingorNot = (sss) => {
    for (let i = 0; i < sss.length - 1; i++) {
        if (sss[i] > sss[i + 1]) {
            // console.log(sss[i] + "   " + sss[i + 1]);
            return false;
        }
    }
    return true;
}

// console.log(checkAccendingorNot(SortOrNot));



//! 19.	Find GCD of Two Numbers

// Calculate the Greatest Common Divisor (GCD) of two integers.
// Input: 12, 18
// Expected Output: 6

let checkgcd = (a, b) => {
    let gcd;
    for (let i = 0; i <= Math.min(a, b); i++) {
        if (a % i === 0 && b % i === 0) {
            gcd = i;
        }
    }
    console.log(gcd);
}
// checkgcd(12, 18)


//! 20.	Find LCM of Two Numbers

// Calculate the Least Common Multiple (LCM) of two numbers.
// Input: 4, 6
// Expected Output: 12

let checklcm = (a, b) => {
    let gcd = 0;
    for (let i = 0; i <= Math.min(a, b); i++) {
        if (a % i === 0 && b % i === 0) {
            gcd = i;
        }
    }
    // console.log((a * b) / gcd);
}
checklcm(12, 18)

//! 21.	Count Occurrence of a Character
// Count how many times a specific character appears in a string.
// Input: “banana”, ‘a’
// Expected Output: 3
let inn = (arr, ch) => {
    let cn = 0;
    arr.forEach(element => {
        if (element === ch) {
            cn++;
            // console.log(element);
        }
    });
    return cn;
}
let m = "banana".split("");
// console.log(inn(m, 'a'));


// !22.	Merge Two Arrays

// Write a function that merges two arrays into one.
// Input: [1, 2], [3, 4]
// Expected Output: [1, 2, 3, 4]

let mergeArrays = (arr1, arr2) => {
    return arr1.concat(arr2);
};

// console.log(mergeArrays([1, 2], [3, 4])); // Output: [1, 2, 3, 4]




//! 23.	Flatten a Nested Array
// Flatten a deeply nested array into a single-level array.
// Input: [1, [2, [3, [4]]]]
// Expected Output: [1, 2, 3, 4]



//! 24.	Find Missing Number
// Find the missing number in an array containing numbers from 1 to n.
// Input: [1, 2, 4, 5]
// Expected Output: 3

let z = [1, 2, 4, 5];

for (let i = 0; i < z.length; i++) {

    if (z[i + 1] !== z[i] + 1) {
        // console.log("first occurance: "+z[i] + 1);
        break;
    }
}




// 25.	Remove Falsy Values

// Remove all falsy values from an array (false, null, 0, “”, undefined, NaN).
// Input: [0, 1, false, 2, ‘’, 3]
// Expected Output: [1, 2, 3]

let q25 = [0, 1, false, 2, '', 3];

let removeFalsyValues = (arr) => {
    let result = [];
    for (let i = 0; i < arr.length; i++) {
        let value = arr[i];
        if (value !== false && value !== 0 && value !== '' && value !== null && value !== undefined && !Number.isNaN(value)) {
            result.push(value);
        }
    }
    return result;
};

// console.log(removeFalsyValues(q25)); // Output: [1, 2, 3]







//! 26.	Convert String to Number
// Convert a string value into a number.
// Input: “123”
// Expected Output: 123

let ii = parseInt("123");
// console.log(typeof ii);
// console.log(ii);





//! 27.	Convert Number to String

// Convert a number into a string.
// Input: 456
// Expected Output: “456”

let aaa = 123;
let bbb = aaa.toString();
// console.log(bbb);
// console.log(typeof bbb);





//! 28.	Get Unique Values from Array

// Return only the unique values from an array.
const inputArray = [1, 2, 2, 3, 4, 4];
let uniqueValues = [...new Set(inputArray)];

// console.log(uniqueValues);



// !29.	Frequency of Elements in Array

// Return an object showing how many times each element occurs.
// Input: [1, 2, 2, 3]
// Expected Output: {1: 1, 2: 2, 3: 1}
const ttt = [1, 2, 2, 3];
let frequency = {};

for (let i = 0; i < ttt.length; i++) {
    let item = ttt[i];
    if (frequency[item]) {
        frequency[item] += 1;
    } else {
        frequency[item] = 1;
    }
}

// console.log(frequency); // Output: {1: 1, 2: 2, 3: 1}



//! 30.	Intersection of Two Arrays

// Return the common elements of two arrays.
// Input: [1, 2, 3], [2, 3, 4]
// Expected Output: [2, 3]

let q30=[1, 2, 3];  let q301=[2, 3, 4];

let nnnn=[];
for (let i = 0; i < q30.length; i++) {
    const element = q30[i];

    if (q301.includes(element)) {

        nnnn.push(element);
    }
}
// console.log(nnnn);


//! 31.	Union of Two Arrays

// Return a new array that combines elements from both arrays without duplicates.
// Input: [1, 2], [2, 3]
// Expected Output: [1, 2, 3]

const inputArray1 = [1, 2];
const inputArray2 = [2,3];

const ot=inputArray1.concat(inputArray2);
let uniqueValues2 = [...new Set(ot)];

// console.log(uniqueValues2);




// !32.	Sum of Digits in a Number

// Calculate the sum of digits in a number.
// Input: 1234
// Expected Output: 10

let num3 = 1234;
let sum1 = 0;

while (num3 > 0) {
    sum1 += num3 % 10;            
    num3 = Math.floor(num3 / 10); 
}
// console.log(sum1); 





// !33.	Reverse a Number
// Reverse the digits of a number.
// Input: 1234
// Expected Output: 4321
let nummm = 1234;
let Strrr = nummm.toString().split("").reverse().join("");
// console.log(Strrr);



//! 34.	Count Even and Odd Digits
// Count the number of even and odd digits in a number.
// Input: 123456
// Expected Output: Even: 3, Odd: 3
let num7 = 123456;
let evenCount = 0;
let oddCount = 0;

while (num7 > 0) {
    let digit = num7 % 10;
    
    if (digit % 2 === 0) {
        evenCount++;
    } else {
        oddCount++;
    }

    num7 = Math.floor(num7 / 10);
}

// console.log(`Even: ${evenCount}, Odd: ${oddCount}`);



//? 35.	Celsius to Fahrenheit Conversion

// Convert a temperature from Celsius to Fahrenheit.
// Input: 0
// Expected Output: 32



//! 36.	Check If String Is Numeric

// Check if a given string can be converted to a number.
let t="fgh";
// Expected Output: true

// try { parseFloat(t)
//     console.log("jj");
    
// } catch (error) {
    
// }


//! 37.	First Non-Repeating Character

// Find the first non-repeating character in a string.
// Input: “aabbcdde”
// Expected Output: “c”

function firstNonRepeatingChar(str) {
  const charCount = {};

  // Step 1: Count frequency of each character
  for (let char of str) {
    charCount[char] = (charCount[char] || 0) + 1;
  }

  // Step 2: Find the first character with count = 1
  for (let char of str) {
    if (charCount[char] === 1) {
      return char;
    }
  }

  return null; // if no non-repeating character found
}

// Test
const input4 = "aabbcdde";
// console.log(firstNonRepeatingChar(input4)); // Output: "c"





//! 38.	Find Duplicates in Array

// Identify all elements that appear more than once.
// Input: [1, 2, 2, 3, 3, 4]
// Expected Output: [2, 3]

function findDuplicates(arr) {
  const countMap = {};
  const duplicates = new Set();

  for (let num of arr) {
    if (countMap[num]) {
      duplicates.add(num);
    } else {
      countMap[num] = 1;
    }
  }

  return Array.from(duplicates);
}

// Test
const input9 = [1, 2, 2, 3, 3, 4];
// console.log(findDuplicates(input9)); // Output: [2, 3]

 



//! 39.	Generate Random Number Between Range

// Generate a random number between two values (inclusive).
// Input: 1, 10
// Expected Output: Random number between 1 and 10
function getRandomNumber(min, max) {
  return Math.floor(Math.random() * (max - min + 1)) + min;
}

// Test
const random = getRandomNumber(1, 10);
// console.log(random);



//! 40.	Check If Year Is Leap Year

// Determine if a year is a leap year.
// Input: 2024
// Expected Output: true
function isLeapYear(year) {
  return (year % 4 === 0 && year % 100 !== 0) || (year % 400 === 0);
}

// Test
const input99 = 2024;
// console.log(isLeapYear(input99));  // Output: true



//! 41.	Count Uppercase and Lowercase Letters
// Count the number of uppercase and lowercase characters in a string.
// Input: “HelloWorld”
// Expected Output: Uppercase: 2, Lowercase: 8
function countCase(str) {
  let upper = 0;
  let lower = 0;

  for (let char of str) {
    if (char >= 'A' && char <= 'Z') {
      upper++;
    } else if (char >= 'a' && char <= 'z') {
      lower++;
    }
  }

  return { uppercase: upper, lowercase: lower };
}

// Test
const result = countCase("HelloWorld");
// console.log(`Uppercase: ${result.uppercase}, Lowercase: ${result.lowercase}`);
// Output: Uppercase: 2, Lowercase: 8




//! 42.	Find Average of Array Elements
// Calculate the average of all numbers in an array.
// Input: [10, 20, 30, 40]
// Expected Output: 25
function findAverage(arr) {

  const sum = arr.reduce((total, num) => total + num, 0);
  return sum / arr.length;
}

// Test
const input88 = [10, 20, 30, 40];
// console.log("Average:", findAverage(input88)); // Output: 25




// ! 43.	Check If All Array Elements Are Unique

// Verify if all elements in an array are unique.
// Input: [1, 2, 3, 4]
// Expected Output: true

function areElementsUnique(arr) {
  const uniqueSet = new Set(arr);
  return uniqueSet.size === arr.length;
}

// Test
const input44 = [1, 2, 3, 4];
// console.log(areElementsUnique(input44));  // Output: true





//! 44.	Print Star Pattern

// Print the following pattern for n = 3:

// *
// **


function printStarPattern(n) {
  for (let i = 1; i <= n; i++) {
    let line = '';
    for (let j = 1; j <= i; j++) {
      line += '*';
    }
    console.log(line);
  }
}

// Test
// printStarPattern(3);




// 45.	Find Median of Array

// Return the median value of an array.
// Input: [5, 3, 1, 4, 2]
// Expected Output: 3



// 46.	Find Mode of Array

// Find the element(s) that appear most frequently.
// Input: [1, 2, 2, 3, 3, 3, 4]
// Expected Output: 3



//! 47.	Sum of Even Numbers in Array

// Calculate the sum of all even numbers in an array.
// Input: [1, 2, 3, 4]
// Expected Output: 6
function sumOfEvenNumbers(arr) {
  let sum = 0;
  for (let num of arr) {
    if (num % 2 === 0) {
      sum++;
    }
  }
  return sum;
}

// Test
// const tt = [1, 2, 3, 4];
// console.log("Sum of even numbers:", sumOfEvenNumbers([1, 2, 3, 4])); // Output: 6



//! 48.	Sum of Odd Numbers in Array
// Calculate the sum of all odd numbers in an array.
// Input: [1, 2, 3, 4]
// Expected Output: 4

function sumOfOddNumbers(arr) {
  let ct = 0;
  for (let num of arr) {
    if (num % 2 !== 0) {
      ct ++;
    }
  }
  return ct;
}

// Test
// console.log("Sum of odd numbers:", sumOfOddNumbers([1, 2, 3, 4])); // Output: 4


// 49.	Convert Time to 24-Hour Format

// Convert time from 12-hour format to 24-hour format.
// Input: “02:30 PM”
// Expected Output: “14:30”



// 50.	Check if Array Contains Specific Value

function containsValue(arr, value) {
  return arr.includes(value);
}

// Test
console.log(containsValue([1, 2, 3, 4], 3)); 

