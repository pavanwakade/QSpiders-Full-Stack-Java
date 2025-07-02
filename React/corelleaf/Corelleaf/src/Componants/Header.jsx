import { Link } from 'react-router-dom';

const Header = () => {
  return (
    <header className="bg-primary sticky top-0 shadow-lg py-4 z-50 transition-all duration-300 animate-fadeInDown">
      <div className="container mx-auto flex items-center justify-between px-4 md:px-8">
        <Link to="/" className="flex items-center gap-2">
          <img
            src="https://corelleaf.com/static/media/logo.2c8a2870.png"
            width="140"
            className="d-inline-block align-top drop-shadow-lg transition-transform hover:scale-105"
            alt="Coreleaf logo"
          />
        </Link>
        <nav className="flex space-x-4 md:space-x-8">
          <Link to="/" className="text-textLight hover:text-accent font-medium transition-colors duration-200">Home</Link>
          <Link to="/about" className="text-textLight hover:text-accent font-medium transition-colors duration-200">About</Link>
          <Link to="/portfolio" className="text-textLight hover:text-accent font-medium transition-colors duration-200">Work</Link>
          <Link to="/contact" className="text-textLight hover:text-accent font-medium transition-colors duration-200">Contact</Link>
        </nav>
      </div>
    </header>
  );
};

export default Header;