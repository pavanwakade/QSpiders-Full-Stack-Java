import React, { useState } from "react";

const ParagraphToggle = () => {
  let [show, setShow] = useState(false);
  let toggleVisibility = () => {
    setShow(!show);
  };
  return (
    <div className="h-[30%] w-[30%] ">
      <button type="button" onClick={toggleVisibility} className="px-3 py-1 bg-red-400 border rounded-md">
        {!show ? "show" : "hide"}
      </button>
      <div>
        {!show && (
          <p className="flex justify-center p-10 text-white align-center bg-stone-500">
            Lorem, ipsum dolor sit amet consectetur adipisicing elit.
            Repudiandae porro veritatis, magnam pariatur tempora laudantium,
            reiciendis odit facilis inventore nam dolore minus eligendi dolor
            quas aliquam ducimus nobis aperiam atque.
          </p>
        )}
      </div>
    </div>
  );
};

export default ParagraphToggle;
