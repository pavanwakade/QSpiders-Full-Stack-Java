import React, { useState } from 'react';

const Counter = () => {
  const [count, setCount] = useState(0);
  return (
    <div>
      <h2>Counter: {count}</h2>
      <button onClick={() => setCount(count + 1)}>Increment</button>   <br />
      <button onClick={() => setCount(count - 1)}>Decrement</button>    <br />
      <button onClick={() => setCount(0)}>Reset</button>
    </div>
  );
};

export default Counter;