let body=document.getElementsByTagName("body")[0];
let div=document.createElement("div");
div.style.height="100vh";
div.style.width="100wh";
div.style.backgroundColor="white";


let header=document.createElement("section");
header.style.display="flex";
header.style.height="70px";
// header.style.width="100%";
header.style.backgroundColor="grey";
div.appendChild(header);


let hero=document.createElement("section");
hero.style.display="flex";
hero.style.height="45vh";
// hero.style.width="100%";
hero.style.backgroundColor="red";
div.appendChild(hero);





body.appendChild(div);