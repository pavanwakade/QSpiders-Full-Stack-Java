
let body = document.querySelector("body")
body.style.backgroundColor = "yellow";
let circle = document.getElementById("circle")
let squre = document.getElementById("squre")
let divv = document.getElementById("sty")

circle.addEventListener("click", () => {
    divv.style.width = "300px";
    divv.style.height = "300px";
    divv.style.backgroundColor = "green";
    divv.style.borderRadius = "50%"
    divv.style.margin = "100px"
    divv.style.display = "flex"
    divv.style.justifyContent = "center"
    divv.style.alignItems = "center"
    divv.style.color = "white"
    divv.innerText = "green"
    divv.style.fontSize = "40px"
    body.style.backgroundColor = "red"
    circle.style.backgroundColor = "green"
    squre.style.backgroundColor = "green"

})

squre.addEventListener("click", () => {
    divv.style.width = "300px";
    divv.style.height = "300px";
    divv.style.backgroundColor = "red";
    divv.style.borderRadius = "0"
    divv.style.margin = "100px"
    divv.style.display = "flex"
    divv.style.justifyContent = "center"
    divv.style.alignItems = "center"
    divv.style.color = "white"
    divv.innerText = "RED"
    divv.style.fontSize = "50px"
    body.style.backgroundColor = "green"
    circle.style.backgroundColor = "red"
    squre.style.backgroundColor = "red"
})

let alldata = [];
let datadiv = document.querySelector("#datadiv");

const displayItems = (items) => {
    items.forEach(item => {
        datadiv.innerHTML += `<div>${item.id}: ${item.title}  :::::   ${item.brand} </div>`;
    });
}

const fetchdata = async () => {
    try {
        let response = await fetch("https://dummyjson.com/products");
        let data = await response.json();
        displayItems(data.products);
    } catch (error) {
        console.error("Error fetching data:", error);
    }
}

fetchdata();
