import React, { useState } from 'react';

const ConsoleLogFormData = () => {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');

  const handleNameChange = (event) => {
    setName(event.target.value);
  };

  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    console.log('Name:', name);
    console.log('Email:', email);
  };

  return (
    <form onSubmit={handleSubmit}>
      <label>Name:</label>
      <input type="text" value={name} onChange={handleNameChange} /><br />
      <label>Email:</label>
      <input type="email" value={email} onChange={handleEmailChange} /><br />
      <button type="submit">Submit</button>
    </form>
  );
};

export default ConsoleLogFormData;