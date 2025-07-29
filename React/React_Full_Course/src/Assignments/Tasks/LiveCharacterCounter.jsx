import React, { useState } from 'react';

function LiveCharacterCounter() {
  const [value, setValue] = useState('');
  const overLimit = value.length > 20;
  return (
    <div>
      <input
        value={value}
        onChange={e => setValue(e.target.value)}
        style={{ borderColor: overLimit ? 'red' : undefined }}
      />
      <div>Characters: {value.length}</div>
    </div>
  );
}
export default LiveCharacterCounter;
