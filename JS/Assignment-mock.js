//🍛 "Lo re, yeh rahe tumhare JavaScript ke easy-easy  tasks"
//🔁 Loops (forEach, for...of, for...in – sirf arrays ke liye)

// ?________________________________________
//! 1. Har sheher ka naam print karo (forEach se)
//!Indian cities ka array diya gaya hai. Har city ke li
// ye print karo: City visited: [city]

const indiancities = ["Mumbai", "Pune", "Indore", "Kolkata"];

indiancities.forEach(element => {

    // console.log(element);
});


// ________________________________________
//! 2. Sab even PIN codes print karo (for...of)
//! Pin codes ka array hai. Sirf even wale print karo.
const pinCodes = [110001, 560034, 400001, 700019, 600005, 232330];

for (const element of pinCodes) {
    if (element % 2 == 0) {
        // console.log(element);

    }
}



// ________________________________________
//! 3. IPL players ka naam aur unka index print karo (forEach)
// !Jaise: "0 - Dhoni", "1 - Kohli", etc.
const players = ["Dhoni", "Kohli", "Rohit", "Pandya"];
players.forEach(element => {

    // console.log(`index : ${players.indexOf(element)}  ${players.} `)

});




// ________________________________________
//! 4. Har item ko ek message ke saath print karo (for...of)
//! Jaise:  [item]"
const items = ["Dahi", "Pani Puri", "Lassi", "Aloo Paratha"];
for (const element of items) {

    // console.log(`"Item mil gaya: ${element}`)
}


// ________________________________________
//! 🔄 map – Naye array banane ka magician
//! 5. Sab price pe 18% GST jod do (map ka use)
//! ₹ ke prices ka array diya hai. Har ek pe GST lagake naya array banao.
const prices = [100, 250, 500, 750];
const pricesWithGST = prices.map(price => price + (price * 0.18));
// console.log(pricesWithGST);


// ________________________________________
//! 6. Naam ko short form mein convert karo (map)
//! Jaise: "Sachin Tendulkar" → "S. Tendulkar"
const names = ["Sachin Tendulkar", "Rahul Dravid", "MS Dhoni"];

names.map((names) => {
    // let first = names[0];
    let lastName = names.split(" ")
    // console.log(`${names[0]}. ${lastName[1]}`);
}
)


// ________________________________________
//! 7. Har naam ki length nikaalo (map)
// !Array ke har string ki length ka array banao.
const namesofplayer = ["Delhi", "Jaipur", "Chandigarh", "Mumbai"];
const newlengthArray = [];
namesofplayer.map((namesofplayer) => {
    let playernameLength = namesofplayer.length;
    newlengthArray.push(playernameLength);
});
// console.log(newlengthArray);



// ________________________________________
//! 8. Sab item names ko UPPERCASE mein convert karo
//! "biryani" → "BIRYANI"
const foodItems = ["biryani", "paneer", "samosa", "jalebi"];

foodItems.map((foodItems) => {
    let newaa = foodItems.toUpperCase()
    // console.log(newaa);
}
)


// ________________________________________
//! 9. Har student's roll number ke aage "Roll No." jod do
//! Output hona chahiye: Roll No. 101, Roll No. 102, etc.
const rollNumbers = [101, 102, 103, 104];

// rollNumbers.forEach(element=> console.log(`ROLL NO. ${element}`))


// ________________________________________
//! 🔍 filter – Sirf kaam ki cheezein bacha lo
// ________________________________________
// !10. Sab odd numbers filter karo (filter)
// !Array mein se sirf odd numbers chahiye.
const numbers = [2, 5, 8, 11, 14, 17];
// console.log(numbers.filter((num)=>num%2!==0))

// ________________________________________
//! 11. Sirf bade log (age > 18) ko bacha lo (filter)
//! Bas adult log hi filter karo (string/number nahi, sirf logic!)
const ages = [12, 22, 17, 30, 19, 1, 2, 3];
//  console.log(ages.filter((age)=>age>18));

// ________________________________________
//! 12. Sab long naam (length > 6) filter karo
//! Chhote naam hata do.
const nams = ["Amit", "Abhishek", "Parthiban", "Ramesh", "ram"];
// console.log(nams.filter((nam) => nam.length < 6));

// ________________________________________
//! 13. Sab items jinke naam "p" se shuru ho rahe hain, filter karo
//! Case-insensitive check karo.
const snacks = ["Pani Puri", "Bhel", "Poha", "Pizza", "Samosa"];
// console.log(snacks.filter((nam) => nam.startsWith('P')));


// ________________________________________
//! 14. ₹100 se kam wale items nikaalo (filter)
//! Budget friendly list chahiye bhai!
const pricess = [150, 80, 60, 120, 200, 40];
// console.log(pricess.filter((pri)=>pri<100));


