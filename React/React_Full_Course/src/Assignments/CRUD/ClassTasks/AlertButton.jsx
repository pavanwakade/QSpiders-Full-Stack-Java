import React from 'react';

const AlertButton = () => {
  const showAlert = () => {
    alert('This is an alert message from button!');
  };

  return (
    <button onClick={showAlert}>Show Alert</button>
  );
};

export default AlertButton;