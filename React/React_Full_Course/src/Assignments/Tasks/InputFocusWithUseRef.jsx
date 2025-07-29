import React, { useRef } from 'react';

function InputFocusWithUseRef() {
  const inputRef = useRef();
  return (
    <div>
      <input ref={inputRef} />
      <button onClick={() => inputRef.current && inputRef.current.focus()}>
        Focus Input
      </button>
    </div>
  );
}
export default InputFocusWithUseRef;
