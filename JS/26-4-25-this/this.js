let obj = {
    name: "pavan",
    add: "latur",
    fun1: () => {
        console.log(this);
    }

    ,
    fun2: function () {
        console.log(this);
        let fun = () => {
            console.log(this);
        }
        fun();
    }
}

// obj.fun1();


// callappliedbind


let student = {
    id: 1,
    name: "pavan",
    add: "latur"
}
let student1 = {
    id: 1,
    name: "pavan",
    add: "latur"
}
let studen2t = {
    id: 1,
    name: "pavan",
    add: "latur"
}
let student3 = {
    id: 1,
    name: "pavan",
    add: "latur"
}


function check() {
    console.log(this);
}


check.call(student3)