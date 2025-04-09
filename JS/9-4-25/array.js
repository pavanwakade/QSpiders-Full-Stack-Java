

let f= (a,b)=>a+b;

let a=[1,2,3,"hello",null,NaN,f];

console.log(a);

console.log(a[6](4,7));

