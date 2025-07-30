import React, { useState } from 'react';

const ThemeToggle = () => {
  const [isDarkMode, setIsDarkMode] = useState(false);

  return (
    <div style={{ background: !isDarkMode ? 'white' : 'black', color: !isDarkMode ? 'black' : 'white' }} className='h-[500px] w-[500px] flex items-center justify-center'>
      <button onClick={() => setIsDarkMode(!isDarkMode)} className='bg-[green] rounded-md px-4 py-1'>{!isDarkMode ? 'Dark Mode' : 'Light Mode'}</button>
    </div>
  );
};

export default ThemeToggle;