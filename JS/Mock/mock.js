// //! Variables in JS

// //! var
// //? Stored in global scope/window object (if declared outside functions)
//  We can:
// declare only: var a;
//  declare and initialize: var a = 10;
//  update: a = 20;
// redeclare: var a = 50;


// //! let
// //? Stored in block scope/Temporal Dead Zone (TDZ) before initialization
// /* We can:
// - declare only: let a;
// - declare and initialize: let a = 10;
// - update: a = 20;
// //? Cannot:
// - redeclare: let a = 50; // SyntaxError in same scope
// */

// //! const
// //? Stored in block scope/Temporal Dead Zone (TDZ) before initialization
// /* We can:
// - declare and initialize: const a = 10;
// //? Cannot:
// - declare only: const a; // SyntaxError
// - update: a = 20; // TypeError
// - redeclare: const a = 50; // SyntaxError in same scope
// */


// //! datatypes (NNBBSSU)
// //? Primitive types

// //!Number
// //?use with all types of numbers
// let a = 10; // decimal
// let b = 10.10; // decimal

// //!Null
// //we need to assign null explicitly
// //?used to indicate that a variable has no value
// let n = null; // Null


// //!BigInt
// //?use with all types of numbers
// let c = 100n; // BigInt
// let d = BigInt(100); // BigInt

// //!boolean
// //?used to indicate true or false
// //boolean default value is false
// let e = true; // boolean
// let f = false; // boolean

// console.log(true + false); // 1
// console.log(true + true); // 2
// console.log(false + false); // 0
// console.log(false + true); // 1

// //!String
// //?used to indicate string values
// let g = 'hello'; // String
// let h = "hello"; // String
// let i = `hello`; // String
// let j = new String("hello"); // String
// let k = String("hello"); // String
// let l = String(100); // String

// //!Symbol
// //?used to create unique identifiers
// let m = Symbol("hello"); // Symbol
// let o = Symbol("hello"); // Symbol
// let r = Symbol(100); // Symbol
// let s = Symbol(100); // Symbol
// console.log(m === o); // false
// console.log(m == o); // false
// console.log(r === s); // false
// console.log(r == s); // false


// //!Undefined
// //?used to indicate that a variable has no value
// //?undefined is a type itself
// let p; // Undefined
// let q = undefined; // Undefined


// //?Non-primitive types

// //!Array
// //?used to store multiple values in a single variable
// let arr1 = [1, 2, 3, 4, 5]; // Array
// console.log(arr1); //[1, 2, 3, 4, 5]

// let arr2 = new Array(1, 2, 3, 4, 5); // Array
// console.log(arr2); //[1, 2, 3, 4, 5]

// let arr3 = new Array(); // Array
// arr3 = [1, 2, 3, 4, 5]; // Array
// console.log(arr3); // [1, 2, 3, 4, 5]

// let arr4 = new Array(5); // Array
// console.log(arr4); // [ <5 empty items> ]

// let arr5 = new Array(5, 6); // Array
// console.log(arr5); // [ 5, 6 ]

// let arr6 = new Array(5, 6, 7); // Array
// console.log(arr6); // [ 5, 6, 7 ]




// //!Object
// //?used to store multiple values in a single variable in key-value pair
// //object literal way
// let obj1 = {
//     name: "John",
//     age: 30
// }; // Object

// // console.log(obj1); // { name: 'John', age: 30 }


// //create object using new  
// let obj2 = new Object(); // Object
// obj2.name = "John"; // Object
// obj2.age = 30; // Object
// console.log(obj2); // { name: 'John', age: 30 }


// let obj3 = new Object({
//     name: "John",
//     age: 30
// }); // Object
// console.log(obj3); // { name: 'John', age: 30 }

// //by using class constuctor
// class namee {
//     constructor(name,age) {
//         this.name=name;
//         this.age=age;
//     }
// }
// let objj=new namee("pavan",25);
// console.log(objj);










// let obj1 = { name: "chombi",
// age: 16,
// speak: function () {
// console.log('i can speak');
// } }
// obj1["speak"]();//i can speak
// obj1.speak();//i can speak






// let pavan={
//     name:"pavan",
//     age:25,
//     location:"latur",
//     EDU:{
//         10:"matephal",
//         12:"science",
//         Diploma:"computer",
//         BE:"E&TC"
//     }
// }

// pavan.age=26;//?update
// delete pavan.EDU["12"];//?delete
// pavan.certificates={Qspider:"Java Full Stack",Symboises:"java full stack"};
// pavan.certificate="postman";
// console.log(pavan);


// console.log("age" in pavan);//?true
// console.log("certificate" in pavan);


// copyobj=pavan;
// console.log(copyobj);//?shallow copy the same object

// let deepcopyy={};

//  deepcopyy=pavan;//?deep copy
//  pavan.certificate="postman";

// console.log(deepcopyy);






let body = document.querySelector("body")
// body.style.backgroundColor = "yellow";
let circle = document.getElementById("circle")
let squre = document.getElementById("squre")
let divv = document.getElementById("sty")

// circle.addEventListener("click", () => {
//     divv.style.width = "300px";
//     divv.style.height = "300px";
//     divv.style.backgroundColor = "green";
//     divv.style.borderRadius = "50%"
//     divv.style.margin = "100px"
//     divv.style.display = "flex"
//     divv.style.justifyContent = "center"
//     divv.style.alignItems = "center"
//     divv.style.color = "white"
//     divv.innerText = "green"
//     divv.style.fontSize = "40px"
//     body.style.backgroundColor = "red"
//     circle.style.backgroundColor = "green"
//     squre.style.backgroundColor = "green"

// })

// squre.addEventListener("click", () => {
//     divv.style.width = "300px";
//     divv.style.height = "300px";
//     divv.style.backgroundColor = "red";
//     divv.style.borderRadius = "0"
//     divv.style.margin = "100px"
//     divv.style.display = "flex"
//     divv.style.justifyContent = "center"
//     divv.style.alignItems = "center"
//     divv.style.color = "white"
//     divv.innerText = "RED"
//     divv.style.fontSize = "50px"
//     body.style.backgroundColor = "green"
//     circle.style.backgroundColor = "red"
//     squre.style.backgroundColor = "red"
// })
let alldata = [];
let datadiv = document.querySelector("#datadiv");

const displayItems = (items) => {
    // datadiv.innerHTML = ''; // Clear previous content
    items.forEach(item => {
        datadiv.innerHTML += `
            <div id ="dd" style="border: 1px solid #ccc; padding: 15px; margin: 10px; border-radius: 8px;">
                <img src="${item.thumbnail}" alt="${item.title}" width="200px" height="200px">
                <h3>${item.title}</h3>
                <p>Brand: ${item.brand}</p>
                <p>Price: $${item.price}</p>
                <p>Category: ${item.category}</p>
                <p>Rating: ${item.rating}/5</p>
                <p>Stock: ${item.stock}</p>
            </div>`;
    });
}

const fetchdata = async () => {
    try {
        let response = await fetch("https://dummyjson.com/products");
        let data = await response.json();
        alldata = data.products;
        displayItems(alldata);
    } catch (error) {
        console.error("Error fetching data:", error);
        datadiv.innerHTML = '<p>Error loading products</p>';
    }
}

fetchdata();
