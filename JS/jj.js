
// 15. Magic Mirror
function magicMirror(word, callback) {
    console.log(callback(word));
}

let word = "    hello   ";
// console.log(word);
// console.log(word.trim()); // "hello"

magicMirror( word, (w) => w.split('').reverse().join('').trim() ); // "olleh"