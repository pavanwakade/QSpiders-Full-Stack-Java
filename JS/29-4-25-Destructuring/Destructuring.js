

//! Destructur

let students = {
    id: 1,
    name: "pavan",
    add: "latur",
    address: {
        city: "latur",
        pin: 411947
    }
}
// console.log(students.add);


let { name, add } = students;
// console.log(name);
// console.log(add);


let bagpak2 = [1, 2, 3, "pavan", ["latur", "indapur"]]

let [a, b] = bagpak2;

// console.log(a);
// console.log(b);

let [, , , , [d, r]] = bagpak2;
// console.log(d);
// console.log(r);


//!rest 

let bagpak3 = ["ram", "sham", "Yudhishthira", "Bhima", "Arjuna", "Nakula", "Sahadeva"];
let [a1, b2, ...pandav] = bagpak3;
// console.log(pandav);
// console.log(...pandav);


let addd = (a, b, c, d) => console.log(a + b + c + d);

addd(1, 2, 3, 4);

let restadd = (...addall) => {
    return addall.reduce((acc, value) => acc + value);

}

// console.log(restadd(1,2,3,4,5,6,6,7));


// !Spread

// ?concatination of two array using Sprade

let arr1 = [1, 2, 3, 4, 5, 6];
let arr2 = [7, 8, 9, 10, 11];

let addi = [...arr1, ...arr2];
console.log(addi);








