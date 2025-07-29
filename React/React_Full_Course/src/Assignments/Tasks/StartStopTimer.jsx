import React, { useRef, useState } from 'react';

function StartStopTimer() {
  const [seconds, setSeconds] = useState(0);
  const [running, setRunning] = useState(false);
  const intervalRef = useRef();

  const start = () => {
    if (!running) {
      setRunning(true);
      intervalRef.current = setInterval(() => {
        setSeconds(s => s + 1);
      }, 1000);
    }
  };
  const stop = () => {
    setRunning(false);
    clearInterval(intervalRef.current);
  };
  return (
    <div>
      <div>Seconds: {seconds}</div>
      <button onClick={start}>Start</button>
      <button onClick={stop}>Stop</button>
    </div>
  );
}
export default StartStopTimer;
