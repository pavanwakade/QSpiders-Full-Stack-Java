import React, { createContext, useContext, useState } from 'react';

const AuthContext = createContext();

function AuthProvider({ children }) {
  const [loggedIn, setLoggedIn] = useState(false);
  return (
    <AuthContext.Provider value={{ loggedIn, setLoggedIn }}>
      {children}
    </AuthContext.Provider>
  );
}

function LoginForm() {
  const { setLoggedIn } = useContext(AuthContext);
  const [user, setUser] = useState('');
  const [pass, setPass] = useState('');
  const handleSubmit = e => {
    e.preventDefault();
    if (user === 'admin' && pass === '1234') setLoggedIn(true);
  };
  return (
    <form onSubmit={handleSubmit}>
      <input value={user} onChange={e => setUser(e.target.value)} placeholder="User" />
      <input value={pass} onChange={e => setPass(e.target.value)} placeholder="Pass" type="password" />
      <button type="submit">Login</button>
    </form>
  );
}

function Dashboard() {
  return <div>Welcome</div>;
}

function LoginFormWithContextAndConditionalUI() {
  return (
    <AuthProvider>
      <AuthConsumer />
    </AuthProvider>
  );
}

function AuthConsumer() {
  const { loggedIn } = useContext(AuthContext);
  return loggedIn ? <Dashboard /> : <LoginForm />;
}

export default LoginFormWithContextAndConditionalUI;
