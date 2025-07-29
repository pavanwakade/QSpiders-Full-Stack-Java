import React, { useRef } from 'react';

function ClearInputFieldUsingUseRef() {
  const inputRef = useRef();
  return (
    <div>
      <input ref={inputRef} />
      <button onClick={() => (inputRef.current.value = '')}>Clear</button>
    </div>
  );
}
export default ClearInputFieldUsingUseRef;
