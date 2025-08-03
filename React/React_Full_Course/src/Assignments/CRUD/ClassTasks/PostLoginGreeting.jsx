import React, { useState } from 'react';

const PostLoginGreeting = () => {
  const [username, setUsername] = useState('');
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const handleInputChange = (event) => {
    setUsername(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    setIsLoggedIn(true);
  };

  return (
    <div>
      {!isLoggedIn ? (
        <form onSubmit={handleSubmit}>
          <label>Username:</label>
          <input type="text" value={username} onChange={handleInputChange} /><br />
          <button type="submit">Login</button>
        </form>
      ) : (
        <h2>Welcome, {username}!</h2>
      )}
    </div>
  );
};

export default PostLoginGreeting;