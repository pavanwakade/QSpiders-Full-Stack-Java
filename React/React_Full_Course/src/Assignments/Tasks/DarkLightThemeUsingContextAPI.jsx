import React, { createContext, useContext, useState } from 'react';

const ThemeContext = createContext();

function ThemeProvider({ children }) {
  const [dark, setDark] = useState(false);
  return (
    <ThemeContext.Provider value={{ dark, setDark }}>
      <div style={{ background: dark ? '#222' : '#fff', minHeight: '100vh' }}>
        {children}
      </div>
    </ThemeContext.Provider>
  );
}

function ThemeToggler() {
  const { dark, setDark } = useContext(ThemeContext);
  return <button onClick={() => setDark(d => !d)}>{dark ? 'Light' : 'Dark'} Mode</button>;
}

function ChildComponent() {
  const { dark } = useContext(ThemeContext);
  return <div>Theme is {dark ? 'Dark' : 'Light'}</div>;
}

function DarkLightThemeUsingContextAPI() {
  return (
    <ThemeProvider>
      <ThemeToggler />
      <ChildComponent />
    </ThemeProvider>
  );
}
export default DarkLightThemeUsingContextAPI;
