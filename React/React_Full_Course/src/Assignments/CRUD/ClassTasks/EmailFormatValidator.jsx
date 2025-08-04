import React, { useState } from "react";

const EmailFormatValidator = () => {
  let [email, setmail] = useState("");
  let [valid, setValid] = useState(false);
  let handleSubmit = () => {
    email.includes("@", ".com") ? setValid(true) : setValid(false);
    setmail("")
  };
  return (
    <div>
      <input
        type="email"
        placeholder="email"
        onChange={(e) => setmail(e.target.value)}
      />
      <button type="button" onClick={handleSubmit}>
        submit
      </button>

      <div>{!valid ? "check mail" : "Email correct"}</div>
    </div>
  );
};

export default EmailFormatValidator;
