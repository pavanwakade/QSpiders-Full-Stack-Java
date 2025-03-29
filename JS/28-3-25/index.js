function decide(msg, yesCallback, noCallback) {
    if (msg === "happy") { // Compare with the correct string
        console.log(yesCallback()); // Log the return value of yesCallback
    } else {
        console.log(noCallback()); // Log the return value of noCallback
    }
}

// Calling the function with correct logging
decide("happy", () => {
    return "Yay!";
}, () => {
    return "Oh no!";
});
