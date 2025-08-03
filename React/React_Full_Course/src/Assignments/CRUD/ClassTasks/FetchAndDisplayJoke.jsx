import React, { useState, useEffect } from "react";

const FetchAndDisplayJoke = () => {
  const [joke, setJoke] = useState("");

  useEffect(() => {
    const fetchJoke = async () => {
      try {
        const response = await fetch("https://icanhazdadjoke.com", {
          headers: {
            Accept: "text/plain",
          },
        });
        const data = await response.text();
        setJoke(data);
      } catch (error) {
        setJoke("Failed to fetch joke");
      }
    };

    fetchJoke();
  }, []);

  return (
    <div>
      <p>{joke}</p>
    </div>
  );
};

export default FetchAndDisplayJoke;
