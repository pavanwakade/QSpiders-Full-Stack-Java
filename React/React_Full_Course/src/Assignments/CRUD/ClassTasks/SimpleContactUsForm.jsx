import React, { useState } from 'react';

const SimpleContactUsForm = () => {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [message, setMessage] = useState('');

  const handleNameChange = (event) => {
    setName(event.target.value);
  };

  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };

  const handleMessageChange = (event) => {
    setMessage(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    console.log('Name:', name);
    console.log('Email:', email);
    console.log('Message:', message);
    alert('Form submitted!');
  };

  return (
    <form onSubmit={handleSubmit}>
      <label>Name:</label>
      <input type="text" value={name} onChange={handleNameChange} /><br />
      <label>Email:</label>
      <input type="email" value={email} onChange={handleEmailChange} /><br />
      <label>Message:</label>
      <textarea value={message} onChange={handleMessageChange} /><br />
      <button type="submit">Submit</button>
    </form>
  );
};

export default SimpleContactUsForm;