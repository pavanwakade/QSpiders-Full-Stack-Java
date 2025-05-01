//! Variables in JS

//! var
//? Stored in global scope/window object (if declared outside functions)
/* We can:
- declare only: var a;
- declare and initialize: var a = 10;
- update: a = 20;
- redeclare: var a = 50;
*/

//! let
//? Stored in block scope/Temporal Dead Zone (TDZ) before initialization
/* We can:
- declare only: let a;
- declare and initialize: let a = 10;
- update: a = 20;
//?Cannot:
- redeclare: let a = 50; // SyntaxError in same scope
*/

//! const
//? Stored in block scope/Temporal Dead Zone (TDZ) before initialization
/* We can:
- declare and initialize: const a = 10;
//?Cannot:
- declare only: const a; // SyntaxError
- update: a = 20; // TypeError
- redeclare: const a = 50; // SyntaxError in same scope
*/


//! datatypes (NNBBSSU)
//? Primitive types

//!Number
//?use with all types of numbers
let a = 10; // decimal
let b = 10.10; // decimal

//!Null
//we need to assign null explicitly
//?used to indicate that a variable has no value
let n = null; // Null


//!BigInt
//?use with all types of numbers
let c = 100n; // BigInt
let d = BigInt(100); // BigInt

//!boolean
//?used to indicate true or false
let e = true; // boolean
let f = false; // boolean

console.log(true + false); // 1
console.log(true + true); // 2
console.log(false + false); // 0
console.log(false + true); // 1

//!String
//?used to indicate string values
let g = 'hello'; // String
let h = "hello"; // String
let i = `hello`; // String
let j = new String("hello"); // String
let k = String("hello"); // String
let l = String(100); // String

//!Symbol
//?used to create unique identifiers
let m = Symbol("hello"); // Symbol
let o = Symbol("hello"); // Symbol
let r = Symbol(100); // Symbol
let s = Symbol(100); // Symbol
console.log(m === o); // false
console.log(m == o); // false
console.log(r === s); // false
console.log(r == s); // false


//!Undefined
//?used to indicate that a variable has no value
//?undefined is a type itself
let p; // Undefined
let q = undefined; // Undefined


//?Non-primitive types

//!Array
//?used to store multiple values in a single variable
let arr1 = [1, 2, 3, 4, 5]; // Array
console.log(arr1); //[1, 2, 3, 4, 5]

let arr2 = new Array(1, 2, 3, 4, 5); // Array
console.log(arr2); //[1, 2, 3, 4, 5]

let arr3 = new Array(); // Array
arr3 = [1, 2, 3, 4, 5]; // Array
console.log(arr3); // [1, 2, 3, 4, 5]

let arr4 = new Array(5); // Array
console.log(arr4); // [ <5 empty items> ]

let arr5 = new Array(5, 6); // Array
console.log(arr5); // [ 5, 6 ]

let arr6 = new Array(5, 6, 7); // Array
console.log(arr6); // [ 5, 6, 7 ]




//!Object
//?used to store multiple values in a single variable in key-value pair
//object literal way
let obj1 = {
    name: "John",
    age: 30
}; // Object

// console.log(obj1); // { name: 'John', age: 30 }


//create object using new  
let obj2 = new Object(); // Object
obj2.name = "John"; // Object
obj2.age = 30; // Object
console.log(obj2); // { name: 'John', age: 30 }


let obj3 = new Object({
    name: "John",
    age: 30
}); // Object
console.log(obj3); // { name: 'John', age: 30 }

//by using class constuctor
class namee {
    constructor(name,age) {
        this.name=name;
        this.age=age;
    }
}
let objj=new namee("pavan",25);
console.log(objj);





