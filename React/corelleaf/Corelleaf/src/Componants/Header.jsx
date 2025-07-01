import { Link } from 'react-router-dom';

const Header = () => {
  return (
    <header className="bg-white sticky top-0 shadow-sm py-3 z-50">
      <div className="container mx-auto flex items-center justify-between">
        <Link to="/" className="flex items-center">
          <img
            src="https://corelleaf.com/static/media/logo.2c8a2870.png"
            width="150"
            className="d-inline-block align-top"
            alt="Coreleaf logo"
          />
        </Link>
        <nav className="flex space-x-6">
          <Link to="/" className="text-gray-700 hover:text-green-600 font-medium">Home</Link>
          <Link to="/about" className="text-gray-700 hover:text-green-600 font-medium">About</Link>
          <Link to="/portfolio" className="text-gray-700 hover:text-green-600 font-medium">Work</Link>
          <Link to="/contact" className="text-gray-700 hover:text-green-600 font-medium">Contact</Link>
        </nav>
      </div>
    </header>
  );
};

export default Header;