import React, { useState } from "react";

const EmailFormatValidator = () => {
  let [email, setmail] = useState("");
  let [valid, setValid] = useState(false);
  let handleSubmit = () => {
    email.includes("@", ".com") ? setValid(true) : setValid(false);
    setmail("");
  };
  return (
    <div>
      <input
        type="email"
        value={email}
        placeholder="email"
        onChange={(e) => setmail(e.target.value)}
      />   <br /> <br />
      <button type="button" onClick={handleSubmit}>
        submit
      </button>
      <div>{!valid ? <p className="bg-[red]">check mail</p> : <p className="bg-[green]">Email correct</p> }</div>
    </div>
  );
};

export default EmailFormatValidator;
