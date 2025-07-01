import { Col, Container, Row } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import './Footer.css';

const Footer = () => {
  return (
    <footer className="footer-section">
      <Container>
        <Row>
          <Col lg={4} md={6} sm={12} className="footer-widget">
            <h4>Social Media</h4>
            <p>Follow us on social media to find out the latest updates on our progress.</p>
            <div className="social-links">
              {/* Add social media links here */}
            </div>
          </Col>
          <Col lg={4} md={6} sm={12} className="footer-widget">
            <h4>Navigation</h4>
            <ul>
              <li><Link to="/">Home</Link></li>
              <li><Link to="/about">About Us</Link></li>
              <li><Link to="/portfolio">Work</Link></li>
              <li><Link to="/contact">Contact</Link></li>
            </ul>
          </Col>
          <Col lg={4} md={12} sm={12} className="footer-widget">
            <h4>Contact</h4>
            <p>Mail us: <a href="mailto:career@corelleaf.com">career@corelleaf.com</a></p>
            <p>Address: Venus Garden Building, Office No- 10, behind Bank of Baroda, Thite Vasti, Kharadi, Maharashtra 411014.</p>
          </Col>
        </Row>
        <Row className="mt-4">
          <Col className="text-center">
            <p>&copy; 2020 corelleaf design studio</p>
          </Col>
        </Row>
      </Container>
    </footer>
  );
};

export default Footer;