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

//by using class constuctor
class namee {
    constructor(name,age) {
        this.name=name;
        this.age=age;
    }
}
let objj=new namee("pavan",25);
// console.log(objj);



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
// console.log(person.hobbies);

// person.hobbies.push("BGMI");//!update

// delete person.hobbies[person.hobbies.length-1];  //!delete last //?it will just delete the value not block of Array  //<1 empty item>
// delete person.hobbies;  //!delete

// console.log(person); //!read

// console.log(person.hobbies);


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

// const objDiv = document.getElementById("obj");
// arr.map((val) => {
//     const paragraph = document.createElement("div");
//     paragraph.innerHTML = `<p>ID: ${val.id}</p>
//    <p>Name: ${val.name}</p>
//    <p>YOP: ${val.yop}</p>`;
//     objDiv.appendChild(paragraph);
// });



//! methods of object

let pavan = {
    education: "BE",
    yop: 2024,
    collage: "SBPCOE",
    certificates: "Java Full Stack"
}


// console.log("Keys :"+Object.keys(pavan));  //!print all keys
// console.log("values :"+Object.values(pavan)); //!print all values
// console.log("entries:"+Object.entries(pavan));//!print all / convert object to Array

let car = [
    ["id", 1],
    ["name", "innova"],
    ["speed", 120]
]
// console.log(Object.fromEntries(car));   //!conver Array to object

let emp1 = {
    name: "pavan"
}

let emp2 = {
    age: 26
}

let emp3 = {
   add:"lature"
}


let newaobj = Object.assign(emp1, emp2);//(target,sourse)

// console.log(newaobj);//concate objects

// console.log(emp1); //all sourse store in target

// console.log(emp2);//sourse not changed




let newaobj1 = Object.assign(emp1, emp2,emp3);//we can have n no of sourse
// console.log(newaobj1);



//when we want dont change any object than

let newobj3=Object.assign({},emp1,emp2,emp3)//it strore all value in new object  here store in {}   , here {} is a target
// console.log(newobj3);

newobj3=Object.assign({aa:77},emp1,emp2,emp3)
// console.log(newobj3);  //we can also add extra values directly

//! in operator
// console.log("add" in newobj3); ///it check weither proparty present in that object or not

// console.log();


// for (const key in pavan) {
//    console.log(key);
// }

let ketan={
    education: "BE",
    yop: 2024,
    collage: "SBPCOE",
    certificates: "Java Full Stack"
}


//!shalo copy here if we changes any in copy object  chnged in orignal object
// let b=ketan;  
// b.loc="jalgaon";

// console.log(b);
// console.log(ketan);


//!deepcopy //here if we changes any in copy object not chnge in orignal object
let a=JSON.stringify(ketan); //it convert js object into JSON object
let copy=JSON.parse(a); // 
copy.location="jalgaon";

// console.log(ketan);
// console.log(copy);

// shalo copy vs deep copy
// in shalow copy


// !Exception handeling

// try {
//     console.log("tinku nikla pinku ke ghar");
//     throw new Error("tonku ne boot dekha");
    
// } catch (error) {
    // console.log(error);
// }
// finally{
//     console.log("Zut bolta hai sala");
    
// }

// try {
//     printkaro(a)
//     console.log("Executing try block");
// } catch (error) {
    
//     console.log("An error occurred:", error);
// }
// finally{
//     console.log("hahaha");
    
// }