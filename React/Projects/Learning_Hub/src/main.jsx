import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import Alerts from './HOF/Alerts.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <Alerts>
    <App />
    </Alerts>
  </StrictMode>,
)
