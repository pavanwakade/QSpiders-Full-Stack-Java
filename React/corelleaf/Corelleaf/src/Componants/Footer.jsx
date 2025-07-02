import { Link } from 'react-router-dom';
import './Footer.css';

const Footer = () => {
  return (
    <footer className="bg-secondary text-textLight py-10 mt-10 border-t border-borderLight animate-fadeInUp">
      <div className="container mx-auto px-4">
        <div className="flex flex-wrap -mx-4">
          <div className="w-full md:w-1/2 lg:w-1/3 px-4 mb-8">
            <h4 className="text-lg font-semibold mb-2 text-textLight">Social Media</h4>
            <p className="mb-4">Follow us on social media to find out the latest updates on our progress.</p>
            <div className="social-links flex gap-4">
              <a href="https://twitter.com/corelleaf" target="_blank" rel="noopener noreferrer" className="hover:text-accent transition">
                <svg className="w-6 h-6" fill="currentColor" viewBox="0 0 24 24"><path d="M24 4.557a9.93 9.93 0 0 1-2.828.775 4.932 4.932 0 0 0 2.165-2.724c-.951.564-2.005.974-3.127 1.195A4.92 4.92 0 0 0 16.616 3c-2.73 0-4.942 2.21-4.942 4.932 0 .386.045.763.127 1.124C7.728 8.807 4.1 6.884 1.671 3.965c-.423.722-.666 1.561-.666 2.475 0 1.708.87 3.216 2.188 4.099a4.904 4.904 0 0 1-2.237-.616c-.054 2.281 1.581 4.415 3.949 4.89a4.936 4.936 0 0 1-2.224.084c.627 1.956 2.444 3.377 4.6 3.417A9.868 9.868 0 0 1 0 21.543a13.94 13.94 0 0 0 7.548 2.209c9.057 0 14.009-7.496 14.009-13.986 0-.213-.005-.425-.014-.636A9.936 9.936 0 0 0 24 4.557z"/></svg>
              </a>
              <a href="https://www.linkedin.com/company/corelleaf" target="_blank" rel="noopener noreferrer" className="hover:text-accent transition">
                <svg className="w-6 h-6" fill="currentColor" viewBox="0 0 24 24"><path d="M19 0h-14c-2.76 0-5 2.24-5 5v14c0 2.76 2.24 5 5 5h14c2.76 0 5-2.24 5-5v-14c0-2.76-2.24-5-5-5zm-11 19h-3v-10h3v10zm-1.5-11.27c-.966 0-1.75-.79-1.75-1.76 0-.97.784-1.76 1.75-1.76s1.75.79 1.75 1.76c0 .97-.784 1.76-1.75 1.76zm13.5 11.27h-3v-5.6c0-1.34-.03-3.07-1.87-3.07-1.87 0-2.16 1.46-2.16 2.97v5.7h-3v-10h2.89v1.36h.04c.4-.76 1.37-1.56 2.82-1.56 3.01 0 3.57 1.98 3.57 4.56v5.64z"/></svg>
              </a>
              <a href="https://www.facebook.com/corelleaf" target="_blank" rel="noopener noreferrer" className="hover:text-accent transition">
                <svg className="w-6 h-6" fill="currentColor" viewBox="0 0 24 24"><path d="M22.675 0h-21.35c-.733 0-1.325.592-1.325 1.326v21.348c0 .733.592 1.326 1.325 1.326h11.495v-9.294h-3.128v-3.622h3.128v-2.671c0-3.1 1.893-4.788 4.659-4.788 1.325 0 2.463.099 2.797.143v3.24l-1.918.001c-1.504 0-1.797.715-1.797 1.763v2.312h3.587l-.467 3.622h-3.12v9.293h6.116c.73 0 1.323-.593 1.323-1.326v-21.349c0-.734-.593-1.326-1.326-1.326z"/></svg>
              </a>
            </div>
          </div>
          <div className="w-full md:w-1/2 lg:w-1/3 px-4 mb-8">
            <h4 className="text-lg font-semibold mb-2 text-textLight">Navigation</h4>
            <ul>
              <li><Link to="/" className="hover:text-accent transition">Home</Link></li>
              <li><Link to="/about" className="hover:text-accent transition">About Us</Link></li>
              <li><Link to="/portfolio" className="hover:text-accent transition">Work</Link></li>
              <li><Link to="/contact" className="hover:text-accent transition">Contact</Link></li>
            </ul>
          </div>
          <div className="w-full lg:w-1/3 px-4 mb-8">
            <h4 className="text-lg font-semibold mb-2 text-textLight">Contact</h4>
            <p className="mb-2">Mail us: <a href="mailto:career@corelleaf.com" className="hover:text-accent transition">career@corelleaf.com</a></p>
            <p>Address: Venus Garden Building, Office No- 10, behind Bank of Baroda, Thite Vasti, Kharadi, Maharashtra 411014.</p>
          </div>
        </div>
        <div className="mt-8 text-center text-gray-400">
          <p>&copy; 2020 corelleaf design studio</p>
        </div>
      </div>
    </footer>
  );
};

export default Footer;