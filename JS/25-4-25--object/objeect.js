//! ways to create objects
// !--> 1
// let o = new fun({
//     id: 1,
//     name: "pavan"
// })

// // !--> 2
// let obj = {
//     id: 1,
//     name: "pavan"
// }


//? example

let person = {
    name: "pavan",
    age: 25,
    hobbies: ["Esport", "coding", "Travlling"],
    education: {
        degree: "BE",
        YOP: 2024,
        collage: {
            name: "SBPCOE",
            univercity: "SPPU",
            address: {
                city: "indapur",
                pincode: 411047,
                state: "Maharashtra"
            }
        }
    }
}


// console.log(person.hobbies[0]);
// console.log(person.hobbies.map((v) => {
//     return v;
// }));

// person.hobbies.map((v) => {
//     console.log(v);
// })

// console.log(person.education.collage.address.city);

// console.table(person.education.collage.address);




// ?CRUD

person.hobbies.push("BGMI");//!update

// delete person.

console.log(person); //!read

