import { Component } from 'react';
import "../../../App.css";

class Landing extends Component {
  render() {
    return (
      <div className="landing-container">
        <header>
          <nav className="landing-nav" aria-label="Main navigation">
            <h3>Logo</h3>
            <div className="nav-links">
              <a href="#home">Home</a>
              <a href="#about">About</a>
              <a href="#services">Services</a>
              <a href="#contact">Contact</a>
            </div>
          </nav>
        </header>

        <main className="hero-section">
          <h1>Welcome to Our Amazing Platform</h1>
          <p className="subtitle">
            Discover the endless possibilities with our innovative solutions
          </p>
          <div className="cta-buttons">
            <button className="primary-btn">Get Started</button>
            <button className="secondary-btn">Learn More</button>
          </div>
        </main>

        <section className="features" aria-label="Features">
          <div className="feature-card">
            <h3>Fast & Reliable</h3>
            <p>Lightning-fast performance you can count on</p>
          </div>
          <div className="feature-card">
            <h3>Secure</h3>
            <p>Your security is our top priority</p>
          </div>
          <div className="feature-card">
            <h3>24/7 Support</h3>
            <p>We're here whenever you need us</p>
          </div>
        </section>
      </div>
    );
  }
}

export default Landing;