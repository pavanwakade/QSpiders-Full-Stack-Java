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
            university: "SPPU",
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

// person.hobbies.push("BGMI");//!update

// delete person.hobbies;  //!delete

// console.log(person); //!read



let arr = [{
    id: 1,
    name: "pavan",
    yop: 2024
},
{
    id: 2,
    name: "pavan",
    yop: 2024
},
{
    id: 3,
    name: "pavan",
    yop: 2024
},
{
    id: 4,
    name: "pavan",
    yop: 2024
},
{
    id: 5,
    name: "pavan",
    yop: 2024
},
{
    id: 6,
    name: "pavan",
    yop: 2024
},
{
    id: 7,
    name: "pavan",
    yop: 2024
},
{
    id: 8,
    name: "pavan",
    yop: 2024
},
{
    id: 9,
    name: "pavan",
    yop: 2024
},
{
    id: 10,
    name: "pavan",
    yop: 2024
}]

// arr.map((val) => console.log(val.id));
// arr.map((val) => console.log(val));

const objDiv = document.getElementById("obj");
arr.map((val) => {
    const paragraph = document.createElement("div");
    paragraph.innerHTML = `<p>ID: ${val.id}</p>
   <p>Name: ${val.name}</p>
   <p>YOP: ${val.yop}</p>`;
    objDiv.appendChild(paragraph);
});



//! methods of object

let pavan = {
    education: "BE",
    yop: 2024,
    collage: "SBPCOE",
    certificates: "Java Full Stack"
}


// console.log("Keys :"+Object.keys(pavan));  //?print all keys
// console.log("values :"+Object.values(pavan)); //?print all values
// console.log("entries:"+Object.entries(pavan));//?print all





