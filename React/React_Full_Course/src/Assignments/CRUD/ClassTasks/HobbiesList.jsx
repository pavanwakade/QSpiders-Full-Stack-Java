import React from 'react';

const HobbiesList = ({ hobbies }) => {
  return (
    <ul>
      {hobbies.map((hobby, index) => (
        <p key={index}>{index} . {hobby}</p>
      ))}
    </ul>
  );
};

export default HobbiesList;