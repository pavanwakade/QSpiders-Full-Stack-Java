import React from 'react';

const ColorListDisplay = () => {
  const colors = ['red', 'green', 'blue', 'yellow', 'purple'];

  return (
    <ul>
      {colors.map((color, index) => (
        <li key={index}>{color}</li>
      ))}
    </ul>
  );
};

export default ColorListDisplay;