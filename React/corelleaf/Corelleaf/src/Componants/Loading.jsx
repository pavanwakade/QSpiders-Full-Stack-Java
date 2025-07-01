import { useEffect, useState } from 'react';
import './Style/Loading.css';

const Loading = () => {
  const [counter, setCounter] = useState(8); // Start from 8

  useEffect(() => {
    if (counter === 0) return;

    const timer = setTimeout(() => {
      setCounter(prev => prev - 1);
    }, 100);

    return () => clearTimeout(timer);
  }, [counter]);

  return (
    <div className="loading-container">
      <div className="counter-display">
        {counter}
      </div>
      <div className="loading-text">Loading...</div>
    </div>
  );
};

export default Loading;
