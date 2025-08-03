import React, { useEffect, useState } from "react";

const PostLoginGreeting = () => {
  const [login, setLogin] = useState("");
  const [name, setName] = useState("");

  useEffect(() => {
    console.log("Logged in as:", login);
  }, [login]);

  return (
    <div className="p-4 text-white">
      <input
        type="text"
        className="bg-[gray] text-white px-2 py-1 rounded"
        placeholder="Enter name"
        value={name}
        onChange={(e) => setName(e.target.value)}
      />
      <br />
      <button
        type="submit"
        className="px-3 py-1 mt-2 bg-blue-500 rounded"
        onClick={() => {
          setLogin(name.trim());
          setName("");
        }}
      >
        Login
      </button>

      {login && (
        <div className="p-2 mt-4 rounded bg-slate-600">
          {`Welcome ${login}`}
        </div>
      )}
    </div>
  );
};

export default PostLoginGreeting;
