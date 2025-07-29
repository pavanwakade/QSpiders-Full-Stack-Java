import React, { useRef, useEffect } from 'react';

function AutoFocusInputOnPageLoad() {
  const inputRef = useRef();
  useEffect(() => {
    inputRef.current && inputRef.current.focus();
  }, []);
  return <input ref={inputRef} placeholder="Auto focus" />;
}
export default AutoFocusInputOnPageLoad;
