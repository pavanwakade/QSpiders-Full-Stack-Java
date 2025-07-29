import React, { useState } from 'react';

function ShowHidePasswordField() {
  const [show, setShow] = useState(false);
  const [value, setValue] = useState('');
  return (
    <div>
      <input
        type={show ? 'text' : 'password'}
        value={value}
        onChange={e => setValue(e.target.value)}
      />
      <label>
        <input
          type="checkbox"
          checked={show}
          onChange={() => setShow(s => !s)}
        />
        Show Password
      </label>
    </div>
  );
}
export default ShowHidePasswordField;
