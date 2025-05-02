//promise in js

//!js syntax
//   let a = 10;
//   let promise = new Promise((resolve, reject) => {
//     if (a == 10) {
//       resolve("a is 10");
//     } else {
//       reject("a is not 10");
//     }
//   });

// //   console.log(promise);

//   promise.then((msg) => console.log(msg));
//   promise.catch((msg) => console.log(msg));
//   promise.finally((msg) => console.log("end"));



let p = fetch("https://api.github.com/users")

let p2 = p.then((res) => {
    return res.json()
});
p2.then((api) => {
    console.log(api);
});


fetch("https://api.github.com/users").then((res) => res.json()).then((api) => console.log(api))