// ________________________________________
// 🔢 reduce – Total ka king
// ________________________________________
//! 15. Sab item prices ka total nikaalo (reduce)
//! Kirana store ki total bill banana hai.
const itemPrices = [40, 80, 60, 30, 100];
const total = itemPrices.reduce((acc, val) => acc + val, 0);
// console.log(total);


// ________________________________________
//! 16. Sentence banao words se (reduce)
//! Ek array of words diya hai, poora sentence banado.
 const words = ["India", "is", "my", "country"];

// console.log(words.reduce((acc, word) => acc + " " + word, ""));



// ________________________________________
//! 17. Har word ki total length milaake ek number banao (reduce)
//! Jaise ek hi string mein sab milakar length.
const states = ["Goa", "Kerala", "Punjab", "Gujarat"];
// console.log(states.reduce((acc, le) => acc + le.length, 0));


// ________________________________________
//? 🔍 find – Pehla mil jaaye toh kaafi hai
// ________________________________________
//! 18. Pehla naam find karo jo "M" se start hota ho (find)
//! Bhai "M" se hero chahiye.
 const namees = ["Amit", "Manish", "Vikas", "Mehul"];
//  console.log(namees.find((val)=>val[0]==="M"));


// ________________________________________
//! 19. Pehla bada number find karo jo 500 se upar ho (find)
//! Bas pehla mil jaaye, baaki chhodo.
 const prics = [120, 300, 550, 800];
// console.log( prics.find((val)=>val>500)  );




// ________________________________________
//? 🔃 sort & reverse – Thoda arrange, thoda ulta
// ________________________________________
//! 20. Sab numbers ascending mein sort karo
//! Chhota se bada.
 const marks = [88, 45, 76, 90, 69];
//  console.log(marks.sort((a, b) => a - b));
 
// ________________________________________
//! 21. Sab words ko reverse order mein print karo
//! Last word pehle aayega.
const fruits = ["Banana", "Mango", "Apple", "Guava"];
// console.log(fruits.reverse());


// ________________________________________
//! 22. Sab strings ko alphabetical order mein lagao (sort)
const itm = ["Jalebi", "Barfi", "Rasgulla", "Gulab Jamun"];
// console.log(itm.sort());


// ________________________________________
//! 23. Dates ko sort karo (string format)
//! "2023-05-01" jaisi dates ko sahi order mein lagao.
const dates = ["2024-01-01", "2023-12-12", "2024-03-01"];
// console.log(dates.sort());

// ________________________________________
//? 🔤 String methods only – Bas string se kheloge ab!
// ________________________________________
//! 24. Har naam ka pehla character nikaalo (charAt / [0])
const namaas = ["Ankita", "Bhavesh", "Chandan", "Divya"];
// console.log(namaas.map(name => name.charAt(0)));

// ________________________________________
//! 25. Sab strings ko lowercase mein convert karo (toLowerCase)
const cities = ["DELHI", "MUMBAI", "CHENNAI"];
// console.log(cities.map(city => city.toLowerCase()));

// ________________________________________
//! 26. Har string ke aage "Mr./Ms." jod do (concat)
const namess = ["Ravi", "Sneha", "Arjun"];
// console.log(namess.map(name => "Mr./Ms. ".concat(name)));

// ________________________________________
//! 27. Sab strings ke starting/trailing spaces hata do (trim)
const messyNames = ["  Raju  ", "   Meena", "Tarun   "];
// console.log(messyNames.map(name => name.trim()));

// ________________________________________
//! 28. Check karo kaunse string "Singh" se end ho rahe hain (endsWith)
const surnames = ["Ram Singh", "Ajay", "Rohit Singh"];
// console.log(surnames.filter(name => name.endsWith("Singh")));

// ________________________________________
//! 29. Sab string ko 10 characters tak padStart karo (padStart)
const number = ["45", "6", "123", "9999"];
// console.log(number.map(num => num.padStart(10, '0')));

// ________________________________________
//! 30. "Delhi" ko "New Delhi" bana do (replace)
const city = "Delhi";
// console.log(city.replace("Delhi", "New Delhi"));

// ________________________________________
//! 31. Substring nikalo first 3 characters ka (substring / slice)
const namesa = ["Amitabh", "Shahrukh", "Salman"];
// console.log(namesa.map(name => name.substring(0, 3)));

// ________________________________________
//! 32. Har string ka last character print karo
const foods = ["Poha", "Upma", "Idli", "Dosa"];
// console.log(foods.map(food => food.charAt(food.length - 1)));

// ________________________________________
//! 33. Convert string to array using split() and back to string using join()
const sentence = "Chai peene chalein?"; 
console.log(sentence.split(" ").join("-"));


