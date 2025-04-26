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

obj.fun1();


// callappliedbind