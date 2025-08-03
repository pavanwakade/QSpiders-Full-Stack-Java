import React, { useEffect, useState } from "react";

const LiveDigitalClock = () => {
  let [time, settime] = useState(new Date());

  useEffect(() => {
    settime(new Date());
  }, [time]);
  // console.log(new Date());

  return (
    <div>
      {time.toLocaleDateString()} <br />
      {time.toLocaleTimeString()}
    </div>
  );
};

export default LiveDigitalClock;
