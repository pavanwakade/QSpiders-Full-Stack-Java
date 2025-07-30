import React from 'react';

const ProfileCard = ({ name, age, location }) => {
  return (
    <div style={{ border: '1px solid #ccc', padding: '10px', margin: '10px', width: '300px' ,background:"#27F56F" }} >
      <h3>Name:{name}</h3>
      <p>Age: {age}</p>
      <p>Location: {location}</p>
    </div>
  );
};

export default ProfileCard;