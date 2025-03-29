// 12. Ask the Oracle
function askOracle(question, callback) {
    console.log(callback(question));
}
askOracle("Will I succeed?", () => [
    "Nahi bhai nahi",
    "Yes", 
    "No", 
    "Maybe",
    "kyo",
    "Without a doubt",
    "Ask again later",
    "Better not tell you now",
    "Cannot predict now"
][Math.floor(Math.random() * 9)]);
