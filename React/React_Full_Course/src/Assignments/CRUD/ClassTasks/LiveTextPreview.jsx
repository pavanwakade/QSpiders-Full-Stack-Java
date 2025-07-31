import React from "react";

const LiveTextPreview = () => {
  return (
    <div>
      <input type="text" onChange={(e) => e.target.value} />
<div>
{
  e.target.value
}
</div>

    </div>
  );
};

export default LiveTextPreview;
