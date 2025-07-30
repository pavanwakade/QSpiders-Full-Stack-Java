import React, { useState } from 'react';

const BasicLoginForm = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleUsernameChange = (event) => {
    setUsername(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    // Handle form submission logic here
    console.log('Username:', username);
    console.log('Password:', password);
  };

  return (
    <form onSubmit={handleSubmit}>
      <label>Username:</label>
      <input type="text" value={username} onChange={handleUsernameChange} /><br />
      <label>Password:</label>
      <input type="password" value={password} onChange={handlePasswordChange} /><br />
      <button type="submit">Login</button>
    </form>
  );
};

export default BasicLoginForm;