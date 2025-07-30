import React from 'react';

const FavoriteColor = ({ color = 'red' }) => {
  return (
    <div style={{ color: 'white', background: color, padding: 10 }}>
      My favorite color is {color}.
    </div>
  );
};

export default FavoriteColor;