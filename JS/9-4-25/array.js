

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


let nummm=[2,3,4,5,7,8]

let ff=nummm.filter((val){
    return val
})


nummm.reduce