import React, { useState } from 'react';

function Counter({ initial }) {
  const [count, setCount] = useState(initial);
  return (
    <div>
      <button onClick={() => setCount(c => c - 1)}>-</button>
      <span>{count}</span>
      <button onClick={() => setCount(c => c + 1)}>+</button>
    </div>
  );
}

function ReusableCounterComponentUsingProps() {
  return (
    <div>
      <Counter initial={0} />
      <Counter initial={10} />
      <Counter initial={100} />
    </div>
  );
}
export default ReusableCounterComponentUsingProps;
