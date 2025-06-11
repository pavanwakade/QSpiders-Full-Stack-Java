import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import App from './App.jsx';
import './index.css';
//! all by using class components.
console.log('all by using class components.'); // Added console log

createRoot(document.getElementById('root')).render(
  <StrictMode>
    {/* <h1> h1 from main</h1> */}
    <App />
  </StrictMode>,
)
