import { Link } from 'react-router-dom';
import './Footer.css';

const Footer = () => {
  return (
    <footer className="footer-section bg-gray-100 py-10 mt-10">
      <div className="container mx-auto px-4">
        <div className="flex flex-wrap -mx-4">
          <div className="w-full md:w-1/2 lg:w-1/3 px-4 mb-8">
            <h4 className="text-lg font-semibold mb-2">Social Media</h4>
            <p className="mb-4">Follow us on social media to find out the latest updates on our progress.</p>
            <div className="social-links">
              {/* Add social media links here */}
            </div>
          </div>
          <div className="w-full md:w-1/2 lg:w-1/3 px-4 mb-8">
            <h4 className="text-lg font-semibold mb-2">Navigation</h4>
            <ul>
              <li><Link to="/" className="hover:text-green-600">Home</Link></li>
              <li><Link to="/about" className="hover:text-green-600">About Us</Link></li>
              <li><Link to="/portfolio" className="hover:text-green-600">Work</Link></li>
              <li><Link to="/contact" className="hover:text-green-600">Contact</Link></li>
            </ul>
          </div>
          <div className="w-full lg:w-1/3 px-4 mb-8">
            <h4 className="text-lg font-semibold mb-2">Contact</h4>
            <p className="mb-2">Mail us: <a href="mailto:career@corelleaf.com" className="hover:text-green-600">career@corelleaf.com</a></p>
            <p>Address: Venus Garden Building, Office No- 10, behind Bank of Baroda, Thite Vasti, Kharadi, Maharashtra 411014.</p>
          </div>
        </div>
        <div className="mt-8 text-center">
          <p>&copy; 2020 corelleaf design studio</p>
        </div>
      </div>
    </footer>
  );
};

export default Footer;