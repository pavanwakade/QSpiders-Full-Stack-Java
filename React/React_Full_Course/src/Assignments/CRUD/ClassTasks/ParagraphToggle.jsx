import React, { useState } from 'react';

const ParagraphToggle = () => {
  const [isVisible, setIsVisible] = useState(true);

  const toggleVisibility = () => {
    setIsVisible(!isVisible);
  };

  return (
    <div>
      <button onClick={toggleVisibility}>Toggle Paragraph</button>
      {isVisible && <p>This is a paragraph.</p>}
    </div>
  );
};

export default ParagraphToggle;