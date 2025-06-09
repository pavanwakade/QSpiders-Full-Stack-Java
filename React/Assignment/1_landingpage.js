let body = document.getElementsByTagName("body")[0];

let container = document.createElement("div");
container.setAttribute("class", "container");
container.style.backgroundColor = "white";
container.style.height = "100vh";
container.style.width = "100vw";

let header = document.createElement("section");
header.style.display = "flex";
header.style.height = "70px";
header.style.backgroundColor = "white";

let hero = document.createElement("section");
hero.style.display = "flex";
hero.style.height = "45vh";
hero.style.width = "100%";
hero.style.backgroundColor = "rgb(179, 230, 214)";

let hero1 = document.createElement("div"); // 🔥 fixed
// hero1.style.display = "flex";
hero1.style.width = "70%";
hero1.style.height = "100%";
hero1.style.color = "white";
hero1.style.backgroundColor = "rgb(179, 230, 214)"; // 🔥 fixed
hero1.style.display = "flex";
// hero1.style.flexDirection = "column";
hero1.style.justifyContent = "center";
hero1.style.alignItems = "center";

hero1.style.fontSize = "5vw"; // responsive to screen width
hero1.style.fontWeight = "bold";
hero1.style.textAlign = "center";
hero1.innerText = "SIMPLICITY";
hero1.style.padding = "1%";



let hero2 = document.createElement("div"); // 🔥 fixed
// hero1.style.display = "flex";
hero2.style.width = "30%";
hero2.style.backgroundColor = "rgb(75, 58, 58)"; // 🔥 fixed
let image = document.createElement("img");
image.setAttribute("src", "https://images.unsplash.com/photo-1522071820081-009f0129c71c?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80");
// Optional: style the image
image.style.width = "100%";
image.style.height = "100%";
image.style.objectFit = "cover"; // keeps image nicely filled






// Append in order
body.appendChild(container);
container.append(header, hero);
hero.append(hero1, hero2);
hero2.appendChild(image);