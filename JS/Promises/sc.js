// !promise in js
// promises are the objects that represent the eventual completion 
// (or failure) of an asynchronous operation and its resulting value.
// promise is a placeholder for a value that we don't have yet but
// promise is an object that may produce a single value some time in the future
// promisees are introduce to avoid callback hell
// ! callback hell: its a complex situation when we use multiple callback function

// !js syntax
//   let a = 10;
//   let promise = new Promise((resolve, reject) => {
//     if (a == 10) {
//       resolve("a is 10");
//     } else {
//       reject("a is not 10");
//     }
//   });

//   console.log(promise);

//   promise.then((msg) => console.log(msg));
//   promise.catch((msg) => console.log(msg));
//   promise.finally((msg) => console.log("end"));



// let p = fetch("https://api.github.com/users")

// let p2 = p.then((res) => {
//     return res.json()
// });
// p2.then((api) => {
//     console.log(api);
// });


// fetch("https://api.github.com/users").then((res) => res.json()).then((api) => console.log(api));

// fetch("https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&current=temperature_2m,wind_speed_10m&hourly=temperature_2m,relative_humidity_2m,wind_speed_10m").then((res) => res.json()).then((api) => {
//     // console.log(api);

//     console.log("Current time :" +api.current.time);

//     console.log("Current temperature : " + api.current.temperature_2m + api.current_units.temperature_2m);
// });



// async,await is a advance wait to handle promises
// async function is a function that returns a promise
// await is a keyword that can only be used inside an async function
// await makes the function wait for a promise to be resolved or rejected

// let funnn = async () => {

//     let resobj = await fetch("https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&current=temperature_2m,wind_speed_10m&hourly=temperature_2m,relative_humidity_2m,wind_speed_10m")

//     let data = await resobj.json()

//     console.log(data);
// }
// funnn()



// !timer   

// setTimeout: it is used to execute a function after a specified interval of time

// console.log("start");
// setTimeout(() => {
//     console.log("Timeout executed");
// }, 1000);



// setInterval: it is used to execute a function repeatedly at a specified interval of time
// let count = 0;
// setInterval(() => {
//     console.log(count++);
// }, 1000);


// let count2 = 10;
// let interval = setInterval(() => {
//     console.log(count2);
//     if (count2 > 0) count2 -= 1;
//     else clearInterval(interval);
// }, 1000);