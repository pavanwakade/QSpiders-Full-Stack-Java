import React, { useState } from 'react';

function ClickCounterWithCustomMessage() {
  const [count, setCount] = useState(0);
  return (
    <div>
      <div>Count: {count}</div>
      <button onClick={() => setCount(c => c + 1)}>Click</button>
      {count > 0 && count % 5 === 0 && <div>Milestone Reached!</div>}
    </div>
  );
}
export default ClickCounterWithCustomMessage;
