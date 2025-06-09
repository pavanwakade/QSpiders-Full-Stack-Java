let body=document.getElementsByTagName("body")[0];

let container=document.createElement("div");
container.setAttribute("class", "container");
container.style.backgroundColor="white";
container.style.height="100vh";
container.style.width="100vw";

let header=document.createElement("section");
header.style.display="flex";
header.style.height="70px";
// header.style.width="100%";
header.style.backgroundColor="white";


let hero=document.createElement("section");
hero.style.display="flex";
hero.style.height="45vh";
// hero.style.width="100%";
hero.style.backgroundColor="rgb(179, 230, 214)";

let hero1=document.createElement("div");
hero1.style.display="flex";
hero1.style.height="45vh";
hero.style.width="100%";
hero1.backgroundColor="rgb(0, 0, 0)";




body.appendChild(container);
container.append(header,hero);
hero.append(hero1)
