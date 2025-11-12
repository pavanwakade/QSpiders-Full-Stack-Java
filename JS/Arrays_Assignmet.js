// --- Array Tasks ---

//! 1. Reverse an Array
let array1 = [1, 2, 3, 4, 5];
function reverseArray(arr) {
    return arr.slice().reverse();
}
// console.log("1. Reverse Array:", reverseArray(array1));



//! 2. Find Max and Min
// let array2 = [10, 5, 20, 8, 15];
// function findMaxMin(arr) {
//     arr.sort((a, b) => a - b);
//     let min = arr[0];
//     let max = arr[arr.length - 1];
//     // console.log(arr);
//     return { max, min };
// }
// console.log("2. Max and Min:", findMaxMin(array2));


//! 3. Check if Array is Palindrome
let array3 = [1, 2, 3, 2, 1];
function isArrayPalindrome(arr) {
    for (let i = 0, j = arr.length - 1; i < j; i++, j--) {
        if (arr[i] == arr[j]) {
            i++;
            j--;
        }
        else {
            return "not palinfrome";
        }
    }
    return "palindrome";
}
// console.log(isArrayPalindrome(array3));



// //! 4. Remove Duplicates
function removeDuplicates(arr) {
    return [...new Set(arr)];
}

// //! 5. Sum of All Elements
// function sumArray(arr) {
//     return arr.reduce((acc, num) => acc + num, 0);
// }

// //! 6. Reverse a String
// function reverseString(str) {
//     return str.split('').reverse().join('');
// }

// //! 7. Check if String is Palindrome
// function isStringPalindrome(str) {
//     return str === str.split('').reverse().join('');
// }

// //! 8. Count Number of Vowels
// function countVowels(str) {
//     let vowels = str.match(/[aeiou]/gi);
//     return vowels ? vowels.length : 0;
// }

// //! 9. Find First Non-Repeating Character
// function firstNonRepeatingChar(str) {
//     for (let char of str) {
//         if (str.indexOf(char) === str.lastIndexOf(char)) {
//             return char;
//         }
//     }
//     return null;
// }

// //! 10. Check if Two Strings are Anagrams
// function areAnagrams(str1, str2) {
//     return str1.split('').sort().join('') === str2.split('').sort().join('');
// }



// let array4 = [1, 2, 2, 3, 4, 4, 5];
// let array5 = [5, 10, 15];

// // String Inputs
// let str1 = "hello";
// let str2 = "madam";
// let str3 = "interview";
// let str4 = "stress";
// let str5a = "listen";
// let str5b = "silent";

// console.log("4. Remove Duplicates:", removeDuplicates(array4));
// console.log("5. Sum of Array:", sumArray(array5));

// console.log("6. Reverse String:", reverseString(str1));
// console.log("7. Is String Palindrome:", isStringPalindrome(str2));
// console.log("8. Number of Vowels:", countVowels(str3));
// console.log("9. First Non-Repeating Character:", firstNonRepeatingChar(str4));
// console.log("10. Are Anagrams:", areAnagrams(str5a, str5b));



