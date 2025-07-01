import { Route, Routes } from 'react-router-dom';
import About from './Componants/About';
import Contact from './Componants/Contact';
import Footer from './Componants/Footer';
import Header from './Componants/Header';
import Home from './Componants/LandingPage';
import Portfolio from './Componants/Portfolio';
import Services from './Componants/Services';

function App() {
  return (
    <>
      <Header />
      <main>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/about" element={<About />} />
          <Route path="/services" element={<Services />} />
          <Route path="/portfolio" element={<Portfolio />} />
          <Route path="/contact" element={<Contact />} />
        </Routes>
      </main>
      <Footer />
    </>
  );
}

export default App;