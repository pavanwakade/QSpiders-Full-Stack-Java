import React, { useState, useRef } from 'react';

function Modal({ onClose }) {
  const inputRef = useRef();
  React.useEffect(() => {
    inputRef.current && inputRef.current.focus();
  }, []);
  return (
    <div>
      <button onClick={onClose}>X</button>
      <input ref={inputRef} placeholder="Autofocus" />
    </div>
  );
}

function ModalComponentWithToggle() {
  const [open, setOpen] = useState(false);
  return (
    <div>
      <button onClick={() => setOpen(true)}>Open Modal</button>
      {open && <Modal onClose={() => setOpen(false)} />}
    </div>
  );
}
export default ModalComponentWithToggle;
