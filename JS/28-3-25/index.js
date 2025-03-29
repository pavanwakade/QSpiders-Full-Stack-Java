// 1. Callback Function
function shout(message, callback) {
    callback(message);
}
shout("hello", (msg) => console.log(msg.toUpperCase()));

// 2. Friendly Robot
function robot(name, callback) {
    console.log(callback(name));
}
robot("Alice", (name) => `Hello, ${name}! I am your friendly robot.`);

// 3. Math Wizard
function mathWizard(a, b, callback) {
    console.log(callback(a, b));
}
mathWizard(5, 3, (x, y) => {
   return x + y
}
);

let decide = (msg, yesCallback, noCallback) => {
    if (msg === "happy") {
        console.log(yesCallback());
    } else {
        console.log(noCallback()); 
    }
}

let yes = () => "Yay!";

let no = () => "Oh no!";
decide("happy",yes,no);

// decide("sad",yes,no);

 

// 5. Double the Fun
let doubleFun =(num, callback) => console.log(callback(num));

doubleFun(4, (n) => n * 2);

// 6. Compliment Machine
function complimentMachine(name, callback) {
    console.log(callback(name));
}
let awesome = (name) => `${name}, you are awesome!`;
complimentMachine("Sam",awesome);

// 7. Checker Board
function checkNumber(num, callback) {
    console.log(callback(num));
}
checkNumber(10, (n) => (n % 2 === 0 ? "Even Number" : "Odd Number"));

// 8. Number Judge
function judgeNumber(num, callback) {
    console.log(callback(num));
}
judgeNumber(-5, (n) => (n > 0 ? "Positive" : "Negative"));

// 9. Tiny Calculator
function tinyCalculator(a, b, callback) {
    console.log(callback(a, b));
}
tinyCalculator(8, 2, (x, y) => x * y);

// 10. Name Styler
function nameStyler(name, callback) {
    console.log(callback(name));
}
nameStyler("Charlie", (name) => `***${name.toUpperCase()}***`);

// 11. Number Transformer
function transform(num, callback) {
    console.log(callback(num));
}
transform(3, (n) => n ** 2);

// 12. Ask the Oracle
function askOracle(question, callback) {
    console.log(callback(question));
}
askOracle("Will I succeed?", () => ["Yes", "No", "Maybe"][Math.floor(Math.random() * 3)]);

// 13. Number Battle
function numberBattle(a, b, callback) {
    console.log(callback(a, b));
}
numberBattle(7, 9, Math.max);

// 14. Your Mood Checker
function moodChecker(mood, callback) {
    console.log(callback(mood));
}
moodChecker("happy", (m) => (m === "happy" ? "😊" : "😢"));

// 15. Magic Mirror
function magicMirror(word, callback) {
    console.log(callback(word));
}
magicMirror("hello", (w) => w.split('').reverse().join(''));

// 16. Age Validator
function isAdult(age, callback) {
    console.log(callback(age));
}
isAdult(20, (age) => (age >= 18 ? "You are an adult" : "You are a minor"));

// 17. Magic Number
function magicNumber(num, callback) {
    console.log(callback(num));
}
magicNumber(7, (n) => (n === 7 ? "Lucky Number!" : "Just a number"));

// 18. Shout or Whisper
function speak(message, callback) {
    console.log(callback(message));
}
speak("HELLO", (msg) => (msg === msg.toUpperCase() ? "Shouting!" : "Whispering!"));

// 19. Number Magic Show
function numberMagic(num, callback) {
    console.log(callback(num));
}
numberMagic(10, (n) => (n % 5 === 0 ? "High Five!" : "No Magic"));

// 20. Friendly Introduction
function introduce(name, callback) {
    console.log(callback(name));
}
introduce("Bob", (name) => `Hey, I'm ${name}! Nice to meet you!`);
