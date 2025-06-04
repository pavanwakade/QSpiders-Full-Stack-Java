//!default expoart

// import phone from "./india.js";
// console.log(phone);
// console.log(price);

// import resourses from "./india.js"; //!we can import with same name  with default export

// console.log(resourses);


// import indianoil from "./india.js"; //!we can import with diffrant name  with default export
// console.log(indianoil);


// console.log(indianoil);
// let resourses=indianoil;   //!import array for easy access
// console.log(resourses);
// console.log(resourses[0]);
// console.log(resourses[1]);


// let {resourses,resourses2}=indianoil;   //!import object and destucture for easy access

// console.log(resourses);
// console.log(resourses2);


     //! Named expoart

import { age, loc, name } from "./india.js";

console.log(name);
console.log(age);
console.log(loc);

console.log(
    `my name is ${name} my age is ${age} i am from ${loc}`
);
