import React, { useState } from 'react';

const ThemeToggle = () => {
  const [isDarkMode, setIsDarkMode] = useState(false);

  const toggleTheme = () => {
    setIsDarkMode(!isDarkMode);
  };

  return (
    <div style={{ backgroundColor: isDarkMode ? 'black' : 'white', color: isDarkMode ? 'white' : 'black' }}>
      <button onClick={toggleTheme}>Toggle Theme</button>
      <p>Current Mode: {isDarkMode ? 'Dark Mode' : 'Light Mode'}</p>
    </div>
  );
};

export default ThemeToggle;