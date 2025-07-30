import React, { useState } from 'react';

const FormStateTracker = () => {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');

  const handleNameChange = (event) => {
    setName(event.target.value);
  };

  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };

  return (
    <div>
      <label>Name:</label>
      <input type="text" value={name} onChange={handleNameChange} /><br />
      <label>Email:</label>
      <input type="email" value={email} onChange={handleEmailChange} /><br />
      <p>Name: {name}</p>
      <p>Email: {email}</p>
    </div>
  );
};

export default FormStateTracker;