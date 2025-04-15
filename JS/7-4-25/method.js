// let a = typeof 10;

// console.log(typeof a); // string

// console.log(typeof typeof 10); // string




let str = "hello";
let str1 = 'hello';
let str3 = new String("hello");

console.log(str);
console.log(str1);
console.log(str3);

console.log(str.toLowerCase());
console.log(str.toUpperCase());

console.log(str.charAt(0));
console.log(str.charCodeAt(0));

console.log(str.padStart(10, "X"));
console.log(str.padEnd(10, "X"));


console.log(str.startsWith("h"));
console.log(str.startsWith("he"));
console.log(str.startsWith("p"));

console.log(str.endsWith("llo"));
console.log(str.endsWith("o"));
console.log(str.endsWith("p"));



console.log(str.indexOf("l"));
console.log(str.indexOf("e"));
console.log("pavan".indexOf("n"));


let nam = "pavan";
console.log(nam.lastIndexOf("a"));

let num = 1000;
console.log(num.toString());


let b = true;
console.log(b.toString());

let un = undefined;
// console.log(un.toString()); // TypeError: Cannot read properties of undefined (reading 'toString')

let n = null;
// console.log(n.toString()); // TypeError: Cannot read properties of null (reading 'toString')

let arr = [1, 2, 3, 4, 5];
console.log(arr.toString()); // "1,2,3,4,5"






let move = "bahubali";
console.log(move.replace("b", "P"));
console.log(move.replace('i', 'I'));
console.log(move.replace("bahubali", "a"));



let c = move.replace("b", "kh");
console.log(c);

let d = c.replace('b', 'g');
console.log(d);

let e = d.replace('l', 'll');
console.log(e);





let a = move.replace("b", "kh").replace("b", "g")
console.log(a);





let strs = "Lorem ipsum dolor sit amet consectetur adipisicing elit. Adipisci veniam earum impedit corrupti consectetur eius provident molestiae ab deleniti cumque debitis obcaecati, amet aspernatur veritatis distinctio at eligendi doloribus! Omnis!";

// console.log(strs.includes("impedit"));   //true

// console.log(strs.includes("!"));

// console.log(strs.includes("Adipisci veniam earum"));


//slice()   //extract part of the string

// syntax   slice(start index,end index) it will not include end index

// console.log(move.slice(0,5)); 

// console.log("pavan".slice(1,5));


let nn = "pavan";

console.log(nn.split('a'));
console.log(nn.split(''));

console.log(strs.split(''));

let strArray = strs.split(' ');

console.log(strArray);

let inp = "pavan";
let check = (p) => strArray.includes(p) ? console.log(p) : console.log(`${p} not present`);
console.log(check(inp));


// let check = (p) => {
//     if (strArray.includes(p)) {
//         console.log(p);

//     }
//     else {
//         console.log(`${p} not present`);
//     }
// }
// check("pavan");




// isFinite(arg)

let numbr = 10;

console.log(isFinite(numbr));

console.log(isFinite("10"));
console.log(Number.isInteger(numbr));
console.log(Number.isNaN("10"));
console.log(Number.isNaN(10));
console.log(Number.isNaN(NaN));
console.log(Number.parseInt("1000 year old")); //only 1000
console.log(Number.parseInt("10.5")); //only 10
console.log(Number.parseInt("10.5abc")); //only 10
console.log(Number.parseFloat("10.5abc")); //10.5


