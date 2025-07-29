import React, { useState } from 'react';

function ToggleButtonState() {
  const [isOn, setIsOn] = useState(false);
  return (
    <div>
      <div>Status: {isOn ? 'ON' : 'OFF'}</div>
      <button
        style={{ background: isOn ? 'lightgreen' : 'lightcoral' }}
        onClick={() => setIsOn((prev) => !prev)}
      >
        {isOn ? 'ON' : 'OFF'}
      </button>
    </div>
  );
}
export default ToggleButtonState;
