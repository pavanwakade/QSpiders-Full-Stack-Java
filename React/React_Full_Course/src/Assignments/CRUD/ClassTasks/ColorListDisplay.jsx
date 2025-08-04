import React from 'react';

const ColorListDisplay = () => {
  const colors = ['red', 'green', 'blue', 'yellow', 'purple'];

  return (
    <ul>
      {colors.map((color, i) => (
        <li key={i}>{i+1+" "}{color}</li>
      ))}
    </ul>
  );
};

export default ColorListDisplay;