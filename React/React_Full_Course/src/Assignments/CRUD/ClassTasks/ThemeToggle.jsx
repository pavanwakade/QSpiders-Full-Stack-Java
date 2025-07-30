import React, { useState } from 'react';

const ThemeToggle = () => {
  const [isDarkMode, setIsDarkMode] = useState(false);

  return (
    <div style={{ background: !isDarkMode ? 'white' : 'black', color: !isDarkMode ? 'black' : 'white', height: 100, width: 100 }}>
      <button onClick={() => setIsDarkMode(!isDarkMode)}>{!isDarkMode ? 'Dark Mode' : 'Light Mode'}</button>
    </div>
  );
};

export default ThemeToggle;