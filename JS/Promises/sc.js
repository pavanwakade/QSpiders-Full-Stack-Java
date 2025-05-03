//promise in js
//promises are the objects that represent the eventual completion 
// (or failure) of an asynchronous operation and its resulting value.
//promise is a placeholder for a value that we don't have yet but
//promise is an object that may produce a single value some time in the future


//!js syntax
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



let p = fetch("https://api.github.com/users")

let p2 = p.then((res) => {
    return res.json()
});
p2.then((api) => {
    // console.log(api);
});


fetch("https://api.github.com/users").then((res) => res.json()).then((api) => console.log(api)).finally(console.log("hello")
);



