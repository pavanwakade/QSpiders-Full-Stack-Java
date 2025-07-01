import { useState } from 'react';
import { Link, Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import './App.css';
import About from './Componants/About';
import Contact from './Componants/Contact';
import LandingPage from './Componants/LandingPage';
import Loading from './Componants/Loading';
import Portfolio from './Componants/Portfolio';
import Services from './Componants/Services';

function App() {
  const [loading, setLoading] = useState(true);

  return (
    <>
      {loading && <Loading onFinished={() => setLoading(false)} />}
      {!loading && (
        <Router>
          <nav className="navbar">
            <div className="logo">corelleaf Design Studio</div>
            <ul>
              <li><Link to="/">Home</Link></li>
              <li><Link to="/about">About</Link></li>
              <li><Link to="/services">Services</Link></li>
              <li><Link to="/portfolio">Portfolio</Link></li>
              <li><Link to="/contact">Contact</Link></li>
            </ul>
          </nav>
          <Routes>
            <Route path="/" element={<LandingPage />} />
            <Route path="/about" element={<About />} />
            <Route path="/services" element={<Services />} />
            <Route path="/portfolio" element={<Portfolio />} />
            <Route path="/contact" element={<Contact />} />
          </Routes>
        </Router>
      )}
    </>
  )
}

export default App
