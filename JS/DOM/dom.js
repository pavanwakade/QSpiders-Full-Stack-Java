// console.log(Window.document);
// let heading = document.getElementsByTagName("h1");
// console.log(heading);
// let para=document.getElementsByTagName("p");

// document.body.style.backgroundColor = "yellow";
// for (let i = 0; i < heading.length; i++) {
//     heading[i].style.backgroundColor = "red";
//     heading[i].style.color = "white";
// }

// for (let i = 0; i < para.length; i++) {
//     para[i].style.backgroundColor = "blue";
//     para[i].style.color = "white";
// }


// let headingByid=document.getElementById("hh");

// headingByid.style.backgroundColor = "green";
// headingByid.style.color = "white";


// let divclass=document.getElementsByClassName("dombackground") //target html element by class name 
// console.log(divclass);

// for (let i = 0; i < divclass.length; i++) { // loop through all the elements with the class name
//     divclass[i].style.backgroundColor="pink"; // set the background color
//     divclass[i].style.color="white";// set the text color
//     divclass[i].style.fontSize="20px";// set the font size
// }




//!querySelector
//? querySelector is used to select the first element that matches the specified CSS selector(s) in the document.

let ele=document.querySelector('#hh')
console.log(ele);

let classs=document.querySelectorAll('div')
console.log(classs);
classs.forEach((val)=>val.style.backgroundColor="yellow")






//!querySelectorAll
//? querySelectorAll is used to select all elements that match the specified CSS selector(s) in the document.










let userdata = [
    {
        "id": 1,
        "firstname": "Frederique",
        "lastname": "Claibourn",
        "email": "fclaibourn0@opensource.org",
        "gender": "Female",
        "contact": "554-123-9026"
    },
    {
        "id": 2,
        "firstname": "Cesaro",
        "lastname": "Lendrem",
        "email": "clendrem1@ucoz.ru",
        "gender": "Male",
        "contact": "889-402-9013"
    },
    {
        "id": 3,
        "firstname": "Merry",
        "lastname": "Wurst",
        "email": "mwurst2@smugmug.com",
        "gender": "Male",
        "contact": "220-856-3003"
    },
    {
        "id": 4,
        "firstname": "Iver",
        "lastname": "Stormont",
        "email": "istormont3@wikia.com",
        "gender": "Male",
        "contact": "947-245-9158"
    },
    {
        "id": 5,
        "firstname": "Berke",
        "lastname": "Alphonso",
        "email": "balphonso4@gmpg.org",
        "gender": "Male",
        "contact": "597-994-3017"
    },
    {
        "id": 6,
        "firstname": "Ev",
        "lastname": "Turn",
        "email": "eturn5@admin.ch",
        "gender": "Male",
        "contact": "859-107-0216"
    },
    {
        "id": 7,
        "firstname": "Natividad",
        "lastname": "Janovsky",
        "email": "njanovsky6@google.fr",
        "gender": "Female",
        "contact": "230-374-7379"
    },
    {
        "id": 8,
        "firstname": "Josh",
        "lastname": "Maslen",
        "email": "jmaslen7@zdnet.com",
        "gender": "Male",
        "contact": "847-881-9761"
    },
    {
        "id": 9,
        "firstname": "Corbet",
        "lastname": "Brabham",
        "email": "cbrabham8@wp.com",
        "gender": "Male",
        "contact": "765-213-7312"
    },
    {
        "id": 10,
        "firstname": "Vania",
        "lastname": "Gringley",
        "email": "pavan9@nih.gov",
        "gender": "male",
        "contact": "116-937-9747"
    },
    {
        "id": 1,
        "firstname": "Frederique",
        "lastname": "Claibourn",
        "email": "fclaibourn0@opensource.org",
        "gender": "Female",
        "contact": "554-123-9026"
    },
    {
        "id": 2,
        "firstname": "Cesaro",
        "lastname": "Lendrem",
        "email": "clendrem1@ucoz.ru",
        "gender": "Male",
        "contact": "889-402-9013"
    },
    {
        "id": 3,
        "firstname": "Merry",
        "lastname": "Wurst",
        "email": "mwurst2@smugmug.com",
        "gender": "Male",
        "contact": "220-856-3003"
    },
    {
        "id": 4,
        "firstname": "Iver",
        "lastname": "Stormont",
        "email": "istormont3@wikia.com",
        "gender": "Male",
        "contact": "947-245-9158"
    },
    {
        "id": 5,
        "firstname": "Berke",
        "lastname": "Alphonso",
        "email": "balphonso4@gmpg.org",
        "gender": "Male",
        "contact": "597-994-3017"
    },
    {
        "id": 6,
        "firstname": "Ev",
        "lastname": "Turn",
        "email": "eturn5@admin.ch",
        "gender": "Male",
        "contact": "859-107-0216"
    },
    {
        "id": 7,
        "firstname": "Natividad",
        "lastname": "Janovsky",
        "email": "njanovsky6@google.fr",
        "gender": "Female",
        "contact": "230-374-7379"
    },
    {
        "id": 8,
        "firstname": "Josh",
        "lastname": "Maslen",
        "email": "jmaslen7@zdnet.com",
        "gender": "Male",
        "contact": "847-881-9761"
    },
    {
        "id": 9,
        "firstname": "Corbet",
        "lastname": "Brabham",
        "email": "cbrabham8@wp.com",
        "gender": "Male",
        "contact": "765-213-7312"
    },
    {
        "id": 10,
        "firstname": "Vania",
        "lastname": "Gringley",
        "email": "pavan9@nih.gov",
        "gender": "male",
        "contact": "116-937-9747"
    }
];

// userdata.filter((user)=>{
//     if(user.firstname.startsWith("F")){
//         document.write(user);
//     }
// })



