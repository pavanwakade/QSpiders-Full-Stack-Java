function add(a,b){
    return a + b;
}
console.log(add(2,3));

//using lambda function

let a=(x,y) => x + y;
console.log(a(2,3));


let hello = arg => `hello ${arg}`;
console.log(hello("pavan"));
