import React, { useState } from "react";

const LiveTextPreview = () => {
  let[text,setText]=useState('')
  return (
    <div>
      <input type="text" onChange={(e) => setText(e.target.value)} className="text-center border-2" placeholder="Enter text" />
<div> priview : { text} </div>

    </div>
  );
};

export default LiveTextPreview;
