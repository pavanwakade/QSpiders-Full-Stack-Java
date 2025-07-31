import React, { useState } from "react";

const ConsoleLogFormData = () => {
  console.log("hii");

  let [state, setState] = useState("");

  let handlwSubmit = () => {
    console.log( "name : " + state);
  };
  return (
    <div>
      <form action={handlwSubmit}>
        <input
          type="text"
          onChange={(e) => setState(e.target.value)}
          placeholder="Enter name"
        />
        <button type="submit">submit</button>
      </form>
    </div>
  );
};

export default ConsoleLogFormData;
