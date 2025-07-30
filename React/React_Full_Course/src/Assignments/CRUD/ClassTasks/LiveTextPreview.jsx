import React, { useState } from 'react';

const LiveTextPreview = () => {
  const [text, setText] = useState('');

  const handleTextChange = (event) => {
    setText(event.target.value);
  };

  return (
    <div>
      <input type="text" value={text} onChange={handleTextChange} />
      <p>Preview: {text}</p>
    </div>
  );
};

export default LiveTextPreview;