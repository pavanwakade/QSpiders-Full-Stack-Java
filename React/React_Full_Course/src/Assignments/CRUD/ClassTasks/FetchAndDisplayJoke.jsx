import React, { useEffect, useState } from "react";

const FetchAndDisplayJoke = () => {
  let [jok, setJok] = useState("");
  useEffect(() => {
    let fetchdata = async () => {
      let response = await fetch("https://api.chucknorris.io/jokes/random");

      let data = await response.json();
      setJok(data.value);
    };
    fetchdata();
  }, []);

  return <div className="bg-[pink] py-5 px-5 rounded-md fles flex-wrap">Joke   :   {jok}</div>;
};

export default FetchAndDisplayJoke;
