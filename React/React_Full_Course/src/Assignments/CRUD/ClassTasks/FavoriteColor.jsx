import React from 'react';

const FavoriteColor = ({ color = 'red' }) => {
  return (
    <div style={{ color: color }}>
      My favorite color is {color}.
    </div>
  );
};

export default FavoriteColor;