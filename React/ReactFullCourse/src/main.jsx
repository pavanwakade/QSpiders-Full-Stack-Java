import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import App from './App.jsx';
import './index.css';

console.log('Attempting to render the App component.'); // Added console log

createRoot(document.getElementById('root')).render(
  <StrictMode>
    {/* <h1> h1 from main</h1> */}
    <App />
  </StrictMode>,
)
