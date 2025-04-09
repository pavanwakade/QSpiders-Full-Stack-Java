

let f = (a, b) => a + b;

let a = [1, 2, 3, "hello", null, NaN, f, undefined];

// console.log(a);

// console.log(a[6](4,7));


for (let i = 0; i < a.length; i++) {
    // console.log(a[i]);
}

// a.forEach(element => console.log(element));


//MAP

//Working of map
let num = [1, 3, 4, 6, 8, 0, 2];
let apnamap = (cbf, arr) => {
    for (let i = 0; i < arr.length; i++) {
        cbf(arr[i])
    }
}

apnamap((val) => {
    // console.log(val)
}, num)



//map
num.map((val) => {
    // console.log(val)
}
);


let friends = ["pavan", "shashi", "kedar", "sandip"]

friends.map((val) => {
    if (val === "pavan") {
        // console.log(val)
        return;
    }
}
);



let numbers = [8, 5, 7, 9, 2, 6, 8];
numbers.map((val, i) => console.log(val * 100 + 5 + 10, i));
