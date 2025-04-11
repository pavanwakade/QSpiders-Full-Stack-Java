

// let f = (a, b) => a + b;

// let a = [1, 2, 3, "hello", null, NaN, f, undefined];

// console.log(a);

// console.log(a[6](4,7));


// for (let i = 0; i < a.length; i++) {
//     // console.log(a[i]);
// }

// // a.forEach(element => console.log(element));


// //MAP
// //Working of map
// let num = [1, 3, 4, 6, 8, 0, 2];
// let apnamap = (cbf, arr) => {
//     for (let i = 0; i < arr.length; i++) {
//         cbf(arr[i])
//     }
// }

// apnamap((val) => {
//     // console.log(val)
// }, num)



// //map
// num.map((val) => {
//     // console.log(val)
// }
// );

// let friends = ["pavan", "shashi", "kedar", "sandip"]

// friends.map((val) => {
//     if (val === "pavan") {
//         // console.log(val)
//         return;
//     }
// }
// );


// let numbers = [8, 5, 7, 9, 2, 6, 8];
// numbers.map((val, i) => console.log(val * 100 + 5 + 10, i));











// //Map practice 

// let arr = [94, 3, 5, 6, 3, 3, 5, 6, 6,]

// //work of map

// let mapcaoncept = (callback, arr) => {
//     for (let i = 0; i < arr.length; i++) {
//         callback(arr[i]);
//     }
// }

// mapcaoncept((val) => {
//     // console.log(val);

// }, arr)




// // map
// arr.map((val) => console.log(val))



//,map with return

// let num = [1, 2, 3, 7, 6, 8, 9,];

// apnamap = (callback, arr) => {

//     let tremparr = [];
//     for (let i = 0; i < arr.length; i++) {

//         let a = callback(arr[i], i, arr)
//         tremparr.push(a);
//     }

//     return tremparr;
// }

// let b = apnamap((val, index, arr) => {
//     return val;
// }, num)

// console.log(b);



//Filter method

// let nume = [3, 5, 6, 7, 8, 9, 0];

// let apanaFilter = (callback, num) => {

//     let aaa = [];

//     for (let i = 0; i < num.length; i++) {

//         let a = callback(num[i], i, num)

//         if (a) {
//             aaa.push(num[i]);
//         }
//     }
//     return aaa
// }

// let final = apanaFilter((val, i, nume) => {
//     return val > 5
// }, nume)

// console.log(final);


//actual filter


let nummm = [2, 3, 4, 5, 7, 8]

let ff = nummm.filter((val) => {
    return val > 4
})
console.log(ff);


//find

let fin = nummm.find((val) => {
    return val > 4
}
)
console.log(fin);





// reduce
// let addition = nummm.reduce((acc, val, i, arr) => {
//     return acc += val;   //need to add/write every time  acc & val
// }, 10)

// console.log(addition);








let userdata = [
    [{ "id": 1, "firstname": "Frederique", "lastname": "Claibourn", "email": "fclaibourn0@opensource.org", "gender": "Female", "contact": "554-123-9026" },
    { "id": 2, "firstname": "Cesaro", "lastname": "Lendrem", "email": "clendrem1@ucoz.ru", "gender": "Male", "contact": "889-402-9013" },
    { "id": 3, "firstname": "Merry", "lastname": "Wurst", "email": "mwurst2@smugmug.com", "gender": "Male", "contact": "220-856-3003" },
    { "id": 4, "firstname": "Iver", "lastname": "Stormont", "email": "istormont3@wikia.com", "gender": "Male", "contact": "947-245-9158" },
    { "id": 5, "firstname": "Berke", "lastname": "Alphonso", "email": "balphonso4@gmpg.org", "gender": "Male", "contact": "597-994-3017" },
    { "id": 6, "firstname": "Ev", "lastname": "Turn", "email": "eturn5@admin.ch", "gender": "Male", "contact": "859-107-0216" },
    { "id": 7, "firstname": "Natividad", "lastname": "Janovsky", "email": "njanovsky6@google.fr", "gender": "Female", "contact": "230-374-7379" },
    { "id": 8, "firstname": "Josh", "lastname": "Maslen", "email": "jmaslen7@zdnet.com", "gender": "Male", "contact": "847-881-9761" },
    { "id": 9, "firstname": "Corbet", "lastname": "Brabham", "email": "cbrabham8@wp.com", "gender": "Male", "contact": "765-213-7312" },
    { "id": 10, "firstname": "Vania", "lastname": "Gringley", "email": "vgringley9@nih.gov", "gender": "Female", "contact": "116-937-9747" }]

]


// filter
let users = userdata.filter((val) => {
    return val.id == 1;
})

console.log(users);


//find

